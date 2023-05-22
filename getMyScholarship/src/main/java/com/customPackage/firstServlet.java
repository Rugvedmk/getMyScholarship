package com.customPackage;
import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class firstServlet implements Servlet {

    ServletConfig conf;
    public void init(ServletConfig conf) {
        this.conf = conf;
        System.out.println("Creating object...");
    }


    public void service(ServletRequest req, ServletResponse resp) throws  ServletException, IOException {
        System.out.println("Servicing");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h1>THIS IS DONE WITH LOVE:</h1>");
    }


    public void destroy() {
        System.out.println("Destroying");
    }


    public ServletConfig getServletConfig() {
        return this.conf;
    }


    public String getServletInfo() {
        return "This is done by Om PAtil";
    }
}
