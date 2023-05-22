package org.example;

import com.customPackage.mongoConnect;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import static com.customPackage.searchQuery.*;
import static com.customPackage.update.addDoc.addScheme;
import static com.customPackage.update.dltDoc.dltScheme;
import  static  com.customPackage.update.setDoc.setScheme;
import java.util.Scanner;


public class Main {

    static mongoConnect mongoDB = new mongoConnect();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        FindIterable<Document> tempiterate = mongoDB.MahaDBT.find(Filters.eq("Collection","MahaDBT"));
        for (Document result : tempiterate) {
            System.out.println(result.toJson());
        }
        int choice = 1;
        String pause = new String();
        //System.out.println("Press enter to continue ");
        pause = sc.nextLine();
        //System.out.println("continued...");
        //choice = sc.nextInt()

        while (choice != 0){
            System.out.println("Enter 1 to test the functions");
            System.out.println("Enter 2 to add scholarship");
            System.out.println("Enter 3 to update a scholarship");
            System.out.println("Enter 4 to delete a scholarship");
            System.out.println("Enter 5 to search relevant scholarships");
            System.out.println("Enter 0 to exit");
            choice = sc.nextInt();
            System.out.println();

            switch (choice){
                case 1:
                    addScheme(1,mongoDB);
                    pause = sc.nextLine();

                    setScheme(1,mongoDB);
                    pause = sc.nextLine();

                    dltScheme(1,mongoDB);
                    pause = sc.nextLine();

                    System.out.println("Now we will try to search a query ");
                    printResult(mongoDB);
                    break;

                case 2:
                    addScheme(0,mongoDB);
                    break;

                case 3:
                    setScheme(0,mongoDB);
                    break;

                case 4:
                    dltScheme(0,mongoDB);
                    break;

                case 5:
                    printResult(mongoDB);
            }
        }
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