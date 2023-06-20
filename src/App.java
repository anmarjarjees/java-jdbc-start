import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager; // The basic service for managing a set of JDBC drivers.
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws Exception {
        /*
         * Below is the connection string for the Database URL:
         * > jdbc:database-driver:database-URL-name
         * - database-type like: derby, mysql, ...
         * - URL the localhost for the local development
         * 
         * Examples:
         * - jdbc:derby:jdbc-test
         * - jdbc:mysql:/loclahost:3306/jdbc_test
         */
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/jdbc_test";
        final String USER = "root";
        final String PASS = "";
        final String QUERY = "SELECT * FROM authors";

        // use try-with-resources to connect to and query the database
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASS);

            // Creating the statement
            Statement statement = connection.createStatement();

            // Execute the query
            ResultSet resultSet = statement.executeQuery(QUERY);

            System.out.println("the first way:");
            // Extract data from result set:
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getString("author_id"));
                System.out.println("First Name: " + resultSet.getString("first_name"));
                System.out.println("Last Name: " + resultSet.getString("last_name"));
                System.out.println("Phone: " + resultSet.getString("phone"));
                System.out.println("Email: " + resultSet.getString("email"));
            }

            /*
             * The other alternative way (solution) :-)
             * ****************************************
             */
            // re-execute the query
            resultSet = statement.executeQuery(QUERY);
            System.out.println("the other way:");

            // get ResultSet's meta data
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();

            System.out.printf("Authors Table of JDBC_Test Database:%n%n");

            // display the names of the columns in the ResultSet
            for (int i = 1; i <= numberOfColumns; i++) {
                System.out.printf("%-22s\t", metaData.getColumnName(i));
            }
            System.out.println();

            // display query results
            while (resultSet.next()) {
                // the number of columns in each row/record:
                for (int i = 1; i <= numberOfColumns; i++) {
                    System.out.printf("%-22s\t", resultSet.getObject(i));
                }
                System.out.println();
            }
        } catch (

        SQLException sqlException) {
            sqlException.printStackTrace();
        }

    } // main()
} // class