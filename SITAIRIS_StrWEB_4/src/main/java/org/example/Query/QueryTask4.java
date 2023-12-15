package org.example.Query;
import org.example.Connection.JDBC;
import java.sql.*;
import java.util.*;

public class QueryTask4 {
    public static void main(String[] args) {
        Statement stmt = null;
        try {

            JDBC.connect();

            stmt = JDBC.connection.createStatement();

            String query2Author = "SELECT Titles.publisherID, Titles.title, Publishers.publisherName FROM Titles " +
                    "INNER JOIN Publishers ON Titles.publisherID = Publishers.publisherID  " +
                    "WHERE Publishers.publisherID = 3 ORDER BY Titles.title DESC;";
            System.out.println("Show publisher and their titles");

            ResultSet rs1 = stmt.executeQuery(query2Author);
            while (rs1.next()) {
                int id = rs1.getInt("publisherID");
                String pubName = rs1.getString("publisherName");
                String titleName = rs1.getString("title");
                System.out.println(id + "\t" + pubName + "\t" + titleName);
            }

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } finally {
            //finally block used to close resources
            JDBC.close();
        }
    }
}


