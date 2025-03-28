import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class Final_Instructors  {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/test";
    private static final String USER = "postgres";
    private static final String PASSWORD = "100100";

    public static void main(String[] args) {

        SwingUtilities.invokeLater(Final_Instructors::createandshowGUI);

    }

    public static void createandshowGUI() {


        JFrame frame = new JFrame("Instructor Information Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridBagLayout());
        frame.setBackground(Color.BLACK);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // spacing

        // Instructor ID
        JLabel idLabel = new JLabel("Instructor ID:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        frame.add(idLabel, gbc);

        JTextField idField = new JTextField(20);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        frame.add(idField, gbc);

        // Instructor Name
        JLabel nameLabel = new JLabel("Instructor Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        frame.add(nameLabel, gbc);

        JTextField nameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        frame.add(nameField, gbc);

        // Course
        JLabel courseLabel = new JLabel("Course:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        frame.add(courseLabel, gbc);

        JTextField courseField = new JTextField(20);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        frame.add(courseField, gbc);

        // Email
        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        frame.add(emailLabel, gbc);

        JTextField emailField = new JTextField(20);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        frame.add(emailField, gbc);

        // Submit Button
        JButton submitButton = new JButton("Submit");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(submitButton, gbc);


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String name = nameField.getText();
                String course = courseField.getText();
                String email = emailField.getText();
                insertInstructorData(id, name, course, email);

                idField.setText("");
                nameField.setText("");
                courseField.setText("");
                emailField.setText("");
            }
        });

        // Make frame visible
        frame.setVisible(true);
    }

    private static void insertInstructorData(String id, String name, String course, String email) {
        String insertSQL = "INSERT INTO Instructors (id, name, course, email) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name );
            preparedStatement.setString(3, course);
            preparedStatement.setString(4, email);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Instructor data inserted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error inserting data: " + e.getMessage());
        }
    }
}

