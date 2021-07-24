import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.paradaux.airtable4j.Airtable4J;
import io.paradaux.airtable4j.core.structure.ABase;
import object.Post;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CreateRecordTest {

    private static final Logger logger = LoggerFactory.getLogger(CreateRecordTest.class);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private final String API_KEY = System.getenv("airtable-api-key");
    private final String BASE_ID = System.getenv("airtable-base-id");

    @Test
    public void createRecordTest() throws InterruptedException {
        if (API_KEY == null || BASE_ID == null) return;

        Airtable4J airtable4J = new Airtable4J(API_KEY);
        ABase base = airtable4J.base(BASE_ID);

        List<Post> posts = new ArrayList<>();
        posts.add(new Post("345345", "https://example.org", "Submitted", "wefweffwe"));
        posts.add(new Post("343455345", "https://example.org/ergerg", "Submitted", "wefwergergeffwe"));

        base.table("Queue").create(posts, new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                System.out.println("Response Code: " + response.code());
                assertEquals(200, response.code());
                System.out.println("response: " + response.body().string());
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("executed: " + call.isExecuted());
                System.out.println("error: " + e.getMessage());
                fail("Failed to add element to Queue: " + e.getMessage());
            }
        });

        Thread.sleep(5000);
    }

}
