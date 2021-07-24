package io.paradaux.airtable4j;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.paradaux.airtable4j.auth.AAuthentication;
import io.paradaux.airtable4j.auth.AAuthenticationType;
import io.paradaux.airtable4j.core.structure.ABase;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Airtable4J {

    private static final Logger logger = LoggerFactory.getLogger(Airtable4J.class);
    private final OkHttpClient client = new OkHttpClient();

    private final AAuthentication authentication;
    private final Gson gson;


    public Airtable4J(String key) {
        this.authentication = new AAuthentication(key);
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public Airtable4J(AAuthenticationType type) {
        switch (type) {
            case ENVOIRNMENT:
                this.authentication = new AAuthentication(System.getenv("airtable-api-key"));
                break;

            case CONFIGURATION:
                this.authentication = new AAuthentication(System.getProperty("airtable-api-key"));
                break;

            default:
                this.authentication = null;
                break;
        }
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public ABase base(String baseName) {
        return new ABase(baseName, this);
    }

    public String authenticate() {
        return authentication.getKey();
    }

    public Gson gson() {
        return gson;
    }

    public OkHttpClient client() {
        return client;
    }
}
