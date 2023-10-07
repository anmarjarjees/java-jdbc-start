import java.sql.Connection;
import java.sql.DriverManager; // The basic service for managing a set of JDBC drivers.
import java.sql.SQLException;
import java.sql.Statement;

public class C2InsertBssic {

    public static void main(String[] args) throws SQLException {
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

        // Basic Solution (Without prepared statement):
        /*
         * The pure SQL query statement:
         * *****************************
         * INSERT INTO authors (first_name, last_name, email, phone, city, province)
         * VALUES ('John','Doe','john.doe@jdbcdemo.ca','4161112222','Toronto','ON');
         */

        // Hard Coding :-(
        String query_example = "INSERT INTO authors (first_name, last_name, email, phone, city, province) VALUES ('Sarah','Grand','sarah.grand@trying.ca','4161119999','Markham','ON')";

        // Test:
        System.out.println("The hard coding string: \n" + query_example);

        // PASSWORDing Variables:

        // Concatenation: notice that we use ' ' to surround the fields value
        // as we do in a simple SQL statement
        /*
         * Bad way to passing the data directly to the database!
         * Some may try to attack/hack the database
         * by passing a malicious code instead of real info!
         * we will use "Prepared statement" in the coming code examples
         */
        String query = "INSERT INTO authors (first_name, last_name, email, phone, city, province) VALUES ('" + name1
                + "','" + name2 + "','" + email + "','" + phone + "','" + city + "','" + province + "')";

        // Test:
        System.out.println("The second query string: \n" + query);

        /*
         * Using String Template with the constant "STR"
         */
        // String query = "INSERT INTO authors (first_name, last_name, email, phone,
        // city, province) VALUES ('${name1}','${name2}')";

        /*
         * NOTE:
         * Globally declare the connection string and the prepares statement objects,
         * so we can close them inside the "finally" block
         */
        Connection connection = null;
        Statement statement = null;

        // Using try/catch() with finally to close: connection & prepared statement
        // In the next example, we use Try/catch() with resources to close the
        // connection after done
        try {

            // Getting a connection to our Database by using "DriverManager" for any DB:
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            // Creating the statement object (for C, R, U, or D)
            statement = connection.createStatement();

            // Execute the prepared statement => .executeUpdate()
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to insert data! " + e.getMessage());
            /*
             * NOTE:
             * Regarding enter same (existing) value for a table column/field
             * that has to be unique,
             * For example insert the same email "smith.s@trying.ca"
             * and the "email" field has the unique constraint,
             * Java will throw this exception:
             * "java.sql.SQLIntegrityConstraintViolationException: Duplicate entry 'smith.s@trying.ca' for key 'email'"
             * 
             * So the Exception class name is "SQLIntegrityConstraintViolationException"
             */
        } finally {
            /*
             * NOTE:
             * Calling the close() method on these two object
             * will throw a "Checked Exception":
             * 
             * Both closing statements below will show:
             * "Unhandled exception type SQLException"
             * 
             * Because: Calling the method close on a Connection object
             * that is already closed is a no-op.
             * 
             * Throws: SQLException - if a database access error occurs
             * 
             * Solutions according to "Quick Fix":
             * 1) Surrounding the two close() method with try and catch()
             * => [Too much nested blocks!]
             * 
             * 2) Just let the main() method throws "SQLException"
             * => [Better concise solution]
             */
            connection.close();
            statement.close();
        }
    } // main()
} // class file