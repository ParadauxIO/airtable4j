# airtable4j

![Version](https://img.shields.io/badge/version-0.1--SNAPSHOT-66b3b3) 

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
buildscript {
    repositories {
        jcenter()
    }

    dependencies {
        classpath "com.github.jengelman.gradle.plugins:shadow:2.0.2"
    }
}
apply plugin: "com.github.johnrengelman.shadow"

repositories {
    maven {
        url = "https://repo.paradaux.io/snapshots"
    }
}

dependencies {
    compile "io.paradaux:airtable4j:0.1-SNAPSHOT"
}
```

## Usage

> This is subject to change.