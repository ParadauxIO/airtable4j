package io.paradaux.airtable4j.http;

import okhttp3.MediaType;

public enum ContentType {

    JSON(MediaType.get("application/json; charset=utf-8"));

    private final MediaType type;

    ContentType(MediaType type) {
        this.type = type;
    }

    public MediaType getType() {
        return type;
    }
}
