import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public class Dashboard extends JFrame {
    private Connection connection;
    private CustomPanel panel;

    public Dashboard() {

        setTitle("Dashboard");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        panel = new CustomPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLACK);


        JButton viewCoursesButton = new JButton("View Courses");
        JButton viewStudentsButton = new JButton("View Students");
        JButton viewInstructorsButton = new JButton("View Instructors");
        JButton add_InstructorsButton = new JButton("Add Instructors");
        JButton addStudentsButton = new JButton("Add Students");




        viewCoursesButton.addActionListener(e -> displayCourses());
        viewStudentsButton.addActionListener(e -> displayStudents());
        viewInstructorsButton.addActionListener(e -> displayInstructors());
        add_InstructorsButton.addActionListener(e -> displayFinal_instructors());
        addStudentsButton.addActionListener(e -> displayMyStudents());



        styleButton(viewCoursesButton);
        styleButton(viewStudentsButton);
        styleButton(viewInstructorsButton);
        styleButton(add_InstructorsButton);
        styleButton(addStudentsButton);



        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(viewCoursesButton);
        panel.add(viewCoursesButton);
        panel.add(Box.createRigidArea(new Dimension(0, 50))); // Spacing between buttons
        panel.add(viewStudentsButton);
        panel.add(Box.createRigidArea(new Dimension(0, 50))); // Spacing between buttons
        panel.add(viewInstructorsButton);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(add_InstructorsButton);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(addStudentsButton);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));



        add(panel);


        connectToDatabase();



    }





    private void connectToDatabase() {
        try {
            String DB_URL = "jdbc:postgresql://localhost:5432/PlungerSchool"; // Update with your database URL
            String USER = "postgres"; // Update with your database username
            String PASSWORD = "Godisawesome@21"; // Update with your database password
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Connected to the database successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database connection failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }




    private void displayCourses() {
        StringBuilder courses = new StringBuilder("Courses:\n");
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM course")) {
            while (rs.next()) {
                courses.append(rs.getString("courseid")).append("\n");
                courses.append(rs.getString("course_name")).append("\n");
                courses.append(rs.getString("credits")).append("\n");
                courses.append(rs.getString("instructor")).append("\n\n");
                // Adjust based on your table structure
            }
        } catch (SQLException e) {
            courses.append("Error retrieving courses: ").append(e.getMessage());
        }
        JOptionPane.showMessageDialog(this, courses.toString());
    }


    private void displayStudents() {
        StringBuilder students = new StringBuilder("Students:\n");
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM student")) {
            while (rs.next()) {
                students.append(rs.getString("id")).append("\n");
                students.append(rs.getString("name")).append("\n");
                students.append(rs.getString("phone")).append("\n");
                students.append(rs.getString("email")).append("\n\n");
                // Adjust based on your table structure
            }
        } catch (SQLException e) {
            students.append("Error retrieving students: ").append(e.getMessage());
        }
        JOptionPane.showMessageDialog(this, students.toString());
    }


    private void displayInstructors() {
        StringBuilder instructors = new StringBuilder("Instructors:\n");
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM instructor")) {
            while (rs.next()) {
                instructors.append(rs.getString("id")).append("\n");
                instructors.append(rs.getString("name")).append("\n");
                instructors.append(rs.getString("course")).append("\n");
                instructors.append(rs.getString("email")).append("\n\n");
                // Adjust based on your table structure
            }
        } catch (SQLException e) {
            instructors.append("Error retrieving instructors: ").append(e.getMessage());
        }
        JOptionPane.showMessageDialog(this, instructors.toString());
    }


private void displayFinal_instructors() {

    SwingUtilities.invokeLater(() -> new Dashboard());
    JFrame frame = new JFrame("My Frame");
    JButton add_instructors = new JButton("Add Instructors");

    add_instructors.addActionListener(e ->displayFinal_instructors());

    Final_Instructors.createandshowGUI();
    frame.dispose();



}



private void displayMyStudents(){




}


    // Method to style buttons
    private void styleButton(JButton button) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 14));
    }




    // Custom JPanel to handle background image and color
    private class CustomPanel extends JPanel {
        private BufferedImage badgeImage;

        public CustomPanel() {
            // Load the badge image
            try {
                badgeImage = ImageIO.read(new File("src/school_badge.jpg")); // Update with your image path
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); // Always call this first
            if (badgeImage != null) {
                // Draw the badge image at the top center
                g.drawImage(badgeImage, (getWidth() - badgeImage.getWidth()) / 2, 20, this);
            }
        }
    }

    public static void main(String[] args) {
        // Create and display the dashboard

        SwingUtilities.invokeLater(() -> {
            Dashboard dashboard = new Dashboard();
            dashboard.setVisible(true);
            dashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });

    }


}