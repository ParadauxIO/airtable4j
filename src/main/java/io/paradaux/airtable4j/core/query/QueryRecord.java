package io.paradaux.airtable4j.core.query;

import java.time.Instant;

public class QueryRecord<T> {

    private final T record;
    private final String id;
    private final Instant createdTime;

    public QueryRecord(T record, String id, String timestamp) {
        this.record = record;
        this.id = id;
        this.createdTime = Instant.parse (timestamp);
    }

    public T getRecord() {
        return record;
    }

    public String getId() {
        return id;
    }

    public Instant getCreatedTime() {
        return createdTime;
    }
}
