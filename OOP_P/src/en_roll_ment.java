import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class en_roll_ment extends JFrame {
    public void init() {
        setTitle("Enroll");
        setSize(500, 450);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 5, 5));

        // Creating UI Components
        JLabel label = new JLabel("Enrollment ID:");
        JTextField textField = new JTextField(20);

        // Adding components to frame
        add(label);
        add(textField);


        JLabel sidlabel = new JLabel("Student ID:");
        JTextField sidtextField = new JTextField(20);
        add(sidlabel);
        add(sidtextField);

        JLabel cidlabel = new JLabel("Course ID:");
        JTextField cidtextField = new JTextField(20);
        add(cidlabel);
        add(cidtextField);

        JLabel grlabel = new JLabel("Grade:");
        JTextField grtextField = new JTextField(20);
        add(grlabel);
        add(grtextField);

        JButton button = new JButton("Submit");
        add(button);

        // Adding action listener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enrollment_id = textField.getText();
                String student_id = sidtextField.getText();
                String course_id = cidtextField.getText();
                String grade = grtextField.getText();
                JOptionPane.showMessageDialog(null, "Course Added: " + enrollment_id);

                //  Clear fields after submission
                textField.setText("");
                sidtextField.setText("");
                cidtextField.setText("");
                grtextField.setText("");
            }
        });

        setVisible(true);
    }

    private void insertIntoEnrollment(String enrollment_id,String student_id, String course_id, String grade) {
        String url = "jdbc:mysql://localhost:5432/OOP_Projct"; // Change to your database
        String user = "postgres"; // Change to your MySQL username
        String password = "Wanjiru1806"; // Change to your MySQL password

        String query = "INSERT INTO Enrollment (enrollment_id, student_id, course_id, grade) VALUES (?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, enrollment_id);
            stmt.setString(2, student_id);
            stmt.setString(3, course_id);
            stmt.setString(4, grade);
            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Enrolled Successfully!");
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        en_roll_ment enRollMent = new en_roll_ment();
        enRollMent.init();
    }
}
