import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Mainframe extends JFrame {
    private JTextField courseIDField, courseNameField, creditsField, instructorField;

    public void init() {
        setTitle("Add Course");
        setSize(500, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 5, 5)); 

        // UI Components
        add(new JLabel("Course ID:"));
        courseIDField = new JTextField(20);
        add(courseIDField);

        add(new JLabel("Course Name:"));
        courseNameField = new JTextField(20);
        add(courseNameField);

        add(new JLabel("Credits:"));
        creditsField = new JTextField(20);
        add(creditsField);

        add(new JLabel("Instructor:"));
        instructorField = new JTextField(20);
        add(instructorField);

        JButton submitButton = new JButton("Submit");
        add(submitButton);

        // Action Listener
        submitButton.addActionListener(e -> {
            try {
                String courseID = courseIDField.getText();
                String courseName = courseNameField.getText();
                int credits = Integer.parseInt(creditsField.getText()); // Convert input to integer
                String instructor = instructorField.getText();

                insertIntoCourses(courseID, courseName, credits, instructor);
                JOptionPane.showMessageDialog(null, "Course Added: " + courseName);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input for credits!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }

    private void insertIntoCourses(String course_id, String course_name, int credits, String instructor) {
        String url = "jdbc:postgresql://localhost:5432/test";
        String user = "postgres";
        String password = "100100";

        String query = "INSERT INTO Courses (course_id, course_name, credits, instructor) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, course_id);
            stmt.setString(2, course_name);
            stmt.setInt(3, credits);
            stmt.setString(4, instructor);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Course added successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Mainframe myFrame = new Mainframe();
            myFrame.init();
        });
    }
}
