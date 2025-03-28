import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


    public class StudentForm extends JFrame {
        public void init() {
            setTitle("Add Student");
            setSize(500, 450);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setLayout(new FlowLayout());

            // Creating UI Components
            JLabel nameLabel = new JLabel("Name:");
            JTextField nameTextField = new JTextField(20);

            JLabel emailLabel = new JLabel("Email:");
            JTextField emailTextField = new JTextField(20);

            JLabel pnLabel = new JLabel("Phone Number:");
            JTextField pnTextField = new JTextField(20);


            JButton submitButton = new JButton("Submit");

            // Adding components to frame
            add(nameLabel);
            add(nameTextField);
            add(emailLabel);
            add(emailTextField);
            add(pnLabel);
            add(pnTextField);
            add(submitButton);

            // Action Listener for Submit Button
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = nameTextField.getText();
                    String email = emailTextField.getText();
                    int PhoneNumber = Integer.parseInt(pnTextField.getText());

                    if (name.isEmpty() || email.isEmpty() || PhoneNumber.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Create Student Object
                    Student student;
                    student = new Student(name, email, PhoneNumber);

                    // Save student to database
                    student.saveToDatabase();

                    JOptionPane.showMessageDialog(null, "Student Added: " + student.getname());
                }
            });

            setVisible(true);
        }

        public static void main(String[] args) {
            StudentForm studentForm = new StudentForm();
            studentForm.init();
        }
    }

