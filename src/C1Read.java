import java.sql.Connection; // To connect Java app to SQL
import java.sql.Statement;
import java.sql.DriverManager; // The basic service for managing a set of JDBC drivers.
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/*
 * NOTE:
 * Don't forget to run your XAMPP Control Panel with MySQL Server to test the DB connection
 * Otherwise, error:
 * "The last packet sent successfully to the server was 0 milliseconds ago. 
 * The driver has not received any packets from the server."
 */
public class C1Read {
    /*
     * NOTE:
     * The statement "throws SQLException" is not needed!
     * Because we already handled the Checked exception with Try/Catch blocks
     * 
     */
    public static void main(String[] args) throws SQLException {
        /*
         * Below is the connection string for the Database URL:
         * > jdbc:database-driver:database-URL-name
         * - database-type like: derby, mysql, ...
         * - URL the localhost for the local development
         * 
         * DB Examples: jdbc:derby:, jdbc:mysql:, jdbc:postgresql, or...
         * 
         * - jdbc:mysql://localhost:3306/jdbc_test
         * > Database Driver (Vendor): jdbc:mysql:
         * > Database Host Name (IP): //localhost:3306/
         * > Database Name: "jdbc_test"
         */
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/jdbc_test";
        final String USER = "root"; // the default username for XAMPP
        final String PASSWORD = ""; // the default password for XAMPP

        // TASK: Selecting all records from a table

        final String QUERY = "SELECT * FROM authors";

        // We can use try-catch blocks from "Driver.java" to test the driver first

        /*
         * NOTE:
         * Below we are creating a global variable named "connection" or "conn" of type
         * "Connection"
         * > It will be used for Getting a connection to our Database by using
         * "DriverManager" for any DB:
         * > It' declared globally (outside the try block) so we can access it in the
         * finally block to close it
         * 
         * Notice that we can do using the other way with "try-with-resources"
         * You can refer to my repo "Java-Extra" and the package for "Exceptions"
         * for more details or see the next example :-)
         */
        Connection connection = null;
        // Then using try-with-resources to connect to and query the database
        try {
            // Getting a connection to our Database by using "DriverManager" for any DB:
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            // Creating the statement object (for C, R, U, or D)
            Statement statement = connection.createStatement();

            // Execute the query
            ResultSet resultSet = statement.executeQuery(QUERY);

            System.out.println("The first way:");
            
            // Extract data from result set:
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getString("author_id"));
                System.out.println("First Name: " + resultSet.getString("first_name"));
                System.out.println("Last Name: " + resultSet.getString("last_name"));
                System.out.println("Phone: " + resultSet.getString("phone"));
                System.out.println("Email: " + resultSet.getString("email"));
                /*
                 * .getInt(), .getDouble(), and so on...
                 */
            }

            /*
             * The other alternative way (solution) :-)
             * ****************************************
             */
            // re-execute the query
            resultSet = statement.executeQuery(QUERY);
            System.out.println("The other way:");

            // Getting ResultSet's meta data
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();

            System.out.printf("Authors Table of JDBC_Test Database:%n%n");

            // display the names of the columns in the ResultSet
            for (int i = 1; i <= numberOfColumns; i++) {
                System.out.printf("%-22s\t", metaData.getColumnName(i));
            }
            System.out.println();

            // We can create String container for concatenating all the returned records:
            String allRecords = ""; // initialize it with empty string first

            // display query results
            // next() => returns true if the new current row is valid; false if there are no
            // more rows
            while (resultSet.next()) {
                // the number of columns in each row/record:
                for (int i = 1; i <= numberOfColumns; i++) {
                    System.out.printf("%-22s\t", resultSet.getObject(i));

                    // or we can use our "allRecords" variable:
                    allRecords += resultSet.getString(i) + "\n";
                }
                System.out.println("\n**************\n");

                // printing the allRecords String container
                System.out.println(allRecords);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            System.err.println("Failed to select data!");
        } finally {
            /*
             * NOTE:
             * It's always a good coding practice to close our connection with the database
             * when we are done.
             * We can also use try with recourses
             */
            connection.close();
        }
    } // main()
} // class