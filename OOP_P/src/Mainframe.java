import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

<<<<<<< .merge_file_aeqkxA
public class Mainframe extends JFrame {
    private JTextField courseIDField, courseNameField, creditsField, instructorField;
    private AbstractButton button;
=======
        public class Mainframe extends JFrame {
            public void init() {
                setTitle("Add Course");
                setSize(500, 450);
                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                setLayout(new GridLayout(5, 2, 5, 5));// Set layout for components
                Course course= new Course();
>>>>>>> .merge_file_4spnIL

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


                // Adding action listener to the button
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String course_id = courseIDField.getText();
                        String course_name = courseNameField.getText();
                        String credits = creditsField.getText();
                        String instructor = instructorField.getText();
                        JOptionPane.showMessageDialog(null, "Course Added: " + course_name);

                        if (course_id.isEmpty() || course_name.isEmpty() || credits.isEmpty() || instructor.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "All fields are required!");
                            return;
                        }

                        if (insertIntoCourses(course_id, course_name, credits, instructor)) {
                            JOptionPane.showMessageDialog(null, "Course Added Successfully!");
                        }

                        //  Clear fields after submission
                        idtextField.setText("");
                        textField.setText("");
                        crtextField.setText("");
                        insttextField.setText("");
                    }

                });

                insertIntoCourses(courseID, courseName, credits, instructor);
                JOptionPane.showMessageDialog(null, "Course Added: " + courseName);

                //  Clear fields after submission
                courseIDField.setText("");
                courseNameField.setText("");
                creditsField.setText("");
                instructorField.setText("");
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


            private boolean insertIntoCourses(String course_id,String course_name, String credits, String instructor) {
                String url = "jdbc:postgresql://localhost:5432/test"; // Change to your database
                String user = "postgres"; // Change to your username
                String password = "100100"; // Change to your password

                String query = "INSERT INTO Courses (course_name, course_id, credits, instructor) VALUES (?,?,?,?)";

                try (Connection conn = DriverManager.getConnection(url, user, password);
                     PreparedStatement stmt = conn.prepareStatement(query)) {

                    stmt.setString(1, course_id);
                    stmt.setString(2, course_name);
                    stmt.setString(3, credits);
                    stmt.setString(4, instructor);
                    int rowsInserted = stmt.executeUpdate();

                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(null, "Course Added Successfully!");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            return false;}

            public static void main(String[] args) {
                Mainframe myFrame = new Mainframe();
                myFrame.init();
            }
=======
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
>>>>>>> 1cd56611d80b782492a190c8e3c83e3f10af5b01
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Mainframe myFrame = new Mainframe();
            myFrame.init();
        });
    }
}
