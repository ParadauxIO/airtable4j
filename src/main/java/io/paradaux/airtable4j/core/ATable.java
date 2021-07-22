package io.paradaux.airtable4j.core;

import io.paradaux.airtable4j.Airtable4J;
import io.paradaux.airtable4j.http.ContentType;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.HashMap;
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
    }

    public Call create(List<Map<String, Map<String, String>>> records, Callback callback) {
        Map<String, List<Map<String, Map<String, String>>>> data = new HashMap<>();
        data.put("records", records);
        String json = airtable4J.gson().toJson(data);
        RequestBody body = RequestBody.create(ContentType.JSON.getType(), json);
        System.out.println("request: " + json);
        System.out.println("Authorization" + " " + "Bearer " + airtable4J.authenticate());

        Request request = new Request.Builder()
                .url(base.url() + name)
                .addHeader("Authorization", "Bearer " + airtable4J.authenticate())
                .post(body)
                .build();

        System.out.println(base.url() + name);

        Call call = airtable4J.client().newCall(request);
        call.enqueue(callback);
        return call;
    }
}