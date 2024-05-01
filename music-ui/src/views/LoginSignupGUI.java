package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginSignupGUI extends JFrame implements Window {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupButton;
    private JLabel messageLabel;

    public LoginSignupGUI()  {

    }

    public void createAndShowUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLayout(null); // Using absolute positioning for components
        setResizable(false); // Window is not resizable

        JLabel titleLabel = new JLabel("School Portal");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(120, 20, 200, 30);
        add(titleLabel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 70, 80, 20);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(140, 70, 200, 25);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 110, 80, 20);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(140, 110, 200, 25);
        add(passwordField);

        JLabel userTypeLabel = new JLabel("User Type:");
        userTypeLabel.setBounds(50, 150, 80, 20);
        add(userTypeLabel);


        loginButton = new JButton("Login");
        loginButton.setBounds(100, 190, 80, 30);
        add(loginButton);

        signupButton = new JButton("Signup");
        signupButton.setBounds(200, 190, 80, 30);
        add(signupButton);

        messageLabel = new JLabel();
        messageLabel.setBounds(50, 230, 300, 20);
        add(messageLabel);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                // Perform login logic here based on userType
            }
        });

        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                // Perform signup logic here based on userType
            }
        });

        setVisible(true);
    }


}
