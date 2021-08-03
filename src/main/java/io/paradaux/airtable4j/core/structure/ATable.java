package io.paradaux.airtable4j.core.structure;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.paradaux.airtable4j.Airtable4J;
import io.paradaux.airtable4j.core.query.ListQuery;
import io.paradaux.airtable4j.http.ContentType;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ATable<T> {

    private final String name;
    private final Airtable4J airtable4J;
    private final ABase base;

    /**
     * Represents a table of an arbitrary type.
     * @param name The desired table
     * @param airtable4J The connection to Airtable
     * @param base The base this table belongs to.
     * @see ABase
     * */
    public ATable(String name, Airtable4J airtable4J, ABase base) {
        this.name = name;
        this.airtable4J = airtable4J;
        this.base = base;

        JsonObject obj = new JsonObject();
    }


    /**
     * Creates a record within the table from the provided object. The object will be serialised by Gson internally.
     * The {@link Callback} is used to allow for asynchronous execution of the request.
     * @param record A Gson-serializable POJO containing the record data.
     * @param callback A callback function containing the HTTP response from Airtable.
     * */
    public Call create(Object record, Callback callback) {
        ArrayList<Object> list = new ArrayList<>();
        list.add(record);
        return create(list, callback);
    }


    /**
     * Creates a record within the table from the provided object. The object will be serialised by Gson internally.
     * The {@link Callback} is used to allow for asynchronous execution of the request.
     * @param record A Gson-serializable POJO containing the record data.
     * @return Response from Okhttp
     * */
    public Response create(Object record) throws IOException {
        ArrayList<Object> list = new ArrayList<>();
        list.add(record);
        return create(list);
    }


    /**
     * Creates records within the table from the provided object. The list of object will be serialised by Gson internally.
     * The {@link Callback} is used to allow for asynchronous execution of the request.
     * @param recordsObj A list of Gson-serializable POJOs containing the record data.
     * @return Response from Okhttp
     * */
    public Response create(List<?> recordsObj) throws IOException {
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
                .url(Airtable4J.getAPILink() + "/" + base.getBaseID() + "/" + name)
                .addHeader("Authorization", "Bearer " + airtable4J.authenticate())
                .post(body)
                .build();

        Call call = airtable4J.client().newCall(request);
        return call.execute();
    }


    /**
     * Creates records within the table from the provided object. The list of object will be serialised by Gson internally.
     * The {@link Callback} is used to allow for asynchronous execution of the request.
     * @param recordsObj A list of Gson-serializable POJOs containing the record data.
     * @param callback A callback function containing the HTTP response from Airtable.
     * */
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
                .url(Airtable4J.getAPILink() + "/" + base.getBaseID() + "/" + name)
                .addHeader("Authorization", "Bearer " + airtable4J.authenticate())
                .post(body)
                .build();

        Call call = airtable4J.client().newCall(request);
        call.enqueue(callback);
        return call;
    }

    public ListQuery<T> list() {
        return new ListQuery<>(this);
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

    public ABase getBase() {
        return base;
    }

    public String getName() {
        return name;
    }

    public Airtable4J getAirtable4J() {
        return airtable4J;
    }
}
