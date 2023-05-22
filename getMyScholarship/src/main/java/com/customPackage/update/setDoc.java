package com.customPackage.update;

import com.customPackage.mongoConnect;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import com.mongodb.client.model.Updates;
import com.mongodb.client.model.UpdateOptions;

import static com.customPackage.searchQuery.getQuery;
import org.bson.conversions.Bson;

import java.util.Scanner;

import java.util.List;


public class setDoc {
    //static mongoConnect mongoDB = new mongoConnect();
    static Scanner sc = new Scanner(System.in);
    public static void setScheme(int test,mongoConnect mongoDB){
        String scholarshipName,Category,Religion,DepartmentName,EligibilityLink;
        int ApplicationFees,Amount;
        String pause = new String();

        if (test == 1){
            scholarshipName = "Rajarshi Chhatrapati Shahu Maharaj Scholarship scheme";
            //String []param = getQuery();
            Category = "SC";//param[1];
            Religion = "Hindu";//param[0];
            Amount = 800000;//Integer.parseInt(param[2]);

            System.out.println("Now we will update the eligibility scheme in the previously added document based on following filters : ");
            System.out.println("scholarship Name : " + scholarshipName);
            System.out.println("Category : " + Category);
            System.out.println("Religion : " + Religion);
            System.out.println("Amount : " + Amount);
            pause = sc.nextLine();
            //System.out.println();

            DepartmentName = "ABC";
            EligibilityLink = "EFG";
            ApplicationFees = 0;

            System.out.println("Document details : ");
//            System.out.println("Department Name" + DepartmentName);
            System.out.println("Updated Eligibility Link : "+ EligibilityLink);
//            System.out.println("Application Fees" + ApplicationFees);
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
            //System.out.print("Enter Application Fees : ");
            //ApplicationFees = sc.nextInt();
            System.out.println();

        }


//        Document addDoc = new Document("Department",DepartmentName)
//                .append("Eligibility",EligibilityLink)
//                .append("ApplicationFees",ApplicationFees);

        Bson query = Filters.and(
                Filters.eq("Name",scholarshipName)//"Rajarshi Chhatrapati Shahu Maharaj Scholarship scheme"
        );

        Bson update = Updates.set("Category.$[catObj].Income.$[incObj].Religion.$[relObj].Schemes.$[sheObj].Eligibility", EligibilityLink);


        UpdateOptions options = new UpdateOptions().arrayFilters(
                List.of(
                        //Filters.eq("schObj.Name",scholarshipName),
                        Filters.gte("incObj.Amount",Amount),
                        Filters.in("relObj.Name",Religion),
                        Filters.in("catObj.Name",Category),
                        Filters.eq("sheObj.Department",DepartmentName)
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
//        Amount = Integer.parseInt(param[2]);
//        Category = sc.nextLine();
//        Religion = sc.nextLine();

//        System.out.print("Enter department Name : ");
//        DepartmentName = sc.nextLine();
//        System.out.print("Enter Eligibility Link : ");
//        EligibilityLink = sc.nextLine();
//        System.out.print("Enter Application Fees : ");
//        ApplicationFees = sc.nextLine();

//        DepartmentName = "Social Justice and Special Assistance Department";
//        EligibilityLink = "https://mahadbt.maharashtra.gov.in/FindEligibleSchemes/SchemeData?SchemeId=4&str=F082036ACD839E206B2D3EA9141004DD28E46F9BDE4EB774";
//        ApplicationFees = 0;

//        DepartmentName = "ABC";
//                EligibilityLink = "EFG";
//                ApplicationFees = 0;

