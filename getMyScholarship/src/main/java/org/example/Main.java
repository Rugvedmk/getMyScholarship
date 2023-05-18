package org.example;

import com.customPackage.mongoConnect;
//import com.customPackage.searchQuery.printResult;

import static com.customPackage.searchQuery.printResult;
//import customPackage;

import static com.customPackage.update.addDoc.updateScheme;


import static com.customPackage.insertQuery.insert;

public class Main {

    mongoConnect m = new mongoConnect();


    public static void main(String[] args) {
        //System.out.printf("Hello and welcome!");
        insert(); //creates the database in the device, if already exist does not
        printResult();
        updateScheme();
    }
}

/*
package customPackage;

import com.mongodb.client.*;
import org.bson.Document;


public class connectMongoDB {


        public String URI;
    MongoDatabase scholarship;
    MongoCollection<Document> MahaDBT;


    connectMongoDB(){
            URI = "mongodb://127.0.0.1:27017";
            MongoClient mongoClient = MongoClients.create(URI);
            scholarship = mongoClient.getDatabase("scholarship");
            MahaDBT = database.getCollection("MahaDBT");
    }
}

*/
