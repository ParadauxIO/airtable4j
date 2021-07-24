package object;

import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("ID")
    private final String id;

    @SerializedName("Link")
    private final String link;

    @SerializedName("Status")
    private final String status;

    @SerializedName("Post")
    private final String post;

    public Post(String id, String link, String status, String post) {
        this.id = id;
        this.link = link;
        this.status = status;
        this.post = post;
    }

    public String getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public String getStatus() {
        return status;
    }

    public String getPost() {
        return post;
    }
}
