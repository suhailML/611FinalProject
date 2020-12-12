import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {

    private JTextField usernameField, passwordField;

    private Bank bank;

    public LoginForm(Bank bank){

        this.bank = bank;

        GridLayout layout = new GridLayout(3,2);
        layout.setHgap(10);
        layout.setVgap(10);

        JPanel panel = new JPanel(layout);

        JLabel usernameLabel = new JLabel("USERNAME");
        JLabel passwordLabel = new JLabel("PASSWORD");

        usernameLabel.setHorizontalAlignment(JLabel.CENTER);
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);

        usernameField = new JTextField();
        passwordField = new JTextField();

        JButton loginButton =new JButton("LOGIN");//create button

        loginButton.addActionListener(new LoginButtonHandler());

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel(""));
        panel.add(loginButton);//adding button on frame

        add(panel, BorderLayout.CENTER);//adding button on frame

        setTitle("Login Form");
        setSize(400,150);
        //setLayout(null);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class LoginButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            LoginForm.this.setVisible(false);
            new UserForm(bank, new Customer(
                    usernameField.getText(),
                    passwordField.getText(),
                    "ABCDE",
                    "First Name",
                    "Last Name"
            ), LoginForm.this).setVisible(true); // Main Form to show after the Login Form..
        }
    }
}
