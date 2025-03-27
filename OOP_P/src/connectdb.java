import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class ConnectDB {

    public Connection connectToDatabase(String dbname, String user, String password) {
        Connection conn = null;

        try {
            // Load the JDBC driver
            Class.forName("org.postgresql.Driver");

            // Attempt to connect to the database
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, password);

            if (conn != null) {
                System.out.println("Connection Established");
            } else {
                System.out.println("Connection Failed");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return conn;
    }

    public void useConnection(Connection conn) {
        if (conn != null) {
            try {
                // Create a statement only if connection is not null
                Statement stmt = conn.createStatement();
                System.out.println("Statement created successfully!");
                // Perform operations on stmt
            } catch (SQLException e) {
                System.out.println("Error creating statement: " + e.getMessage());
            }
        } else {
            System.out.println("Cannot create statement. Connection is null.");
        }
    }

    public static void main(String[] args) {
        ConnectDB db = new ConnectDB();
        Connection conn = db.connectToDatabase("oop_project", "postgres", "Murungii");

        // Call the method to use the connection
        db.useConnection(conn);
    }
}
