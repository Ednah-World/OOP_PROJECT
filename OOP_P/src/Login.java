import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame {
    protected JLabel jlUsername;
    protected JTextField UsernameField;
    protected JButton LoginButton;
    protected JPanel MainPanel;
    protected JPasswordField PasswordField;
    protected JLabel jlPassword;
    protected JButton SettingsButton;
    protected Connection conn;

    public Login() {
        Main db = new Main();
        conn = db.connectToDatabase("oop_project", "postgres", "Murungii");
        setContentPane(MainPanel);
        setTitle("Login page");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
        setBackgroundImage();

        jlUsername.setBounds(50, 50, 80, 30);
        UsernameField.setBounds(150, 50, 200, 30);
        UsernameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PasswordField.requestFocus();
            }
        });

        jlPassword.setBounds(50, 100, 80, 30);
        PasswordField.setBounds(150, 100, 200, 30);
        PasswordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginButton.doClick();
            }
        });

        SettingsButton.setBounds(300,220,90,30);
        SettingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSettings();
            }
        });

        LoginButton.setBounds(150, 150, 100, 30);

        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Name = (UsernameField.getText());
                char[] passwordArray = PasswordField.getPassword();
                String password = new String(passwordArray);

                if ("User".equals(Name) && "Password".equals(password)) {
                    JOptionPane.showMessageDialog(Login.this, "Successfully logged in.");
                    openUserPage(Name);
                }
                else {
                    JOptionPane.showMessageDialog(Login.this, "Error: Invalid details.");
                    UsernameField.setText("");
                    PasswordField.setText("");
                    UsernameField.requestFocus();
                }
            }
        });
    }

    protected void setBackgroundImage(){
        ImageIcon background = new ImageIcon("C:/Users/allan/Downloads/Background Image.jpg");
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        MainPanel.add(backgroundLabel);
    }

    protected String getphone(String Name){
        String phone = null;
        try{
            String query = "SELECT phone FROM student, staff WHERE student_id, staff_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, Name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                phone= resultSet.getString("phone");
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            //JOptionPane.showMessageDialog(this, "Failed to fetch phone number. "+ e.getMessage());
        }
        return phone;
    }

    protected String hidephone(String phone){
        if (phone != null && phone.length()>4){
            return phone.substring(0,2)+ "*****" + phone.substring(phone.length() -2);
        }
        return phone;
    }

    protected void openUserPage(String username) {
        JOptionPane.showMessageDialog(this, "Welcome " + username);
    }

    protected void openSettings(){
        String [] options = {"Font size", "Background Theme", "Forgot Password"};
        int option = JOptionPane.showOptionDialog(this, "Settings", "Select Option", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (option){
            case 0:
                String FontSize = JOptionPane.showInputDialog(this, "Enter font size ():");
                if (FontSize != null){
                    int size = Integer.parseInt(FontSize);
                    jlUsername.setFont(new Font("Arial", Font.PLAIN, size));
                    UsernameField.setFont(new Font("Arial", Font.PLAIN, size));
                    jlPassword.setFont(new Font("Arial", Font.PLAIN, size));
                    PasswordField.setFont(new Font("Arial", Font.PLAIN, size));
                    SettingsButton.setFont(new Font("Arial", Font.PLAIN, size));
               }
                break;
            case 1:
                String[] Themes = {"Cyan", "Grey", "Red", "Yellow", "Blue"};
                String ThemeChoice = (String) JOptionPane.showInputDialog(this, "Select Background Theme:", "Theme", JOptionPane.QUESTION_MESSAGE, null, Themes, Themes[0]);
                if(ThemeChoice != null){
                    switch (ThemeChoice){
                        case "Cyan":
                            MainPanel.setBackground(Color.CYAN);
                            break;
                        case "Grey":
                            MainPanel.setBackground(Color.GRAY);
                            break;
                        case "Red":
                            MainPanel.setBackground(Color.RED);
                            break;
                        case "Yellow":
                            MainPanel.setBackground(Color.YELLOW);
                            break;
                        case "Blue":
                            MainPanel.setBackground(Color.BLUE);
                            break;
                    }
                }
                break;
            case 2:
                String Name = JOptionPane.showInputDialog("Enter your username: ");
                if (Name != null && !Name.isEmpty()){
                    String phone = getphone(Name);
                    if (phone != null){
                        String hidephone = hidephone(phone);
                        JOptionPane.showMessageDialog(Login.this, "A password reset link has been sent to " +hidephone);
                    }else {
                        JOptionPane.showMessageDialog(Login.this, "Username not found.");
                    }
               }else {
                    JOptionPane.showMessageDialog(Login.this, "Username cannot be empty. Provide a valid username.");
                }
                break;
        }

    }
    public static void main(String[] args) {
        new Login();
    }
}
