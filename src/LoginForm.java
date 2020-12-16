
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * LoginForm
 * The login form is the entry point for the program. It allows the user to enter credentials as an employee or a user.
 *
 * @author ejbosia
 */

public class LoginForm extends JFrame {

    private JTextField usernameField, passwordField;

    private Bank bank;

    public LoginForm(Bank bank){

        this.bank = bank;

        GridLayout frameLayout = new GridLayout(3,1, 0, 10);
        GridLayout textLayout = new GridLayout(1,2, 5, 0);
        GridLayout actionLayout = new GridLayout(1,3, 5, 0);

        JPanel usernamePanel = new JPanel(textLayout);
        JPanel passwordPanel = new JPanel(textLayout);
        JPanel actionPanel = new JPanel(actionLayout);


        JPanel framePanel = new JPanel(frameLayout);

        JLabel usernameLabel = new JLabel("USERNAME");
        JLabel passwordLabel = new JLabel("PASSWORD");

        usernameLabel.setHorizontalAlignment(JLabel.CENTER);
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);

        usernameField = new JTextField();
        passwordField = new JTextField();

        JButton loginButton =new JButton("USER LOGIN");//create button

        loginButton.addActionListener(new LoginButtonActionListener());

        JButton bankButton =new JButton("BANK LOGIN");//create button

        bankButton.addActionListener(new BankButtonActionListener());

        JButton createUserButton =new JButton("Create User");//create button

        createUserButton.addActionListener(new CreateUserActionListener());

        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        actionPanel.add(bankButton);
        actionPanel.add(createUserButton);
        actionPanel.add(loginButton);//adding button on frame

        framePanel.add(usernamePanel);
        framePanel.add(passwordPanel);
        framePanel.add(actionPanel);

        add(framePanel, BorderLayout.CENTER);//adding button on frame

        setTitle("Login Form");
        setSize(400,150);
        //setLayout(null);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    /** Login as a user **/
    private class LoginButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            String username = usernameField.getText();
            String password = passwordField.getText();

            Customer customer = bank.getBankRequestManager().checkCustomerLogin(bank, username, password);

            System.out.println("User login: " + username + " " + password);

            if (customer == null) {
                JOptionPane.showMessageDialog(LoginForm.this, "Customer not found with these credentials", "Customer not Found", JOptionPane.ERROR_MESSAGE);
                passwordField.setText("");
            }
            else {
                LoginForm.this.setVisible(false);
                new UserForm(bank, bank.getCustomer(username, password), LoginForm.this).setVisible(true); // Main Form to show after the Login Form..

                usernameField.setText("");
                passwordField.setText("");
            }
        }
    }


    /** Login as an employee **/
    private class BankButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            String username = usernameField.getText();
            String password = passwordField.getText();

            System.out.println("Employee login " + username + " " + password);
            Employee employee = bank.getBankRequestManager().checkEmployeeLogin(bank, username, password);

            if (employee == null) {
                JOptionPane.showMessageDialog(LoginForm.this, "Employee not found with these credentials", "Employee not Found", JOptionPane.ERROR_MESSAGE);
                passwordField.setText("");
            }
            else{
                LoginForm.this.setVisible(false);

                new BankEmployeeForm(bank, employee, LoginForm.this).setVisible(true); // Main Form to show after the Login Form..

                usernameField.setText("");
                passwordField.setText("");
            }
        }
    }


    /** Create a user **/
    private class CreateUserActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Create a user selected");
            new CreateUserDialog(bank, usernameField.getText(), passwordField.getText(), LoginForm.this).setVisible(true);
        }
    }
}
