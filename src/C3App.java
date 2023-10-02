import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class C3App {
    public static void main(String[] args) {
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/jdbc_test";
        final String USER = "root";
        final String PASSWORD = "";

        // TASK: Inserting a new record to a table

        // `author_id` is the primary auto increment field (no need to be added)
        // `join_date` was also skipped (Null Value is accepted)
        String name1 = "John";
        String name2 = "Doe";
        String email = "john.doe@jdbcdemo.ca";
        String phone = "4161112222";
        String city = "Toronto";
        String province = "ON";

        /*
         * The pure SQL query:
         * INSERT INTO authors (first_name, last_name, email, phone, city, province)
         * VALUES ('John','Doe','john.doe@jdbcdemo.ca','4161112222','Toronto','ON');
         * 
         * As we do with PHP, we will use the prepared statement :-)
         * Sometimes it is more convenient to use a "PreparedStatement" object
         * Link:
         * https://docs.oracle.com/javase/8/docs/api/java/sql/PreparedStatement.html
         * 
         * Link:
         * https://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html
         * 
         * public interface "PreparedStatement"
         * 
         */

        /*
         * The pure SQL query:
         * *******************
         * INSERT INTO authors (first_name, last_name, email, phone, city, province)
         * VALUES ('John','Doe','john.doe@jdbcdemo.ca','4161112222','Toronto','ON');
         */
        final String QUERY = "INSERT INTO authors (first_name, last_name, email, phone, city, province)"
                + " VALUES (?,?,?,?,?,?)";

        // Test:
        System.out.println("Query String: \n" + QUERY);

        // We can use try-catch blocks from "Driver.java" to test the driver first
        // Then using try-with-resources to connect to and query the database
        try (
                Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);) {

            PreparedStatement insertPStmt = connection.prepareStatement(QUERY);
            insertPStmt.setString(1, name1);
            insertPStmt.setString(2, name2);
            insertPStmt.setString(3, email);
            insertPStmt.setString(4, phone);
            insertPStmt.setString(5, city);
            insertPStmt.setString(6, province);
            /*
             * NOTE:
             * we also have .setInt() for integers, .setDouble() for decimals, and so on...
             */

            // Execute the query
            /*
             * NOTES:
             * - With Update or Delete operation,
             * we use the method "executeUpdate" instead of "executeQuery"
             * 
             * - executeUpdate() returns:
             * > either (1) the row count for SQL Data Manipulation Language (DML)
             * statements
             * > or (2) 0 for SQL statements that return nothing
             * 
             * - executeUpdate(): throws SQLException
             */
            int rowCount = insertPStmt.executeUpdate(QUERY);

            /*
             * We can build our condition according to the value of 1 for true update/delete
             */
            if (rowCount == 1) {
                System.out.println("1 row has been inserted");
            } else {
                System.out.println("O rows affected, insertion operation failed!");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.err.println("Failed to insert a record!");
            System.err.println("Your SQL Statement: \n" + QUERY);
            /*
             * NOTE: IF SQL query statement has error(s):
             * Java will throw this exception "SQLSyntaxErrorException"
             * 
             * Package java.sql.SQLSyntaxErrorException:
             */
        }
    } // main()
} // class file
