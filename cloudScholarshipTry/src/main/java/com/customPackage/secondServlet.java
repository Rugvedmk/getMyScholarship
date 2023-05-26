package com.customPackage;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class secondServlet extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        System.out.println("this is using generic class");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h1>Using Generic Class</h1>");
    }
}
