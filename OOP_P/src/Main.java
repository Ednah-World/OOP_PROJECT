import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Statement state = null;
        ConnectDB db = new ConnectDB();
        Connection conn = db.connectToDatabase("oop_project", "postgres", "Murungii");

        try {
            String query = "insert into courses (course_id, course_name, credits, instructor) values ('001', 'BComm', '4','Prof. Wafula')";
            state = conn.createStatement();
            state.executeUpdate(query);
            System.out.println("Value inserted successfully");
        } catch (Exception e) {
            System.err.println("Error inserting into courses: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (state != null) {
                try {
                    state.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            String query = "insert into Student (student_id, student_name, email) values ('5001', 'Bing Bong', 'boing@gmail.com')";
            state = conn.createStatement();
            state.executeUpdate(query);
            System.out.println("Value inserted successfully");
        } catch (Exception e) {
            System.err.println("Error inserting into Student: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (state != null) {
                try {
                    state.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            String query = "insert into Enrollments (enrollment_id, student_id, grade) values ('50', '5001', 'A')";
            state = conn.createStatement();
            state.executeUpdate(query);
            System.out.println("Value inserted successfully");
        } catch (Exception e) {
            System.err.println("Error inserting into Enrollments: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (state != null) {
                try {
                    state.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            String query = "insert into staff (staff_id, department) values ('S001', 'Mathematics')";
            state = conn.createStatement();
            state.executeUpdate(query);
            System.out.println("Value inserted successfully");
        } catch (Exception e) {
            System.err.println("Error inserting into staff: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (state != null) {
                try {
                    state.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
