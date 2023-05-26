package com.customPackage;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Collections;

public class mongoConnect {
    public String URI;
    MongoDatabase scholarship;
    public MongoCollection<Document> MahaDBT;


    public mongoConnect(){
        URI = "mongodb://127.0.0.1:27017";
        MongoClient mongoClient = MongoClients.create(URI);
        scholarship = mongoClient.getDatabase("scholarshipDB");
        //MahaDBT = scholarship.getCollection("MahaDBT");
        MahaDBT = scholarship.getCollection("MahaDBT");

        //System.out.println("Connection Established");

    }
}
