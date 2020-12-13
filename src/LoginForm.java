import sun.rmi.runtime.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            LoginForm.this.setVisible(false);

            // TODO check the login

            System.out.println("User login");
            new UserForm(bank, bank.getCustomers().get(0), LoginForm.this).setVisible(true); // Main Form to show after the Login Form..
        }
    }


    /** Login as an employee **/
    private class BankButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            System.out.println("Bank login");
            LoginForm.this.setVisible(false);

            //TODO Check the login

            new BankEmployeeForm(bank, new Employee(
                    usernameField.getText(),
                    passwordField.getText(),
                    "ABCDE",
                    "First Name",
                    "Last Name"
            ), LoginForm.this).setVisible(true); // Main Form to show after the Login Form..
        }
    }


    /** Create a user **/
    private class CreateUserActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Create a user selected");

            // TODO create user form
            new CreateUserDialog(bank,LoginForm.this).setVisible(true);


        }
    }
}