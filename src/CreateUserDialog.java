import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateUserDialog extends JDialog {

    private JTextField usernameField, passwordField, firstNameField, lastNameField;

    private Bank bank;


    public CreateUserDialog(Bank bank, JFrame parentFrame){
        super(parentFrame);

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

            // TODO call method of creation

            System.out.println("TODO Create Customer!");
            System.out.println(new UserFactory().createNewCustomer(username, password, firstName, lastName));

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
