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
        setSize(500, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        panel = new CustomPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.gray);


        JButton viewCoursesButton = new JButton("View Courses");
        JButton viewStudentsButton = new JButton("View Students");
        JButton viewInstructorsButton = new JButton("View Instructors");
        JButton add_InstructorsButton = new JButton("Add Instructors");
        JButton addUserButton = new JButton("Add Users");
        JButton addCoursesButton = new JButton("Add Courses");
        JButton addStudentsButton = new JButton("Add Students");
        JButton addenrollButton = new JButton("Enroll Students");
        JButton viewBarButton = new JButton("View BarChart");
        JButton viewPieButton = new JButton("View PieChart");






        viewCoursesButton.addActionListener(e -> displayCourses());
        viewStudentsButton.addActionListener(e -> displayStudents());
        viewInstructorsButton.addActionListener(e -> displayInstructors());
        viewBarButton.addActionListener(e -> displayBar());
        viewPieButton.addActionListener(e -> displayPie());
        add_InstructorsButton.addActionListener(e -> displayFinal_instructors());
        addUserButton.addActionListener(e -> displayuser());
        addenrollButton.addActionListener(e -> displayenrolled());
        addCoursesButton.addActionListener(e -> displayMyCourses());
        addStudentsButton.addActionListener(e -> displayMyStudents());




        styleButton(viewCoursesButton);
        styleButton(viewStudentsButton);
        styleButton(viewBarButton);
        styleButton(viewPieButton);
        styleButton(viewInstructorsButton);
        styleButton(add_InstructorsButton);
        styleButton(addUserButton);
        styleButton(addCoursesButton);
        styleButton(addStudentsButton);
        styleButton(addenrollButton);




        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(viewCoursesButton);
        panel.add(viewCoursesButton);
        panel.add(Box.createRigidArea(new Dimension(0, 30))); // Spacing between buttons
        panel.add(viewStudentsButton);
        panel.add(Box.createRigidArea(new Dimension(0, 30))); // Spacing between buttons
        panel.add(viewInstructorsButton);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(add_InstructorsButton);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(addUserButton);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(addCoursesButton);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(addStudentsButton);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(viewBarButton);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(viewPieButton);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(addenrollButton);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));




        add(panel);


        connectToDatabase();



    }





    private void connectToDatabase() {
        try {
            String DB_URL = "jdbc:postgresql://localhost:5432/test"; // Update with your database URL
            String USER = "postgres"; // Update with your database username
            String PASSWORD = "100100"; // Update with your database password
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Connected to the database successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database connection failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }




    private void displayCourses() {
        StringBuilder courses = new StringBuilder("Courses:\n");
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM courses")) {
            while (rs.next()) {
                courses.append(rs.getString("course_id")).append("\n");
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
                students.append(rs.getString("student_id")).append("\n");
                students.append(rs.getString("student_name")).append("\n");
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
             ResultSet rs = stmt.executeQuery("SELECT * FROM Instructors")) {
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
    private void displayMyCourses() {

        SwingUtilities.invokeLater(() -> new Dashboard());
        JFrame frame = new JFrame("My Frame");
        JButton add_courses = new JButton("Add Courses");

        add_courses.addActionListener(e ->displayMyCourses());

        Mainframe myFrame = new Mainframe();
        myFrame.init();  // Start the "Add Courses" form
        frame.dispose();
    }

    private void displayenrolled() {

        SwingUtilities.invokeLater(() -> new Dashboard());
        JFrame frame = new JFrame("My Frame");
        JButton add_courses = new JButton("Enroll Students");

        add_courses.addActionListener(e ->displayenrolled());

        en_roll_ment enRollMent = new en_roll_ment();
        enRollMent.init();  // Start the "Add Courses" form
        frame.dispose();
    }

    private void displayBar() {

        SwingUtilities.invokeLater(() -> new Dashboard());
        JFrame frame = new JFrame("My Frame");
        JButton add_courses = new JButton("Bar Chart");

        add_courses.addActionListener(e ->displayBar());

        CourseEnrollmentChart chart = new CourseEnrollmentChart();
        chart.setVisible(true);
        frame.dispose();
    }

    private void displayPie() {

        SwingUtilities.invokeLater(() -> new Dashboard());
        JFrame frame = new JFrame("My Frame");
        JButton add_courses = new JButton("Pie Chart");

        add_courses.addActionListener(e ->displayBar());

        GradeDistributionChart chart = new GradeDistributionChart();
        chart.setVisible(true);
        frame.dispose();
    }

    private void displayMyStudents() {

        SwingUtilities.invokeLater(() -> new Dashboard());
        JFrame frame = new JFrame("My Frame");
        JButton add_courses = new JButton("Add Students");

        add_courses.addActionListener(e ->displayMyStudents());

        StudentForm studentForm = new StudentForm();
        studentForm.init();
        frame.dispose();
    }



    private void displayuser(){
        SwingUtilities.invokeLater(() -> new Dashboard());
        JFrame frame = new JFrame("My Frame");
        JButton add_students = new JButton("Add Users");

        add_students.addActionListener(e ->displayuser());

        Registration_Form myform = new Registration_Form(null);
        frame.dispose();




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
