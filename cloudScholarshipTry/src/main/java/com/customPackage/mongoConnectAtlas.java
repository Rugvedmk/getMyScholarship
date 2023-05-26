package com.customPackage;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.Document;

public class mongoConnectAtlas{
    public MongoDatabase scholarship;
    public MongoCollection<Document> MahaDBT;
    public mongoConnectAtlas() {
        String connectionString = "mongodb+srv://ompatil07:TaLCKLU60aQCnkNk@scholarshipcluster.6fwqmwr.mongodb.net/?retryWrites=true&w=majority";
        String connectionStringNew = "mongodb+srv://ompatil07:TaLCKLU60aQCnkNk@scholarshipcluster.6fwqmwr.mongodb.net/?retryWrites=true&w=majority";
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionStringNew))
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        scholarship = mongoClient.getDatabase("scholarshipDB");
        MahaDBT  = scholarship.getCollection("MahaDBT");



        // Create a new client and connect to the server
       // try (MongoClient mongoClient = MongoClients.create(settings)) {
      //      try {
                // Send a ping to confirm a successful connection
//                MongoDatabase database = mongoClient.getDatabase("admin");
//                database.runCommand(new Document("ping", 1));
             //   MongoClient localMongoClient = MongoClients.create("mongodb://127.0.0.1:27017");
             //   MongoDatabase localDatabase = localMongoClient.getDatabase("scholarshipDB");
//
//        // Access the collections
      //  scholarship = mongoClient.getDatabase("scholarshipDB");
      //  MahaDBT = scholarship.getCollection("MahaDBT");
//
//        // Copy data from local database to MongoDB Atlas
      //  FindIterable<Document> documents = localDatabase.getCollection("MahaDBT").find();
      //  for (Document document : documents) {
      //      MahaDBT.insertOne(document);
     //   }
            System.out.println("Data migration from local database to MongoDB Atlas completed successfully.");

          //      System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
          //  } catch (Exception e) {
          //      e.printStackTrace();
          //  }
        }
    }





//package com.customPackage;
//
//import com.mongodb.MongoClientSettings;
//import com.mongodb.MongoCredential;
//import com.mongodb.ServerAddress;
//import com.mongodb.client.FindIterable;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import org.bson.Document;
//
//import java.util.Collections;
//
//public class mongoConnectAtlas {

//
//    public mongoConnectAtlas() {
//        // Replace <password> with your actual password
//        char[] password = "TaLCKLU60aQCnkNk".toCharArray();
//
//        // Create the MongoDB credentials
//        MongoCredential credential = MongoCredential.createCredential("ompatil07", "admin", password);
//
//        // Create the server address
//        ServerAddress serverAddress = new ServerAddress("scholarshipcluster.6fwqmwr.mongodb.net", 27017);
//
//        // Create the MongoDB client settings
//        MongoClientSettings settings = MongoClientSettings.builder()
//                .credential(credential)
//                .applyToClusterSettings(builder ->
//                        builder.hosts(Collections.singletonList(serverAddress)))
//                .build();
//
//        // Create the MongoDB client
//        MongoClient mongoClient = MongoClients.create(settings);
//
//        // Access the local database
//        MongoClient localMongoClient = MongoClients.create("mongodb://127.0.0.1:27017");
//        MongoDatabase localDatabase = localMongoClient.getDatabase("scholarshipDB");
//
//        // Access the collections
//        scholarship = mongoClient.getDatabase("scholarshipDB");
//        MahaDBT = scholarship.getCollection("MahaDBT");
//
//        // Copy data from local database to MongoDB Atlas
//        FindIterable<Document> documents = localDatabase.getCollection("MahaDBT").find();
//        for (Document document : documents) {
//            MahaDBT.insertOne(document);
//        }
//        System.out.println("Data migration from local database to MongoDB Atlas completed successfully.");
//
//    }
//
//}
