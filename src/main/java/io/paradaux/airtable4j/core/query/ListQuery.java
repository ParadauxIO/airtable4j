package io.paradaux.airtable4j.core.query;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.paradaux.airtable4j.Airtable4J;
import io.paradaux.airtable4j.core.structure.ACellFormat;
import io.paradaux.airtable4j.core.structure.ATable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListQuery<T> {

    private final HttpUrl.Builder url;
    private final ATable<T> table;

    public ListQuery(ATable<T> table)  {
        this.table = table;
        url = new HttpUrl.Builder()
                .scheme(Airtable4J.API_SCHEME)
                .host(Airtable4J.API_URL)
                .addPathSegment(Airtable4J.API_VERSION)
                .addPathSegment(table.getBase().getBaseID())
                .addPathSegment(table.getName());
    }

    /**
     * Only data for fields whose names are in this list will be included in the result. If you don't need every field, you can use this
     * parameter to reduce the amount of data transferred.
     * */
    public ListQuery<T> field(String field) {
        url.addEncodedQueryParameter("fields[]", field);
        return this;
    }


    /**
     * Only data for fields whose names are in this list will be included in the result. If you don't need every field, you can use this
     * parameter to reduce the amount of data transferred.
     * */
    public ListQuery<T> fields(String[] fields) {
        for (var field : fields)  {
            url.addEncodedQueryParameter("fields%5B%5D", field);
        }

        return this;
    }


    /**
     * A <a href="https://support.airtable.com/hc/en-us/articles/203255215-Formula-Field-Reference">formula</a> used to filter records.
     * The formula will be evaluated for each record, and if the result is not 0, false, "", NaN, [], or #Error! the record will be included
     * in the response
     *
     * If combined with the view parameter, only records in that view which satisfy the formula will be returned.
     * */
    public ListQuery<T> formula(String formula) {
        url.addEncodedQueryParameter("filterByFormula", formula);
        return this;
    }


    /**
     * The maximum total number of records that will be returned in your requests. If this value is larger than pageSize (which is 100 by
     * default), you may have to load multiple pages to reach this total. See the Pagination section below for more.
     * */
    public ListQuery<T> maxRecords(int maxRecords) {
        url.addEncodedQueryParameter("maxRecords", String.valueOf(maxRecords));
        return this;
    }


    /**
     * The number of records returned in each request. Must be less than or equal to 100. Default is 100. See the Pagination section below
     * for more.
     * */
    public ListQuery<T> pageSize(int pageSize) {
        url.addEncodedQueryParameter("pageSize", String.valueOf(pageSize));
        return this;
    }


    /**
     * A list of sort objects that specifies how the records will be ordered. Each sort object must have a field key specifying the name
     * of the field to sort on, and an optional direction key that is either "asc" or "desc". The default direction is "asc".
     *
     * The sort parameter overrides the sorting of the view specified in the view parameter. If neither the sort nor the view parameter
     * is included, the order of records is arbitrary.
     *
     * For example, to sort records by ID in descending order, send these two query parameters: [{"field", "ID", "direction": "desc"}]
     * */
    public ListQuery<T> sort(Map<String, String> sortOptions) {
        throw new RuntimeException("Unimplemented.");
    }


    /**
     * The name or ID of a view in the table. If set, only the records in that view will be returned. The records will be sorted according
     * to the order of the view unless the sort parameter is included, which overrides that order. Fields hidden in this view will be
     * returned in the results. To only return a subset of fields, use the fields parameter.
     * */
    public ListQuery<T> view(String view) {
        url.addEncodedQueryParameter("view", view);
        return this;
    }

    /**
     * The format that should be used for cell values. Supported values are:
     * - ACellFormat.JSON    - cells will be formatted as JSON, depending on the field type.
     * - ACellFormat.STRING  - cells will be formatted as user-facing strings, regardless of the field type.
     *
     * @implNote The String received by Airtable is subject to change and thus wrapped in an Enum to prevent API breakage in later iterations.
     * */
    public ListQuery<T> cellFormat(ACellFormat format) {
        url.addEncodedQueryParameter("cellFormat", format == ACellFormat.JSON ? "json" : "string");
        return this;
    }


    /**
     * The time zone that should be used to format dates when using string as the cellFormat. This parameter is required when using string
     * as the cellFormat.
     *
     * @see <a href="https://support.airtable.com/hc/en-us/articles/216141558-Supported-timezones-for-SET-TIMEZONE">Airtable Supported Timezones Reference Here</a>
     * */
    public ListQuery<T> timeZone(String timeZone) {
        url.addEncodedQueryParameter("timeZone", timeZone);
        return this;
    }


    /**
     * The user locale that should be used to format dates when using string as the cellFormat. This parameter is required when using
     * string as the cellFormat.
     *
     * @see <a href="https://support.airtable.com/hc/en-us/articles/220340268-Supported-locale-modifiers-for-SET-LOCALE">Supported locale modifiers for SET_LOCALE</a>
     * */
    public ListQuery<T> userLocale(String userLocale) {
        url.addEncodedQueryParameter("userLocale", userLocale);
        return this;
    }


    /**
     * Execute the query asynchronously.
     * */
    public Call execute(Callback callback) {

        return null;
    }


    /**
     * Execute the query synchronously.
     * */
    public List<QueryRecord<T>> execute(Class<T> clazz) throws IOException {
        Request request = new Request.Builder()
                .url(url.build())
                .addHeader("Authorization", "Bearer " + table.getAirtable4J().authenticate())
                .build();

        Response response = table.getAirtable4J().client().newCall(request).execute();
        JsonElement element = table.getAirtable4J().gson().fromJson(response.body().string(), JsonElement.class);

        List<QueryRecord<T>> queryRecords = new ArrayList<>();

        JsonArray records = element.getAsJsonObject().getAsJsonArray("records");
        for (JsonElement e : records) {
            JsonObject obj = e.getAsJsonObject();
            queryRecords.add(new QueryRecord<>(table.getAirtable4J().gson().fromJson(obj.getAsJsonObject("fields"), clazz),
                    obj.get("id").getAsString(), obj.get("createdTime").getAsString()));
        }

        return queryRecords;
    }

    public HttpUrl url() {
        return url.build();
    }

}
