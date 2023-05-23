package com.customPackage;

import java.util.Scanner;
import org.bson.Document;

import com.mongodb.client.*;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Filters;

import java.util.List;

public class searchQuery {
    //Establishing connection with mongoDB
    //static mongoConnect mongoDB = new mongoConnect();

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
    public static AggregateIterable<Document> searchQuery(String []param,mongoConnect mongoDB) {
        //System.out.println("religion "+ param[0]);
        //System.out.println("category "+ param[1]);// 250000
        int num = Integer.parseInt(param[2]);
        //System.out.println("integer "+ num);

//        FindIterable<Document> iterable = mongoDB.MahaDBT.find(Filters.gte("Scholarships.Category.Income.Amount",800000));
//        for (Document result : iterable){
//            System.out.println(result.toJson());
//        }
        //Document updatedoc = new Document().append("Department","my").append("Eligibility","yess");


        AggregateIterable<Document> aggregateIterable = mongoDB.MahaDBT.aggregate(
                List.of(
                        //Aggregates.unwind("$Scholarships"),
                        Aggregates.unwind("$Category"),
                        Aggregates.match(Filters.and(
                                Filters.in("Category.Name",param[1] )//param[1] "General"
                        )),
                        Aggregates.unwind("$Category.Income"),
                        Aggregates.match(
                                Filters.gte("Category.Income.Amount",num)//Integer.parseInt(param[2]) 800000
                        ),
                        Aggregates.unwind("$Category.Income.Religion"),
                        Aggregates.match(
                                Filters.in("Category.Income.Religion.Name", param[0])//param[0] "Hindu"
                        ),
                        //Aggregates.u("$Scholarships.Category.Income.Religion.Scheme",updatedoc),
                        Aggregates.project(Projections.fields(
                                Projections.include("Name",
                                        "ApplyLink",
                                        "Category.Income.Religion.Schemes"))),
                        Aggregates.unwind("$Category.Income.Religion.Schemes")

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

    public static String[][] printResultWeb(mongoConnect mogoDB, String []webparam)
    {
        AggregateIterable<Document> aggregateIterable = searchQuery(webparam,mogoDB);
        System.out.println("Search operation carride out");
       // String []outputResults;
        String [][]result = new String[100][5];
        int count = 0;

        String []element = null;
        for (Document document : aggregateIterable) {
            String scholarshipName = document.getString("Name");

            String applyLink = document.getString("ApplyLink");
            String department = document
                    .get("Category", Document.class)
                    .get("Income", Document.class)
                    .get("Religion", Document.class)
                    .get("Schemes", Document.class)
                    .getString("Department");
            String link = document
                    .get("Category", Document.class)
                    .get("Income", Document.class)
                    .get("Religion", Document.class)
                    .get("Schemes", Document.class)
                    .getString("Eligibility");
            int ApplicationFees = document //.get("Scholarships",Document.class)
                    .get("Category", Document.class)
                    .get("Income", Document.class)
                    .get("Religion", Document.class)
                    .get("Schemes", Document.class)
                    .getInteger("ApplicationFees");
            System.out.println("Scholarship Name : "+ scholarshipName);
            System.out.println("Apply Link : "+ applyLink);
            System.out.println("Department Name : "+ department);
            System.out.println("Eligibility link : "+ link);
            System.out.println("Application Fees : "+ ApplicationFees);
            System.out.println(" ");


            result[count + 1][0] = scholarshipName;
            result[count + 1][1] = applyLink;
            result[count + 1][2] = department;
            result[count + 1][3] = link;
            result[count + 1][4] = Integer.toString(ApplicationFees);

            count++;

            element = new String[]{scholarshipName, applyLink, department, link};

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

        result[0][0] = Integer.toString(count);

//        System.out.println();
//        System.out.println(result[0][0]);
//        for(int i = 1;i<=count;i++){
//            System.out.println(result[i][0]);
//            System.out.println(result[i][2]);
//        }


        return result;
    }

    public static void printResult(mongoConnect mogoDB)
    {
        AggregateIterable<Document> aggregateIterable = searchQuery(getQuery(),mogoDB);
        System.out.println("Search operation carride out");
        for (Document document : aggregateIterable) {
            String scholarshipName = document.getString("Name");

            String applyLink = document.getString("ApplyLink");
            String department = document
                    .get("Category", Document.class)
                    .get("Income", Document.class)
                    .get("Religion", Document.class)
                    .get("Schemes", Document.class)
                    .getString("Department");
            String link = document
                    .get("Category", Document.class)
                    .get("Income", Document.class)
                    .get("Religion", Document.class)
                    .get("Schemes", Document.class)
                    .getString("Eligibility");
            int ApplicationFees = document //.get("Scholarships",Document.class)
                    .get("Category", Document.class)
                    .get("Income", Document.class)
                    .get("Religion", Document.class)
                    .get("Schemes", Document.class)
                    .getInteger("ApplicationFees");

            System.out.println("Scholarship Name : "+ scholarshipName);
            System.out.println("Apply Link : "+ applyLink);
            System.out.println("Department Name : "+ department);
            System.out.println("Eligibility link : "+ link);
            System.out.println("Applicition Fees : "+ ApplicationFees);
            System.out.println(" ");
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

//             Document result = document.get("Scholarships",Document.class)
//                    .get("Category", Document.class)
//                    .get("Income", Document.class)
//                    .get("Religion", Document.class);
//                    //.get("Schemes", Document.class);


//Arrays sresult = (Arrays) result;
//String scholarshipName = result.getString("ScholarshipsName");

//            for (Document scheme : sresult){
//                    String department = scheme.getString("Department");
//                    String link = scheme.getString("Eligibility");
//                    int ApplicationFees = scheme.getInteger("ApplicationFees");
//                System.out.println("Department " + department);
//                System.out.println("Eligibility " + link);
//                System.out.println("ApplicationFees" + ApplicationFees);
//            }

//System.out.println(document.toJson());
