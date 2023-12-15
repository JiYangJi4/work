package org.example.Query;
import org.example.Connection.JDBC;
import java.sql.*;
import java.util.*;

public class QueryTask5 {
    public static void main(String[] args) {
        Statement stmt = null;
        try {

            JDBC.connect();

            stmt = JDBC.connection.createStatement();

            String query = "INSERT INTO authors (firstName, lastName) VALUES ('Walter', 'White');";

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