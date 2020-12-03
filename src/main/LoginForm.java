import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {

    public LoginForm(){

        GridLayout layout = new GridLayout(3,2);
        layout.setHgap(25);
        layout.setVgap(25);

        JPanel panel = new JPanel(layout);

        JLabel usernameLabel = new JLabel("USERNAME");
        JLabel passwordLabel = new JLabel("PASSWORD");

        usernameLabel.setHorizontalAlignment(JLabel.CENTER);
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);

        JTextField usernameField = new JTextField();
        JTextField passwordField = new JTextField();

        JButton loginButton =new JButton("LOGIN");//create button

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel(""));
        panel.add(loginButton);//adding button on frame

        add(panel, BorderLayout.CENTER);//adding button on frame

        setTitle("Login Form");
        setSize(600,300);
        //setLayout(null);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        System.out.println("DONE");
    }
}
