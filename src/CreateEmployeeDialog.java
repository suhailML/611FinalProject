import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * CreateEmployeeDialog
 * Allows the bank employee to create another employee in the system.
 *
 * @author ejbosia
 */

public class CreateEmployeeDialog extends JDialog {

    private JTextField usernameField, passwordField, firstNameField, lastNameField;

    private Bank bank;

    private JFrame parentFrame;

    public CreateEmployeeDialog(Bank bank, JFrame parentFrame){
        this.parentFrame = parentFrame;

        this.bank = bank;

        setSize(400,200);

        GridLayout infoPanelLayout = new GridLayout(1,2, 5,5);

        GridLayout labelPanelLayout = new GridLayout(5,1, 5,5);
        GridLayout textFieldPanelLayout = new GridLayout(5,1, 5,5);


        JPanel infoPanel = new JPanel(infoPanelLayout);
        JPanel labelPanel = new JPanel(labelPanelLayout);
        JPanel textFieldPanel = new JPanel(textFieldPanelLayout);

        usernameField = new JTextField();
        passwordField = new JTextField();
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


        setTitle("Create Employee");
        // set up the action selection buttons

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(null);
    }


    /** Update the title to match the account type selected **/
    private class CreateUserActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            String username = CreateEmployeeDialog.this.usernameField.getText();
            String password = CreateEmployeeDialog.this.passwordField.getText();
            String firstName = CreateEmployeeDialog.this.firstNameField.getText();
            String lastName = CreateEmployeeDialog.this.lastNameField.getText();

            username = username.replaceAll("\\s", "-");
            password = password.replaceAll("\\s", "-");
            firstName = firstName.replaceAll("\\s", "-");
            lastName = lastName.replaceAll("\\s", "-");

            if(username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty()){
                JOptionPane.showMessageDialog(CreateEmployeeDialog.this, "All fields must be filled", "Empty Field", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(bank.getBankRequestManager().createEmployee(bank, username, password, firstName, lastName)){
                JOptionPane.showMessageDialog(CreateEmployeeDialog.this, "Created employee " + firstName + " " + lastName, "Employee Created", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(CreateEmployeeDialog.this, "Employee creation failed.", "Employee Creation Error", JOptionPane.ERROR_MESSAGE);
            }





            CreateEmployeeDialog.this.dispose();
        }
    }


    /** Update the title to match the account type selected **/
    private class CancelActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Cancel");
            CreateEmployeeDialog.this.dispose();
        }
    }
}
