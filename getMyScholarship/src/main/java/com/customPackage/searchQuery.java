package com.customPackage;

import java.util.Arrays;
import java.util.ListIterator;
import java.util.Scanner;
import org.bson.Document;

import com.mongodb.client.*;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Projections.computed;
import org.json.simple.JSONObject;

import java.util.List;

public class searchQuery {
    //Establishing connection with mongoDB
    static mongoConnect mongoDB = new mongoConnect();

    //Function to get the query from the user
    public static String[] getQuery(){
        String []religion = {"Hindu", "Buddhist", "Muslim", "Jain", "Christan", "Parsi", "Sikh", "Jews"};
        String []category = {"General","SC","OBC","VJNT","ST","SBC","SEBC"};
        int []income = {250000,800000};
        System.out.print("Select Religion by pressing the number : ");
        for(int i = 0;i<religion.length;i++){
            System.out.print(i + 1 + "." + religion[i] + " ");
        }
        System.out.println(" ");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Number : ");
        int choice = sc.nextInt();
        System.out.println(" ");
        String selectedReligion = religion[choice-1];

        System.out.print("Select Category by pressing the number : ");
        for(int i = 0;i<category.length;i++){
            System.out.print(i + 1 + "." + category[i] + " ");
        }
        System.out.println(" ");

        System.out.print("Enter Number : ");
        choice = sc.nextInt();
        System.out.println(" ");
        String selectedCategory = category[choice-1];


        System.out.print("Select Income by pressing the number : ");
        for(int i = 0;i<income.length;i++){
            System.out.print(i + 1 + ") Below " + income[i] + "     ");
        }
        System.out.println(" ");
        System.out.print("Enter Number : ");
        choice = sc.nextInt();
        System.out.println(" ");
        String selectedIncome = Integer.toString(income[choice-1]);

        String []choiceTemplate = {selectedReligion,selectedCategory,selectedIncome};

        return choiceTemplate;
    }

    //Function to search the query from the dataBase
    public static AggregateIterable<Document> searchQuery(String []param) {
        System.out.println("religion "+ param[0]);
        System.out.println("category "+ param[1]);// 250000
        int num = Integer.parseInt(param[2]);
        System.out.println("integer "+ num);

//        FindIterable<Document> iterable = mongoDB.MahaDBT.find(Filters.gte("Scholarships.Category.Income.Amount",800000));
//        for (Document result : iterable){
//            System.out.println(result.toJson());
//        }


        AggregateIterable<Document> aggregateIterable = mongoDB.MahaDBT.aggregate(
                List.of(
                        Aggregates.unwind("$Scholarships"),
                        Aggregates.unwind("$Scholarships.Category"),
                        Aggregates.match(Filters.and(
                                Filters.in("Scholarships.Category.Name",param[1] )//param[1] "General"
                        )),
                        Aggregates.unwind("$Scholarships.Category.Income"),
                        Aggregates.match(
                                Filters.gte("Scholarships.Category.Income.Amount",num)//Integer.parseInt(param[2]) 800000
                        ),
                        Aggregates.unwind("$Scholarships.Category.Income.Religion"),
                        Aggregates.match(
                                Filters.in("Scholarships.Category.Income.Religion.Name", param[0])//param[0] "Hindu"
                        ),
                        Aggregates.project(Projections.fields(
                                Projections.include("Scholarships.Name",
                                        "Scholarships.ApplyLink",
                                        "Scholarships.Category.Income.Religion.Schemes"))),
                        Aggregates.unwind("$Scholarships.Category.Income.Religion.Schemes")

//                        Aggregates.project(
//                                computed("scholarshipName","$Scholarships.Name")
//                                computed("applyLink","$Scholarships.ApplyLink"),
//                                computed("schemes","$Scholarships.Category.Income.Religion.Schemes")
//                                )
                ));

//        AggregateIterable<Document> aggregateIterable = mongoDB.MahaDBT.aggregate(
//                List.of(
//                        Aggregates.unwind("$Scholarships")));
                return aggregateIterable;

    }

    public static void printResult()
    {
        AggregateIterable<Document> aggregateIterable = searchQuery(getQuery());
        System.out.println("Search operation carride out");
        for (Document document : aggregateIterable) {
            String scholarshipName = document.get("Scholarships",Document.class).getString("Name");
            String applyLink = document.get("Scholarships",Document.class).getString("ApplyLink");
            String department = document.get("Scholarships",Document.class)
                    .get("Category", Document.class)
                    .get("Income", Document.class)
                    .get("Religion", Document.class)
                    .get("Schemes", Document.class)
                    .getString("Department");
            String link = document.get("Scholarships",Document.class)
                    .get("Category", Document.class)
                    .get("Income", Document.class)
                    .get("Religion", Document.class)
                    .get("Schemes", Document.class)
                    .getString("Eligibility");
            int ApplicationFees = document.get("Scholarships",Document.class)
                    .get("Category", Document.class)
                    .get("Income", Document.class)
                    .get("Religion", Document.class)
                    .get("Schemes", Document.class)
                    .getInteger("ApplicationFees");
            //             Document result = document.get("Scholarships",Document.class)
//                    .get("Category", Document.class)
//                    .get("Income", Document.class)
//                    .get("Religion", Document.class);
//                    //.get("Schemes", Document.class);


            //Arrays sresult = (Arrays) result;
            //String scholarshipName = result.getString("ScholarshipsName");
            System.out.println("Scholarship Name : "+ scholarshipName);
            System.out.println("Apply Link : "+ applyLink);
            System.out.println("Department Name : "+ department);
            System.out.println("Eligibility link : "+ link);
            System.out.println("Applicition Fees : "+ ApplicationFees);
            System.out.println(" ");
//            for (Document scheme : sresult){
//                    String department = scheme.getString("Department");
//                    String link = scheme.getString("Eligibility");
//                    int ApplicationFees = scheme.getInteger("ApplicationFees");
//                System.out.println("Department " + department);
//                System.out.println("Eligibility " + link);
//                System.out.println("ApplicationFees" + ApplicationFees);
//            }

            //System.out.println(document.toJson());
        }
    }


}


//import static com.mongodb.client.model.Aggregates.*;
//        import static com.mongodb.client.model.Filters.eq;
//        import static com.mongodb.client.model.Projections.computed;
//        import static java.util.Arrays.*;
//        import static com.mongodb.client.model.Projections.include;
//
//        MongoClient mc = new MongoClient();
//        MongoDatabase db = mc.getDatabase("test");
//        MongoCollection<Document> collection = db.getCollection("collection");
//        Document document =
//        collection.aggregate(asList(
//        match(eq("day",17)),
//        project(computed("val", "$model1.MondayModel.gtxdotdot.xdotdot")))).
//        first();
//        Double embeddedField = document.getDouble("val");
//        Using Distinct
//
//        Double embeddedField = collection.distinct("model1.MondayModel.gtxdotdot.xdotdot", eq("day",17), Double.class).first();
//        Using Find
//
//        Document document = collection.find(eq("day",17)).projection(include("model1.MondayModel.gtxdotdot.xdotdot")).first();
//        Double embeddedField = document.get("model1", Document.class).get("MondayModel", Document.class).get("gtxdotdot", Document.class).getDouble("xdotdot")
