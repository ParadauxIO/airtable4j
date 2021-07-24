import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.paradaux.airtable4j.Airtable4J;
import object.Post;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CreateRecordTest {

    private static final Logger logger = LoggerFactory.getLogger(CreateRecordTest.class);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private final String API_KEY = System.getenv("airtable-api-key");
    private final String BASE_ID = System.getenv("airtable-base-id");

    @Test
    public void temp() {
        Airtable4J airtable4J = new Airtable4J(API_KEY);

        List<Post> posts = new ArrayList<>();
        posts.add(new Post("345345", "https://example.org", "Submitted", "wefweffwe"));
        posts.add(new Post("343455345", "https://example.org/ergerg", "Submitted", "wefwergergeffwe"));

        JsonArray fields = new JsonArray();
        for (Object o : posts) {
            JsonObject record = new JsonObject();
            record.add("fields", gson.toJsonTree(o));
            fields.add(record);
        }

        JsonObject records = new JsonObject();
        records.add("records", fields);

        System.out.println(gson.toJson(records));
    }


//    @Test
//    public void createRecordTest() throws InterruptedException {
//        Airtable4J airtable4J = new Airtable4J(API_KEY);
//        ABase base = airtable4J.base(BASE_ID);
//
//        Map<String, Map<String, String>> fieldsObject = new HashMap<>();
//        List<Map<String, Map<String, String>>> listOfFields = new ArrayList<>();
//
//        List<Post> posts = new ArrayList<>();
//
//
//        HashMap<String, String> fields = new HashMap<>();
//        fields.put("ID", "69");
//        fields.put("Link", "https://example.org");
//        fields.put("Status", "Submitted");
//        fields.put("Post", "This is a test.");
//
//        fieldsObject.put("fields", fields);
//
//        listOfFields.add(fieldsObject);
//
//        System.out.println(API_KEY + ":" + BASE_ID);
//
//        Call call = base.table("Queue").create(listOfFields, new Callback() {
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                System.out.println("executed: " + call.isExecuted());
//                System.out.println(call.request().url());
//                System.out.println();
//            }
//
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                System.out.println("executed: " + call.isExecuted());
//                System.out.println(call.request().url());
//                System.out.println("response: " + response.body().string());
//            }
//        });
//
//        System.out.println(call.isExecuted());
//        Thread.sleep(5000);
//    }

}
