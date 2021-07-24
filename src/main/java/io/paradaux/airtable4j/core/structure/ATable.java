package io.paradaux.airtable4j.core.structure;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.paradaux.airtable4j.Airtable4J;
import io.paradaux.airtable4j.http.ContentType;
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

    public Call create(List<Object> recordsObj, Callback callback) {
        JsonArray fields = new JsonArray();

        for (Object o : recordsObj) {
            JsonObject record = new JsonObject();
            record.add("fields", airtable4J.gson().toJsonTree(o));
            fields.add(record);
        }

        JsonObject records = new JsonObject();
        records.add("records", fields);

        RequestBody body = RequestBody.create(ContentType.JSON.getType(), airtable4J.gson().toJson(records));

        Request request = new Request.Builder()
                .url(base.url() + name)
                .addHeader("Authorization", "Bearer " + airtable4J.authenticate())
                .post(body)
                .build();

        Call call = airtable4J.client().newCall(request);
        call.enqueue(callback);
        return call;
    }

    public ListBuilder list() {
        return new ListBuilder();
    }

    public static class ListBuilder {

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
