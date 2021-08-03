# airtable4j

![Version](https://img.shields.io/badge/version-0.1--SNAPSHOT-66b3b3)
[![CodeFactor](https://www.codefactor.io/repository/github/paradauxio/airtable4j/badge)](https://www.codefactor.io/repository/github/paradauxio/airtable4j)

> Work in progress. 

> This project is incomplete, drastic API changes are likely, and support will be limited for early adopters. Please keep this in mind. Try [airtable.java](https://github.com/Sybit-Education/airtable.java) in the meantime.

An Airtable Java Driver based on okhttp and Gson, inspired by [airtable.java](https://github.com/Sybit-Education/airtable.java)

## Maven 
```xml
<repositories>
    <repository>
        <id>paradaux</id>
        <url>https://repo.paradaux.io/snapshots</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>io.paradaux</groupId>
        <artifactId>airtable4j</artifactId>
        <version>0.1-SNAPSHOT</version>
    </dependency>
</dependencies>
```

## Gradle 

```gradle
plugins {
  id "com.github.johnrengelman.shadow" version "6.1.4"
}

repositories {
    maven {
        url = "https://repo.paradaux.io/snapshots"
    }
}

dependencies {
    implementation "io.paradaux:airtable4j:0.1-SNAPSHOT"
}
```

## Usage

> This is subject to change.

> This uses a fictional GSON-serializable POJO Post for the purposes of an example type parameter.

### Creating an Instance of Airtable4j
```java
Airtable4J airtable4J = new Airtable4J(API_KEY);
```

### Getting a base
```java
ABase<Post> base = airtable4J.base(BASE_ID);
```

## Getting a table from a base
```java
ATable<Post> table = base.table("Queue")
```

## Creating a record
```java
table.create(new Post("someField", "someOtherField"));
```

## Listing records
As the airtable API provides additional informaiton which each record response (i.e the record ID and creationTime of that record) your POJO is wrapped in a QueryRecord which contains that meta information. You can receive the record itself using `QueryRecord<T>#getRecord`
```java
List<QueryRecord<Post>> posts = table.list()
        .field("ID")
        .maxRecords(5)
        .pageSize(5)
        .cellFormat(ACellFormat.JSON)
        .timeZone("Europe/Dublin")
        .execute(Post.class);
```


> This next section is for functionality which is not yet present in the api
## Retrieving specific records
> Not available

```java
Post post = table.retrieve("rec6ZbJAFCM146e88");
```

## Deleting records
> Not available
```java
table.delete("rec6ZbJAFCM146e88");
```

## Overriding records
This replaces the provided record with the instance of T provided.
> Not available
```java
table.put("rec6ZbJAFCM146e88", new Post("someBetterField", "thisOneisEvenBetter"));
```

## Updating records
You only specify which fields you wish to change. If you want to replace a record using a POJO use the override functionality.

> Not available
```java
table.put("rec6ZbJAFCM146e88", Map<String, String> fields);
```

