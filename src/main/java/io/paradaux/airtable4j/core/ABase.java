package io.paradaux.airtable4j.core;

import io.paradaux.airtable4j.Airtable4J;

public class ABase {

    private final String baseName;
    private final Airtable4J airtable;

    public ABase(String baseName, Airtable4J airtable) {
        this.baseName = baseName;
        this.airtable = airtable;
    }
}
