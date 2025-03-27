import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

    public class CourseForm extends JDialog {
        private JTextField tfcourse_id;
        private JTextField tfcourse_name;
        private JTextField tfcredits;
        private JTextField tfinstructor;
        private JButton btnRegister;
        private JButton btnCancel;
        private JPanel RegisterPanel;

        public CourseForm(JFrame parent){
            super(parent);
            setTitle("Register new courses");
            RegisterPanel= new JPanel();
            setContentPane(RegisterPanel);
            setMinimumSize(new Dimension(600,500));
//        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
//        gd.setFullScreenWindow(this);
            setModal(true);
            setLocationRelativeTo(parent);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            btnRegister.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    enrollCourse();
                }

            });
            btnCancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            setVisible(true);
        }

        public void enrollCourse() {
            String course_id = tfcourse_id.getText();
            String course_name = tfcourse_name.getText();
            String credits = tfcredits.getText();
            String instructor = tfinstructor.getText();

            if(course_id.isEmpty() || course_name.isEmpty() || credits.isEmpty() || instructor.isEmpty()){
                JOptionPane.showMessageDialog(this,
                        "Please enter all fields",
                        "Try again",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }


            Course Course = addCourseToDatabase(course_id, course_name, Integer.parseInt(credits), instructor);

            if (course != null){
                dispose();
            }
            else{
                JOptionPane.showMessageDialog(this,
                        "Failed to register",
                        "Try again",
                        JOptionPane.ERROR_MESSAGE);
            }
        }


        public Course course;

        private Course addCourseToDatabase(String course_name,String course_id, int credits , String instructor){
            Course course = null;
            final String DB_URL = "jdbc:postgresql://localhost/test";
            final String USERNAME = "postgres";
            final String PASSWORD = "";

            try{
                Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
                Statement stat = conn.createStatement();
                String sql = "INSERT INTO courses(course id,course name,credits,instructor) " +
                        "VALUES(?,?,?,?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1,course_id);
                preparedStatement.setString(2,course_name);
                preparedStatement.setString(3, String.valueOf(credits));
                preparedStatement.setString(4,instructor);

                int addedrows = preparedStatement.executeUpdate();
                if (addedrows>0){
                    course = new Course();
                    course.course_id = course_id;
                    course.course_name = course_name;
                    course.credits = credits;
                    course.instructor = instructor;
                }

                stat.close();
                conn.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }

            return course;
        }

        public static void main(String[] args) {
            CourseForm myform;
            myform = new CourseForm(null);
            myform.setVisible(true);
            Course course = myform.course;
            if (course != null){
                System.out.println("Successful registration of: "+ course.course_name);
            }
            else {
                System.out.println("Registration Cancelled");
            }
        }
    }

