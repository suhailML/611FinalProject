import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateUserDialog extends JDialog {

    private JTextField usernameField, passwordField, firstNameField, lastNameField;

    private Bank bank;

    private JFrame parentFrame;

    public CreateUserDialog(Bank bank, String username, String password, JFrame parentFrame){
        this.parentFrame = parentFrame;

        this.bank = bank;

        setSize(400,200);

        GridLayout infoPanelLayout = new GridLayout(1,2, 5,5);

        GridLayout labelPanelLayout = new GridLayout(5,1, 5,5);
        GridLayout textFieldPanelLayout = new GridLayout(5,1, 5,5);


        JPanel infoPanel = new JPanel(infoPanelLayout);
        JPanel labelPanel = new JPanel(labelPanelLayout);
        JPanel textFieldPanel = new JPanel(textFieldPanelLayout);

        usernameField = new JTextField(username);
        passwordField = new JTextField(password);
        firstNameField = new JTextField();
        lastNameField = new JTextField();

        usernameField.setPreferredSize(new Dimension(200, 24));
        passwordField.setPreferredSize(new Dimension(200, 24));
        firstNameField.setPreferredSize(new Dimension(200, 24));
        lastNameField.setPreferredSize(new Dimension(200, 24));



        labelPanel.add(new JLabel("Username"));
        textFieldPanel.add(usernameField);

        labelPanel.add(new JLabel("Password"));
        textFieldPanel.add(passwordField);


        labelPanel.add(new JLabel("First Name"));
        textFieldPanel.add(firstNameField);


        labelPanel.add(new JLabel("Last Name"));
        textFieldPanel.add(lastNameField);


        infoPanel.add(labelPanel);
        infoPanel.add(textFieldPanel);

        JPanel actionPanel = new JPanel();

        add(infoPanel, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);


        JButton confirmButton = new JButton("Confirm");
        JButton cancelButton = new JButton("Cancel");

        confirmButton.addActionListener(new CreateUserActionListener());
        cancelButton.addActionListener(new CancelActionListener());

        actionPanel.add(confirmButton);
        actionPanel.add(cancelButton);


        setTitle("Create User");
        // set up the action selection buttons

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(null);
    }


    /** Update the title to match the account type selected **/
    private class CreateUserActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            String username = CreateUserDialog.this.usernameField.getText();
            String password = CreateUserDialog.this.passwordField.getText();
            String firstName = CreateUserDialog.this.firstNameField.getText();
            String lastName = CreateUserDialog.this.lastNameField.getText();

            username = username.replaceAll("\\s", "-");
            password = password.replaceAll("\\s", "-");
            firstName = firstName.replaceAll("\\s", "-");
            lastName = lastName.replaceAll("\\s", "-");

            System.out.println("TODO Create Customer!");


            Customer customer = bank.getBankRequestManager().createCustomer(bank, username, password, firstName, lastName);

            if(){
                JOptionPane.showMessageDialog(CreateUserDialog.this, "Created user " + customer, "Customer Create", JOptionPane.INFORMATION_MESSAGE);

                //TODO how do we get the customer to add the account to?
                new AddAccountDialog(bank,customer, CreateUserDialog.this.parentFrame).setVisible(true);

            }
            else{
                JOptionPane.showMessageDialog(CreateUserDialog.this, "User creation failed.", "User Creation Error", JOptionPane.ERROR_MESSAGE);
            }





            CreateUserDialog.this.dispose();
        }
    }


    /** Update the title to match the account type selected **/
    private class CancelActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Cancel");
            CreateUserDialog.this.dispose();
        }
    }
}
