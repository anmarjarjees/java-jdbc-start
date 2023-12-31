/* 
 * This just simply for testing the connection
 */
public class Driver {
    public static void main(String[] args) {
        /*
         * 
         * Link:
         * https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-usagenotes-connect-
         * drivermanager.html
         * Link:
         * https://www.ibm.com/docs/en/cognos-analytics/11.2.0?topic=administration-new-
         * mysql-driver-class
         * 
         * The default driver class name used for new MySQL connections has changed to
         * com.mysql.cj.jdbc.Driver.
         */
        try {
            /*
             * The default driver class name used for MySQL connections:
             * com.mysql.cj.jdbc.Driver.
             */
            var classObj = Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println(classObj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
