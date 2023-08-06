package com.customPackage.update;

import com.customPackage.mongoConnect;
import com.customPackage.mongoConnectAtlas;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import com.mongodb.client.model.Updates;
import com.mongodb.client.model.UpdateOptions;

import static com.customPackage.searchQuery.getQuery;
import org.bson.conversions.Bson;

import java.util.Scanner;

import java.util.List;


public class addDoc {

    //static mongoConnect mongoDB = new mongoConnect();

    public static void update(){
        /*
        Document updatedoc = new Document("Myincome",100);//.append("Eligibility","yess");
        Bson query = Filters.and(
                Filters.eq("Scholarships.Name","Rajarshi Chhatrapati Shahu Maharaj Scholarship scheme"),
                Filters.eq("Scholarships.Category.Name","SC")
        );

        UpdateOptions options = new UpdateOptions()
                .arrayFilters(List.of(Filters.eq("elem1.Name", "VJNT"),
                        Filters.eq("elem2._incomeid",new ObjectId("64652c024d833a0ec838378b"))));
        Bson update = Updates.push("Scholarships.$[].Category.$[elem1].Income.$[elem2].Religion.$[].Schemes", updatedoc);

        UpdateResult updateResult = mongoDB.MahaDBT.updateOne(query,update, options);
        System.out.println(updateResult);
         */
    }

    public static void addScheme(int test,mongoConnectAtlas mongoDB){
        String scholarshipName,Category,Religion,DepartmentName,EligibilityLink;
        int ApplicationFees,Amount;
        String pause;
        Scanner sc = new Scanner(System.in);
        if (test == 1){
            scholarshipName = "Rajarshi Chhatrapati Shahu Maharaj Scholarship scheme";
            //String []param = getQuery();
            Category = "SC";//param[1];
            Religion = "Hindu";//param[0];
            Amount = 800000;//Integer.parseInt(param[2]);

            System.out.println("First we will add A document with following filters");
            System.out.println("scholarship Name : " + scholarshipName);
            System.out.println("Category : " + Category);
            System.out.println("Religion : " + Religion);
            System.out.println("Amount : " + Amount);
            pause = sc.nextLine();
            //System.out.println();

            DepartmentName = "ABC";
            EligibilityLink = "BCD";
            ApplicationFees = 0;

            System.out.println("Document details : ");
            System.out.println("Department Name : " + DepartmentName);
            System.out.println("Eligibility Link : "+ EligibilityLink);
            System.out.println("Application Fees : " + ApplicationFees);
            pause = sc.nextLine();
            //System.out.println();
        } else {
                    System.out.print("Enter Scholarship Name : ");
                    scholarshipName = sc.nextLine();
                    String []param = getQuery();
                    Category = param[1];
                    Religion = param[0];
                    Amount = Integer.parseInt(param[2]);

                    System.out.print("Enter department Name : ");
                    DepartmentName = sc.nextLine();
                    System.out.print("Enter Eligibility Link : ");
                    EligibilityLink = sc.nextLine();
                    System.out.print("Enter Application Fees : ");
                    ApplicationFees = sc.nextInt();
                    System.out.println();

        }


        Document addDoc = new Document("Department",DepartmentName)
                .append("Eligibility",EligibilityLink)
                .append("ApplicationFees",ApplicationFees);

        Bson query = Filters.and(
                Filters.eq("Name",scholarshipName)//"Rajarshi Chhatrapati Shahu Maharaj Scholarship scheme"
        );

        Bson update = Updates.push("Category.$[catObj].Income.$[incObj].Religion.$[relObj].Schemes", addDoc);


        UpdateOptions options = new UpdateOptions().arrayFilters(
                List.of(
                        //Filters.eq("schObj.Name",scholarshipName),
                        Filters.gte("incObj.Amount",Amount),
                        Filters.in("relObj.Name",Religion),
                        Filters.in("catObj.Name",Category)
                        )
        );

        UpdateResult updateResult = mongoDB.MahaDBT.updateOne(query,update, options);
        System.out.println(updateResult + "\n");


    }

    public  static void addSchemeWeb(mongoConnectAtlas mongoDB, String []param) {
        String scholarshipName, Category, Religion, DepartmentName, EligibilityLink;
        int ApplicationFees, Amount;
        String pause;
        Scanner sc = new Scanner(System.in);
        scholarshipName = param[0];//"Rajarshi Chhatrapati Shahu Maharaj Scholarship scheme";
        //String []param = getQuery();
        Category = param[1];//"SC";//param[1];
        Religion = param[2];//"Hindu";//param[0];
        Amount = Integer.parseInt(param[3]);//800000;//Integer.parseInt(param[2]);

        DepartmentName = param[4];
        EligibilityLink = param[5];
        ApplicationFees = Integer.parseInt(param[6]);

        Document addDoc = new Document("Department",DepartmentName)
                .append("Eligibility",EligibilityLink)
                .append("ApplicationFees",ApplicationFees);

        Bson query = Filters.and(
                Filters.eq("Name",scholarshipName)//"Rajarshi Chhatrapati Shahu Maharaj Scholarship scheme"
        );

        Bson update = Updates.push("Category.$[catObj].Income.$[incObj].Religion.$[relObj].Schemes", addDoc);


        UpdateOptions options = new UpdateOptions().arrayFilters(
                List.of(
                        //Filters.eq("schObj.Name",scholarshipName),
                        Filters.gte("incObj.Amount",Amount),
                        Filters.in("relObj.Name",Religion),
                        Filters.in("catObj.Name",Category)
                )
        );

        UpdateResult updateResult = mongoDB.MahaDBT.updateOne(query,update, options);
        System.out.println(updateResult + "\n");


    }

}


//        System.out.print("Enter Scholarship Name : ");
//        scholarshipName = sc.nextLine();
//        scholarshipName = "Rajarshi Chhatrapati Shahu Maharaj Scholarship scheme";
//        String []param = getQuery();
//        Category = param[1];
//        Religion = param[0];
//        int Amount = Integer.parseInt(param[2]);

//        Category = sc.nextLine();
//        Religion = sc.nextLine();

//        System.out.print("Enter department Name : ");
//        DepartmentName = sc.nextLine();
//        System.out.print("Enter Eligibility Link : ");
//        EligibilityLink = sc.nextLine();
//        System.out.print("Enter Application Fees : ");
//        ApplicationFees = sc.nextLine();

//            DepartmentName = "ABC";
//            EligibilityLink = "BCD";
//            ApplicationFees = 0;

//        DepartmentName = "Social Justice and Special Assistance Department";
//        EligibilityLink = "https://mahadbt.maharashtra.gov.in/FindEligibleSchemes/SchemeData?SchemeId=4&str=F082036ACD839E206B2D3EA9141004DD28E46F9BDE4EB774";
//        ApplicationFees = 0;
