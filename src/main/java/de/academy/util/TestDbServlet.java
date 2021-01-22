package de.academy.util;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

// Sanity test of the database connection

@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        // setup connection variables

        String jdbcUrl = "jdbc:mysql://localhost:3306/uni-management-db?useSSL=false&serverTimezone=UTC";
        String driver = "com.mysql.cj.jdbc.Driver";

        String user = "unimanager";
        String password = "unimanager";

        // get connection to database
        try {
            PrintWriter out = response.getWriter();
            out.println("Connecting to database " + jdbcUrl);

            Class.forName(driver);
            Connection connection = DriverManager.getConnection(jdbcUrl, user, password);

            out.println("Connection successful!");
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
