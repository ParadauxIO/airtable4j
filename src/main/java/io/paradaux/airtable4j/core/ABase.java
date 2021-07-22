package io.paradaux.airtable4j.core;

import io.paradaux.airtable4j.Airtable4J;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ABase {

    private static final Logger logger = LoggerFactory.getLogger(ABase.class);
    private static final String BASE_URL_ENDPOINT = "https://api.airtable.com/v0/%s/";

    private final String baseID;
    private final Airtable4J airtable;

    public ABase(String baseID, Airtable4J airtable) {
        this.baseID = baseID;
        this.airtable = airtable;
    }

    public String url() {
        return String.format(BASE_URL_ENDPOINT, baseID);
    }

    public ATable table(String name) {
        return new ATable(name, airtable, this);
    }
}
