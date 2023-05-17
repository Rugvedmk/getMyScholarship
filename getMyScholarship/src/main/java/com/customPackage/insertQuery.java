package com.customPackage;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class insertQuery {
    static mongoConnect mongoDB  = new mongoConnect();
    public static void insert() {
        String connectionString = "mongodb://localhost:27017";
        String databaseName = "scholarshipDB";
        String collectionName = "MahaDBT";
        //Getting the data in from json file to jsonObject
        JSONObject jsonObject = null;
        JSONParser jsonparser = new JSONParser();
        try {
            jsonObject = (JSONObject) jsonparser.parse(new FileReader("MahaDBT.json"));
        } catch (ParseException | IOException e) {
            System.out.println("Failed to parse");
            System.out.println(e);
            throw new RuntimeException(e);
        }

        String jsonString = jsonObject.toJSONString(); //whole data of file as a string

        try (MongoClient mongoClient = new MongoClient(new MongoClientURI(connectionString))) {
            MongoDatabase database = mongoClient.getDatabase(databaseName);
            MongoCollection<Document> collection = ((MongoDatabase) database).getCollection(collectionName);

            boolean databaseExists = mongoClient.listDatabaseNames().into(new ArrayList<>()).contains(databaseName); //checks if database already exists
            if (databaseExists) {
                System.out.println("Database already exists.");
            } else {
                Document document = Document.parse(jsonString);
                collection.insertOne(document); //inserting in database
                System.out.println("JSON object stored in the database.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
