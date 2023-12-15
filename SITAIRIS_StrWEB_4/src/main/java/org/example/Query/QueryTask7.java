package org.example.Query;
import org.example.Connection.JDBC;
import java.sql.*;
import java.util.*;

public class QueryTask7 {
    public static void main(String[] args) {
        Statement stmt = null;
        try {

            JDBC.connect();

            stmt = JDBC.connection.createStatement();

// Insert new publisher
            String query = "INSERT INTO Publishers (publisherName) VALUES ('Publisher2');";
            //stmt.executeUpdate(query);

            query = "INSERT INTO authorISBN (authorID, ISBN) " +
                    "VALUES ((SELECT authorID FROM authors " +
                    "WHERE firstName = 'Jesse' AND lastName = 'White' LIMIT 1), '1111111111');";
            //stmt.executeUpdate(query);
// Insert new title with publisherID obtained from subquery (limited to 1 row)
            query = "INSERT INTO Titles (title, publisherID, ISBN) " +
                    "VALUES ('PublisherBook', " +
                    "(SELECT publisherID FROM Publishers WHERE publisherName = 'Publisher2' LIMIT 1), " +
                    "1111111111)";
            stmt.executeUpdate(query);

// Insert authorISBN with authorID obtained from subquery (limited to 1 row)

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } finally {
            //finally block used to close resources
            JDBC.close();
        }
    }
}