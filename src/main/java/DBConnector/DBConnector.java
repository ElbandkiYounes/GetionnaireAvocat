package DBConnector;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
    public static Connection connectDB( ){
        Connection connection = null;
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Establish connection to SQLite database
            //to work in intelij
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/GetionnaireAvocatSqlite.db");
            //to work in jar
           // connection = DriverManager.getConnection("jdbc:sqlite:./GetionnaireAvocatSqlite.db");
            System.out.println("Open Connection With Data Base");

        } catch (Exception e) {
            System.out.println("\n Error in Connection With Data Base");
            System.out.println(e);
        }
        return connection;
    }
}
