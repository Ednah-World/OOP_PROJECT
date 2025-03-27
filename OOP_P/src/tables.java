import java.sql.Connection;
import java.sql.Statement;

public class tables {
    public static void main(String [] args) {
        Statement state;
        connectdb db = new connectdb();
        Connection conn = db.conn("OOP_Projct", "postgres", "Wanjiru1806");
        try {
            String query= "Create table Courses (course_id VARCHAR(20) primary key, course_name VARCHAR(20), credits VARCHAR(20), instructor VARCHAR(20))";
            state= conn.createStatement();
            state.executeUpdate(query);
            System.out.println("Finished");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String query= "Create table Student (student_id VARCHAR(20) primary key, student_name VARCHAR(20), email VARCHAR(20), phone_number VARCHAR(20), password VARCHAR(20))";
            state= conn.createStatement();
            state.executeUpdate(query);
            System.out.println("Finished");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String query = "Create table Enrollments (enrollment_id VARCHAR(20) primary key, student_id VARCHAR(20), course_id VARCHAR(20), grade VARCHAR(20))";
            state = conn.createStatement();
            state.executeUpdate(query);
            System.out.println("Finished");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String query = "Create table Staff (staff_id VARCHAR(20) primary key, department VARCHAR(20), phone_number VARCHAR(20), password VARCHAR(20))";
            state = conn.createStatement();
            state.executeUpdate(query);
            System.out.println("Finished");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String query = "Create table Attendance (student_id VARCHAR(20), hours VARCHAR(20))";
            state = conn.createStatement();
            state.executeUpdate(query);
            System.out.println("Finished");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String query = "Create table Fees (student_id VARCHAR(20), credit VARCHAR(20), debit VARCHAR(20))";
            state = conn.createStatement();
            state.executeUpdate(query);
            System.out.println("Finished");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String query = "Create table Registration (name VARCHAR(20) primary key, email VARCHAR(20), phone VARCHAR(20), address VARCHAR(20), password VARCHAR(20))";
            state = conn.createStatement();
            state.executeUpdate(query);
            System.out.println("Finished");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
