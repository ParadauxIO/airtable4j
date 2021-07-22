package io.paradaux.airtable4j.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

public class ARecord {

    private static final Logger logger = LoggerFactory.getLogger(ARecord.class);

    private final String airtableId;
    private final Map<String, String> fields;
    private final Date createdTime;

    public ARecord(String airtableId, Map<String, String> fields, Date createdTime) {
        this.airtableId = airtableId;
        this.fields = fields;
        this.createdTime = createdTime;
    }

    public String getAirtableId() {
        return airtableId;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public Date getCreatedTime() {
        return createdTime;
    }
}
