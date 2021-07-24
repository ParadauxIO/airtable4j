package io.paradaux.airtable4j.core.structure;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.paradaux.airtable4j.Airtable4J;
import io.paradaux.airtable4j.http.ContentType;
import kotlin.collections.builders.ListBuilder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ATable {

    private final String name;
    private final Airtable4J airtable4J;
    private final ABase base;

    public ATable(String name, Airtable4J airtable4J, ABase base) {
        this.name = name;
        this.airtable4J = airtable4J;
        this.base = base;

        JsonObject obj = new JsonObject();
    }

    public Call create(Object record, Callback callback) {
        ArrayList<Object> list = new ArrayList<>();
        list.add(record);
        return create(list, callback);
    }

    public Call create(List<?> recordsObj, Callback callback) {
        JsonArray records = new JsonArray();

        for (final Object o : recordsObj) {
            JsonObject fieldMap = new JsonObject();
            fieldMap.add("fields", airtable4J.gson().toJsonTree(o));
            records.add(fieldMap);
        }

        JsonObject data = new JsonObject();
        data.add("records", records);

        RequestBody body = RequestBody.create(ContentType.JSON.getType(), airtable4J.gson().toJson(data));

        Request request = new Request.Builder()
                .url(base.url() + name)
                .addHeader("Authorization", "Bearer " + airtable4J.authenticate())
                .post(body)
                .build();

        Call call = airtable4J.client().newCall(request);
        call.enqueue(callback);
        return call;
    }

    public <T> ListQuery<T> list(T type) {
        return new ListQuery<T>();
    }

    public static class ListQuery<T> {

        private String[] fields;

        private String cellFormat;
        private String userLocale;
        private String timeZone;
        private String formula;
        private String view;

        private int maxRecords;
        private int pageSize;

        private ListQuery()  {}

        public ListQuery<T> fields(String[] fields) {
            this.fields = fields;
            return this;
        }

        public ListQuery<T> formula(String formula) {
            this.formula = formula;
            return this;
        }

        public ListQuery<T> maxRecords(int maxRecords) {
            this.maxRecords = maxRecords;
            return this;
        }

        public ListQuery<T> pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        /**
         * Unimplemented
         * */
        public ListQuery<T> sort(String str) {
            throw new RuntimeException("Unimplemented.");
        }

        public ListQuery<T> view(String view) {
            this.view = view;
            return this;
        }

        public ListQuery<T> cellFormat(String cellFormat) {
            this.cellFormat = cellFormat;
            return this;
        }

        public ListQuery<T> timeZone(String timeZone) {
            this.timeZone = timeZone;
            return this;
        }

        public ListQuery<T> userLocale(String userLocale) {
            this.userLocale = userLocale;
            return this;
        }

        public List<T> execute(Callback callback) {
            return null;
        }

        public List<T> execute() {
            return null;
        }

    }

    public Call retrieve(String id) {
        return null;
    }

    public Call put(String id, Map<String, String> fields) {
        return null;
    }

    public Call update(String id, Map<String, String> fields) {
        return null;
    }

    public Call delete(String[] id) {
        return null;
    }

    public Call delete(String id) {
        return null;
    }
}
