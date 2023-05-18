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


public class addDoc {

    static mongoConnect mongoDB = new mongoConnect();

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

    public static void updateScheme(){
        String scholarshipName,Category,Religion,DepartmentName,EligibilityLink;
        int ApplicationFees;

        Scanner sc = new Scanner(System.in);
//        System.out.print("Enter Scholarship Name : ");
//        scholarshipName = sc.nextLine();
        scholarshipName = "Rajarshi Chhatrapati Shahu Maharaj Scholarship scheme";
        String []param = getQuery();
        Category = param[1];
        Religion = param[0];
        int Amount = Integer.parseInt(param[2]);
//        Category = sc.nextLine();
//        Religion = sc.nextLine();

//        System.out.print("Enter department Name : ");
//        DepartmentName = sc.nextLine();
//        System.out.print("Enter Eligibility Link : ");
//        EligibilityLink = sc.nextLine();
//        System.out.print("Enter Application Fees : ");
//        ApplicationFees = sc.nextLine();

            DepartmentName = "ABC";
            EligibilityLink = "BCD";
            ApplicationFees = 0;

        Document addDoc = new Document("Department",DepartmentName)
                .append("Eligibility",EligibilityLink)
                .append("ApplicationFees",ApplicationFees);

        Bson query = Filters.and(
                //Filters.eq("Scholarships.$[schObj].Category.$[catObj].Income.$[incObj].Religion.$[relObj]",Religion)//"Rajarshi Chhatrapati Shahu Maharaj Scholarship scheme"
                Filters.eq("Scholarships.Name","Rajarshi Chhatrapati Shahu Maharaj Scholarship scheme")
                //Filters.eq("Collection","MahaDBT")
                //Filters.eq("Scholarships.Category.Name","SC")
        );

        Bson update = Updates.push("Scholarships.$[schObj].Category.$[catObj].Income.$[incObj].Religion.$[relObj].Schemes", addDoc);


        UpdateOptions options = new UpdateOptions().arrayFilters(
                List.of(
                        Filters.eq("schObj.Name",scholarshipName),
                        Filters.gte("incObj.Amount",Amount),
                        Filters.in("relObj.Name",Religion),
                        Filters.in("catObj.Name",Category)
                        )
        );

        UpdateResult updateResult = mongoDB.MahaDBT.updateOne(query,update, options);
        System.out.println(updateResult);


    }
}
