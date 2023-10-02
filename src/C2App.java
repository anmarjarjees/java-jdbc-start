import java.sql.Connection;
import java.sql.DriverManager; // The basic service for managing a set of JDBC drivers.
import java.sql.SQLException;
import java.sql.Statement;

public class C2App {

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
         * The pure SQL query:
         * *******************
         * INSERT INTO authors (first_name, last_name, email, phone, city, province)
         * VALUES ('John','Doe','john.doe@jdbcdemo.ca','4161112222','Toronto','ON');
         */

        // Hard Coding:
        String query_example = "INSERT INTO authors (first_name, last_name, email, phone, city, province) VALUES ('Sarah','Grand','sarah.grand@trying.ca','4161119999','Markham','ON')";

        // Test:
        System.out.println("The hard coding string: \n" + query_example);

        // PASSWORDing Variables:

        // Concatenation: notice that we use ' ' to surround the fields value
        // as we do in a simple SQL statement
        /*
         * Bad way to passing the data directly to the database!
         * Some may try to attack/hack the database base by passing a malicious code
         * instead of real info!
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

        // String query = STR.

        // Try/catch() for Solution#1:
        try (
                // Getting a connection to our Database by using "DriverManager" for any DB:
                Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

                // Creating the statement object (for C, R, U, or D)
                Statement statement = connection.createStatement();) {

            // Execute the query
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to insert data! " + e.getMessage());
            /*
             * NOTE:
             * When we enter same (existing) value for a table column/field
             * that has be unique,
             * For example insert the same email "smith.s@trying.ca"
             * and the "email" field has the unique constraint,
             * Java will throw this exception:
             * "java.sql.SQLIntegrityConstraintViolationException: Duplicate entry 'smith.s@trying.ca' for key 'email'"
             * 
             * So the Exception class name is "SQLIntegrityConstraintViolationException"
             */
        }
    } // main()
} // class