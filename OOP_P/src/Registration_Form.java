import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Registration_Form extends JDialog{
    private JTextField tfName;
    private JTextField tfEmail;
    private JTextField tfPhone;
    private JTextField tfAddress;
    private JPasswordField pfPassword;
    private JPasswordField pfConfirmpassword;
    private JButton btnRegister;
    private JButton btnCancel;
    private JPanel RegisterPanel;

    public Registration_Form(JFrame parent){
        super(parent);
        setTitle("Register new students");
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
                enrollUser();
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

    public void enrollUser() {
        String name = tfName.getText();
        String email = tfEmail.getText();
        String phone = tfPhone.getText();
        String address = tfAddress.getText();
        String password = String.valueOf(pfPassword.getPassword());
        String confirmPassword = String.valueOf(pfConfirmpassword.getPassword());

        if(name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(!password.equals(confirmPassword)){
            JOptionPane.showMessageDialog(this,
                    "Confirm Password does not match",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        user = addUserToDatabase(name,email,phone,address,password);

        if (user != null){
            dispose();
        }
        else{
            JOptionPane.showMessageDialog(this,
                    "Failed to register",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    public User user;

    private User addUserToDatabase(String name,String email, String phone, String address, String password){
        User user = null;
        final String DB_URL = "jdbc:postgresql://localhost/test";
        final String USERNAME = "postgres";
        final String PASSWORD = "";

        try{
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stat = conn.createStatement();
            String sql = "INSERT INTO registration(name,email,phone,address,password) " +
                    "VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,phone);
            preparedStatement.setString(4,address);
            preparedStatement.setString(5,password);

            int addedrows = preparedStatement.executeUpdate();
            if (addedrows>0){
                user = new User();
                user.name = name;
                user.email = email;
                user.phone = phone;
                user.address = address;
                user.password = password;
            }

            stat.close();
            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }

    public static void main(String[] args) {
        Registration_Form myform = new Registration_Form(null);
        User user = myform.user;
        if (user != null){
            System.out.println("Successful registration of: "+ user.name);
        }
        else {
            System.out.println("Registration Cancelled");
        }
    }
}
