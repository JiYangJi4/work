package org.example.Query;
import org.example.Connection.JDBC;
import java.sql.*;
import java.util.*;

public class QueryTask6 {
    public static void main(String[] args) {
        Statement stmt = null;
        try {

            JDBC.connect();

            stmt = JDBC.connection.createStatement();

            String query = "UPDATE authors SET firstName = 'Jesse' WHERE authorID = 16;";

            stmt.executeUpdate(query);
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } finally {
            //finally block used to close resources
            JDBC.close();
        }
    }
}