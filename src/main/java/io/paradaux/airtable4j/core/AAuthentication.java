package io.paradaux.airtable4j.core;

public class AAuthentication {

    private final String key;

    public AAuthentication(String KEY) {
        this.key = KEY;
    }

    public String getKey() {
        return key;
    }

    public String getHeader() {
        return "Authorization: Bearer " + key;
    }
}
