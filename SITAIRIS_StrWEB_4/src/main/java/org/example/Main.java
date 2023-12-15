package org.example;
import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/laba4";
        String user = "root";
        String password = "123123";

        try (Connection connection = DriverManager.getConnection(url, user, password); Statement statement = connection.createStatement()) {
            // в этом месте вы можете выполнять операции с базой данных
            //statement.execute("INSERT INTO  users(name, age, email) VALUES('name4', 25, 'email4')");
            //statement.executeUpdate();
            //statement.executeQuery("SELECT * FROM users");
            connection.close();
        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }
}