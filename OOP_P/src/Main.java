import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        Statement state;
        connectdb db= new connectdb();
        Connection conn=db.conn("test","postgres","100100");

        try {
            String query = "insert into courses (course_id, course_name, credits, instructor) values ('001', 'BComm', '4','I009')";
            state= conn.createStatement();
            state.executeUpdate(query);
            System.out.println("Value inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String query= "insert into Student (student_id, student_name, email) values ('5001', 'Bing Bong', 'boing@gmail.com')";
            state= conn.createStatement();
            state.executeUpdate(query);
            System.out.println("Value inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String query = "insert into Enrollments (enrollment_id, student_id, grade) values ('50', '5001', '001','A')";
            state= conn.createStatement();
            state.executeUpdate(query);
            System.out.println("Value inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String query = "insert into staff (staff_id, department,phone_number,password) values ('S001', 'Mathematics','0727308900','kairetu27*')";
            state= conn.createStatement();
            state.executeUpdate(query);
            System.out.println("Value inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}