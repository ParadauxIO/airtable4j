package io.paradaux.airtable4j.core.structure;

import io.paradaux.airtable4j.Airtable4J;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ABase {

    private static final Logger logger = LoggerFactory.getLogger(ABase.class);
    private static final String BASE_URL_ENDPOINT = Airtable4J.getAPILink() + "/%s/";

    private final String baseID;
    private final Airtable4J airtable;

    public ABase(String baseID, Airtable4J airtable) {
        this.baseID = baseID;
        this.airtable = airtable;
    }

    public <T> ATable<T> table(String name) {
        return new ATable<>(name, airtable, this);
    }

    public String getBaseID() {
        return baseID;
    }

    public Airtable4J getAirtable() {
        return airtable;
    }
}
