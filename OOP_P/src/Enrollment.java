import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Enrollment extends JFrame {
    protected JLabel jlAdministratorName;
    protected JTextField AdmNameField;
    protected JTextField StaffIdField;
    protected JLabel jlStaffID;
    protected JTable tStudents;
    protected JPanel EnrollmentPanel;

    public Enrollment() {

        setContentPane(EnrollmentPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(new BorderLayout());
        setVisible(true);


        String[] studentColumns = {"Student ID", "Student Name", "Course ID", "Course Name"};
        Object[][] studentData = {};
        DefaultTableModel studentModel = new DefaultTableModel(studentData, studentColumns);
        JTable studentTable = new JTable(studentModel);
        studentTable.setCellSelectionEnabled(true);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane studentScrollPane = new JScrollPane(studentTable);
        add(studentScrollPane, BorderLayout.NORTH);


        String[] instructorColumns = {"Instructor ID", "Instructor Name", "Course ID", "Course Name"};
        Object[][] instructorData = {};
        DefaultTableModel instructorModel = new DefaultTableModel(instructorData, instructorColumns);
        JTable instructorTable = new JTable(instructorModel);
        instructorTable.setCellSelectionEnabled(true);
        instructorTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane instructorScrollPane = new JScrollPane(instructorTable);
        add(instructorScrollPane, BorderLayout.CENTER);


        JButton addStudentButton = new JButton("Add Student");
        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentModel.addRow(new Object[]{"", "", "", ""});
            }
        });


        JButton addInstructorButton = new JButton("Add Instructor");
        addInstructorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructorModel.addRow(new Object[]{"", "", "", ""});
            }
        });


        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Enrollment successful.");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addStudentButton);
        buttonPanel.add(addInstructorButton);
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        new Enrollment();
    }
}