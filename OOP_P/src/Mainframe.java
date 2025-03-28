import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

        public class Mainframe extends JFrame {
            public void init() {
                setTitle("Add Course");
                setSize(500, 450);
                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                setLayout(new FlowLayout());// Set layout for components
                Course course= new Course();

                // Creating UI Components
                JLabel label = new JLabel("Course ID:");
                JTextField textField = new JTextField(20);

                // Adding components to frame
                add(label);
                add(textField);


                JLabel idlabel = new JLabel("Course Name:");
                JTextField idtextField = new JTextField(20);
                add(idlabel);
                add(idtextField);

                JLabel crlabel = new JLabel("Credits:");
                JTextField crtextField = new JTextField(20);
                add(crlabel);
                add(crtextField);

                JLabel instlabel = new JLabel("Instructor:");
                JTextField insttextField = new JTextField(20);
                add(instlabel);
                add(insttextField);

                JButton button = new JButton("Submit");
                add(button);

                // Adding action listener to the button
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String course_id = idtextField.getText();
                        String course_name = textField.getText();
                        String credits = crtextField.getText();
                        String instructor = insttextField.getText();
                        JOptionPane.showMessageDialog(null, "Course Added: " + course_name);
                    }
                });

                setVisible(true);
            }

            private void insertIntoCourses(String course_id,String course_name, String credits, String instructor) {
                String url = "jdbc:mysql://localhost:5432/OOP_Projct"; // Change to your database
                String user = "postgres"; // Change to your MySQL username
                String password = "Wanjiru1806"; // Change to your MySQL password

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
            }

            public static void main(String[] args) {
                Mainframe myFrame = new Mainframe();
                myFrame.init();
            }
        }


