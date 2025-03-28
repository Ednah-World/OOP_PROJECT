import java.sql.*;

public class Student extends Person{

    private String name;
    private String email;
    private int student_id;
    private int PhoneNumber;

    public Student(String name, String email, String student_id, int PhoneNumber){
        super();
        this.student_id = Integer.parseInt(student_id);
    }

    public Student(String name, String email, String phoneNumber) {
        super();
    }

    public int getStudentId() {
        return student_id;
    }

    // Database connection details
    private static final String URL = "jdbc:postgresql://localhost:5432/OOP_Project";
    private static final String USER = "postgres"; // Change this
    private static final String PASSWORD = "Wanjiru1806"; // Change this

    // Method to insert a student into the database
    public void saveToDatabase() {
        String query = "INSERT INTO students (name, email, id, phone number) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, getName());
            stmt.setString(2, getEmail());
            stmt.setString(3, String.valueOf(getStudentId()));
            stmt.setString(4, getPhoneNumber());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                this.student_id = rs.getInt("student_id"); // Retrieve the auto-generated ID
                System.out.println("Student added with ID: " + student_id);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private String getName() {
        return name;
    }

    private String getPhoneNumber() {
        return PhoneNumber;
    }

    private String getEmail() {
        return email;
    }

    // Method to fetch student details from the database
    public static Student getStudentById(int studentId) {
        String query = "SELECT * FROM students WHERE student_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String PhoneNumber = rs.getString("Phone Number");
                return new Student(name, email, PhoneNumber, password,studentId );
            } else {
                System.out.println("Student not found.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // Method to update student details
    public void updateStudent(String newName, String newEmail, int newPhoneNumber) {
        String query = "UPDATE students SET name = ?, email = ?, Phone Number = ?, password = ?, WHERE student_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, newName);
            stmt.setString(2, newEmail);
            stmt.setString(3, String.valueOf(newPhoneNumber));
            stmt.setInt(5, student_id);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Student updated successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Method to delete a student
    public void deleteStudent() {
        String query = "DELETE FROM students WHERE student_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, student_id);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Student deleted successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void displayDetails() {
        System.out.println("Student Id: ");
        System.out.println("Student Name: ");
        System.out.println("Student Email: ");
        System.out.println("Phone Number: ");
    }

    public String getname() {
        return name;
    }
}

