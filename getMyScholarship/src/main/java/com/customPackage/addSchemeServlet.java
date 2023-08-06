package com.customPackage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.customPackage.insertQuery.mongoDB;
import static com.customPackage.update.addDoc.addSchemeWeb;

public class addSchemeServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String scholarshipNameWeb = request.getParameter("scholarshipname");
        String categoryWeb = request.getParameter("CategoryWeb");
        String religionWeb = request.getParameter("religionWeb");
        String amountWeb = request.getParameter("AmountWeb");
        String departmenWeb = request.getParameter("departname");
        String eligibilityWeb = request.getParameter("ElgibilityWeb");
        String applicationWeb = request.getParameter("fees");

        String []query = {scholarshipNameWeb, categoryWeb, religionWeb, amountWeb, departmenWeb, eligibilityWeb, applicationWeb};
        addSchemeWeb(mongoDB,query);
        out.println("<html>");
        out.println("<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Scholarship Portal</title>\n" +
                "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">\n" +
                "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM\" crossorigin=\"anonymous\"></script>\n" +
                "    <link rel=\"stylesheet\" href=\"styles.css\">\n" +
                "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
                "<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
                "<link href=\"https://fonts.googleapis.com/css2?family=Open+Sans&display=swap\" rel=\"stylesheet\">\n" +
                "<link rel=\"stylesheet\" href=\"forms.css\">\n" +
                "</head>\n" +
                "<body>\n" +
                "    <!--NAVBAR DO NOT TOUCH-->\n" +
                "    <div class=\"container-fluid\" style=\"height: 89px;\">\n" +
                "    <nav class=\"navbar navbar-expand-lg navbar-light bg-dark\" style=\"  background-color: #f2f2f2;\n" +
                "    position: fixed;\n" +
                "    top: 0;\n" +
                "    left: 0;\n" +
                "    width: 100%;\n" +
                "    padding: 20px; \n" +
                "    box-sizing: border-box;\n" +
                "    z-index: 999;\n" +
                "    \">\n" +
                "        <div class=\"container-fluid\">\n" +
                "          <a class=\"navbar-brand\" href=\"#\" style=\"color: bisque;\">getMyScholarship</a>\n" +
                "          <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarNavAltMarkup\" aria-controls=\"navbarNavAltMarkup\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
                "            <span class=\"navbar-toggler-icon\"></span>\n" +
                "          </button>\n" +
                "          <div class=\"collapse navbar-collapse\" id=\"navbarNavAltMarkup\">\n" +
                "            <div class=\"navbar-nav ms-auto\">\n" +
                "              <a class=\"nav-link active\" aria-current=\"page\" href=\"indexs.html\" style=\"color:white; padding-right: 40px;\">Home</a>\n" +
                "              <a class=\"nav-link\" href=\"login.html\"><svg xmlns=\"http://www.w3.org/2000/svg\" width=\"30\" height=\"30\" fill=\"white\" class=\"bi bi-person-circle\" viewBox=\"0 0 16 16\">\n" +
                "                <path d=\"M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z\"/>\n" +
                "                <path fill-rule=\"evenodd\" d=\"M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z\"/>\n" +
                "              </svg></a>\n" +
                "            </div>\n" +
                "          </div>\n" +
                "        </div>\n" +
                "      </nav>\n" +
                "    </div>\n");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Scholarship updated Successfully!</h2>");
        out.println("<footer class=\"text-center text-lg-start bg-dark text-muted\" style=\"margin-top: 60px;\">\n" +
                "    <!-- Section: Social media -->\n" +
                "    <section class=\"d-flex justify-content-center justify-content-lg-between p-4 border-bottom\">\n" +
                "      <!-- Left -->\n" +
                "      <div class=\"me-5 d-none d-lg-block\">\n" +
                "        <span>Get connected with us on social networks:</span>\n" +
                "      </div>\n" +
                "      <!-- Left -->\n" +
                "  \n" +
                "      <!-- Right -->\n" +
                "      <div>\n" +
                "        <a href=\"\" class=\"me-4 text-reset\">\n" +
                "          <i class=\"fab fa-facebook-f\"></i>\n" +
                "        </a>\n" +
                "        <a href=\"\" class=\"me-4 text-reset\">\n" +
                "          <i class=\"fab fa-twitter\"></i>\n" +
                "        </a>\n" +
                "        <a href=\"\" class=\"me-4 text-reset\">\n" +
                "          <i class=\"fab fa-google\"></i>\n" +
                "        </a>\n" +
                "        <a href=\"\" class=\"me-4 text-reset\">\n" +
                "          <i class=\"fab fa-instagram\"></i>\n" +
                "        </a>\n" +
                "        <a href=\"\" class=\"me-4 text-reset\">\n" +
                "          <i class=\"fab fa-linkedin\"></i>\n" +
                "        </a>\n" +
                "        <a href=\"\" class=\"me-4 text-reset\">\n" +
                "          <i class=\"fab fa-github\"></i>\n" +
                "        </a>\n" +
                "      </div>\n" +
                "      <!-- Right -->\n" +
                "    </section>\n" +
                "    <!-- Section: Social media -->\n" +
                "  \n" +
                "    <!-- Section: Links  -->\n" +
                "    <section class=\"\">\n" +
                "      <div class=\"container text-center text-md-start mt-5\">\n" +
                "        <!-- Grid row -->\n" +
                "        <div class=\"row mt-3\">\n" +
                "          <!-- Grid column -->\n" +
                "          <div class=\"col-md-3 col-lg-4 col-xl-3 mx-auto mb-4\">\n" +
                "            <!-- Content -->\n" +
                "            <h6 class=\" fw-bold mb-4\" style=\"color: aquamarine;\">\n" +
                "              <i class=\"fas fa-gem me-3\" ></i>getMyScholarship\n" +
                "            </h6>\n" +
                "            <p>\n" +
                "                Our user-friendly website provides a powerful search engine, expert resources, and personalized tools to help students navigate the vast landscape of scholarships available worldwide. \n" +
                "            </p>\n" +
                "          </div>\n" +
                "          <!-- Grid column -->\n" +
                "  \n" +
                "          <!-- Grid column -->\n" +
                "          <div class=\"col-md-2 col-lg-2 col-xl-2 mx-auto mb-4\">\n" +
                "            <!-- Links -->\n" +
                "            <h6 class=\"text-uppercase fw-bold mb-4\" style=\"color: aquamarine;\">\n" +
                "              TEAM\n" +
                "            </h6>\n" +
                "            <p>\n" +
                "              <a href=\"#!\" class=\"text-reset\">Angular</a>\n" +
                "            </p>\n" +
                "            <p>\n" +
                "              <a href=\"#!\" class=\"text-reset\">React</a>\n" +
                "            </p>\n" +
                "            <p>\n" +
                "              <a href=\"#!\" class=\"text-reset\">Vue</a>\n" +
                "            </p>\n" +
                "            <p>\n" +
                "              <a href=\"#!\" class=\"text-reset\">Laravel</a>\n" +
                "            </p>\n" +
                "          </div>\n" +
                "          <!-- Grid column -->\n" +
                "  \n" +
                "          <!-- Grid column -->\n" +
                "          <div class=\"col-md-3 col-lg-2 col-xl-2 mx-auto mb-4\">\n" +
                "            <!-- Links -->\n" +
                "            <h6 class=\"text-uppercase fw-bold mb-4\" style=\"color: aquamarine;\">\n" +
                "              Useful links\n" +
                "            </h6>\n" +
                "            <p>\n" +
                "              <a href=\"#!\" class=\"text-reset\">Pricing</a>\n" +
                "            </p>\n" +
                "            <p>\n" +
                "              <a href=\"#!\" class=\"text-reset\">Settings</a>\n" +
                "            </p>\n" +
                "            <p>\n" +
                "              <a href=\"#!\" class=\"text-reset\">Orders</a>\n" +
                "            </p>\n" +
                "            <p>\n" +
                "              <a href=\"#!\" class=\"text-reset\">Help</a>\n" +
                "            </p>\n" +
                "          </div>\n" +
                "          <!-- Grid column -->\n" +
                "  \n" +
                "          <!-- Grid column -->\n" +
                "          <div class=\"col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4\">\n" +
                "            <!-- Links -->\n" +
                "            <h6 class=\"text-uppercase fw-bold mb-4\"  style=\"color: aquamarine;\">Contact</h6>\n" +
                "            <p><i class=\"fas fa-home me-3\"></i> NASHIK 422010</p>\n" +
                "            <p>\n" +
                "              <i class=\"fas fa-envelope me-3\"></i>\n" +
                "              getmyscholarship2023@gmail.com\n" +
                "            </p>\n" +
                "            <p><i class=\"fas fa-phone me-3\"></i> + 01 234 567 88</p>\n" +
                "            <p><i class=\"fas fa-print me-3\"></i> + 01 234 567 89</p>\n" +
                "          </div>\n" +
                "          <!-- Grid column -->\n" +
                "        </div>\n" +
                "        <!-- Grid row -->\n" +
                "      </div>\n" +
                "    </section>\n" +
                "    <!-- Section: Links  -->\n" +
                "  \n" +
                "    <!-- Copyright -->\n" +
                "    <div class=\"text-center p-4\" style=\"background-color: rgba(0, 0, 0, 0.05);\">\n" +
                "      © 2023 Copyright:\n" +
                "      <a class=\"text-reset fw-bold\" href=\"https://mdbootstrap.com/\">PRUTHVIJ@2023</a>\n" +
                "    </div>\n" +
                "    <!-- Copyright -->\n" +
                "  </footer>");
        out.println("</body>");
        out.println("</html>");
    }
}
