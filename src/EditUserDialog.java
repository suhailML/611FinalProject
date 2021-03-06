import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * EditUserDialog
 * Allows the user to edit their password, firstname, and lastname.
 *
 * @author ejbosia
 */
public class EditUserDialog extends JDialog {

    private JTextField usernameField, passwordField, firstNameField, lastNameField;

    private Bank bank;
    private Customer customer;

    private JFrame parentFrame;

    public EditUserDialog(Bank bank, Customer customer, JFrame parentFrame){
        this.parentFrame = parentFrame;

        this.bank = bank;
        this.customer = customer;

        setSize(400,200);

        GridLayout infoPanelLayout = new GridLayout(1,2, 5,5);

        GridLayout labelPanelLayout = new GridLayout(5,1, 5,5);
        GridLayout textFieldPanelLayout = new GridLayout(5,1, 5,5);

        JPanel infoPanel = new JPanel(infoPanelLayout);
        JPanel labelPanel = new JPanel(labelPanelLayout);
        JPanel textFieldPanel = new JPanel(textFieldPanelLayout);

        // fill in the existing customer data
        usernameField = new JTextField(customer.getUsername());
        passwordField = new JTextField(customer.getPassword());
        firstNameField = new JTextField(customer.getFirstName());
        lastNameField = new JTextField(customer.getLastName());

        usernameField.setPreferredSize(new Dimension(200, 24));
        passwordField.setPreferredSize(new Dimension(200, 24));
        firstNameField.setPreferredSize(new Dimension(200, 24));
        lastNameField.setPreferredSize(new Dimension(200, 24));

        // disable the username
        usernameField.setEnabled(false);

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

        confirmButton.addActionListener(new EditUserActionListener());
        cancelButton.addActionListener(new CancelActionListener());

        actionPanel.add(confirmButton);
        actionPanel.add(cancelButton);


        setTitle("Edit User");
        // set up the action selection buttons

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(null);
    }


    /** Update the title to match the account type selected **/
    private class EditUserActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            String password = EditUserDialog.this.passwordField.getText();
            String firstName = EditUserDialog.this.firstNameField.getText();
            String lastName = EditUserDialog.this.lastNameField.getText();

            password = password.replaceAll("\\s", "-");
            firstName = firstName.replaceAll("\\s", "-");
            lastName = lastName.replaceAll("\\s", "-");

            if(bank.getBankRequestManager().updateCustomer(bank, customer, firstName, lastName, password)){
                JOptionPane.showMessageDialog(EditUserDialog.this, "Edited user " + customer.getFirstName() + " " + customer.getLastName(), "Edit User", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(EditUserDialog.this, "User Edit failed.", "User Edit Error", JOptionPane.ERROR_MESSAGE);
            }

            EditUserDialog.this.dispose();
        }
    }


    /** Update the title to match the account type selected **/
    private class CancelActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Cancel");
            EditUserDialog.this.dispose();
        }
    }
}
