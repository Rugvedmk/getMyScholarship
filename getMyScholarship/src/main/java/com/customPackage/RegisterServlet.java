package com.customPackage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Arrays;

import static com.customPackage.insertQuery.mongoDB;
import static com.customPackage.searchQuery.*;

public class RegisterServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<h2>Welcome to the Backend Operation</h2>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
        out.println("</head>");
        out.println("<body>");
        String religion = request.getParameter("religion");
        String category = request.getParameter("category");
        String amount = request.getParameter("amount");

        String []choiceTemplate = {religion,category,amount};

       // String result = Arrays.toString(printResultWeb(mongoDB, choiceTemplate));
        String [][]arrResult = printResultWeb(mongoDB, choiceTemplate);


        if(amount != "800000") {
//            out.println("<h2>Category Selected: " + category + "</h2>");
//            out.println("<h2>Religion Selected: " + religion + "</h2>");
//            out.println("<h2>Amount Selected: " + amount + "</h2>");
//            out.println("<h1>RESULT: " + result + "</h1>");
            out.println("<div class=\"result\">");
            out.println("<h2 class=\"result-item\">Category Selected: <span>" + category + "</span></h2>");
            out.println("<h2 class=\"result-item\">Religion Selected: <span>" + religion + "</span></h2>");
            out.println("<h2 class=\"result-item\">Amount Selected: <span>" + amount + "</span></h2>");
           // out.println("<h1 class=\"result-item\">RESULT: <span>" + result + "</span></h1>");
            for(int i = 1;i<=Integer.parseInt(arrResult[0][0]);i++){
                out.println("<h1 class=\"result-item\">Scholarship: <span>" + arrResult[i][0] + "</span></h1>");
                out.println("<h1 class=\"result-item\">Apply Link: <a href=\"" + arrResult[i][1] + "\">" + arrResult[i][1] + "</a></h1>");
                out.println("<h1 class=\"result-item\">Department: <span>" + arrResult[i][2] + "</span></h1>");
                out.println("<h1 class=\"result-item\">Link: <a href=\"" + arrResult[i][3] + "\">" + arrResult[i][3] + "</a></h1>");
            }

            out.println("</div>");



        }
        else  {
            out.println("<h2>We have not added 2.5lakh category, Sorry!</h2>");
        }
        out.println("</body>");
        out.println("</html>");
    }
}
