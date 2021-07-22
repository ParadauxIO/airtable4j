package io.paradaux.airtable4j;

import io.paradaux.airtable4j.core.AAuthentication;
import io.paradaux.airtable4j.core.AAuthenticationType;
import io.paradaux.airtable4j.core.ABase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Airtable4J {

    private static final Logger logger = LoggerFactory.getLogger(Airtable4J.class);

    private final AAuthentication authentication;

    public Airtable4J(String key) {
        this.authentication = new AAuthentication(key);
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
    }

    public ABase base(String baseName) {
        return new ABase(baseName, this);
    }
}
