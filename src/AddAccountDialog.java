import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAccountDialog extends JDialog {

    private JTextField nameField;

    private JComboBox currencyBox;

    private JRadioButton savingsRButton;
    private JRadioButton checkingRButton;
    private JRadioButton securitiesRButton;

    private JLabel titleLabel;

    private Bank bank;
    private Customer customer;


    JSpinner depositSpinner;

    public AddAccountDialog(Bank bank, Customer customer, JFrame parentFrame){
        super(parentFrame);

        this.bank = bank;
        this.customer = customer;

        titleLabel = new JLabel("Create Account: Savings");

        setSize(400,400);


        GridLayout infoPanelLayout = new GridLayout(4,1, 5,5);

        JPanel infoPanel = new JPanel(infoPanelLayout);
        JPanel actionPanel = new JPanel();
        JPanel radioPanel = new JPanel();

        add(titleLabel, BorderLayout.NORTH);
        add(infoPanel, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);

        nameField = new JTextField();
        currencyBox = new JComboBox<Character>(bank.getSettings().getCurrencyArray());

        nameField.setPreferredSize(new Dimension(200, 24));


        JPanel namePanel = new JPanel();
        JPanel currencyPanel = new JPanel();

        namePanel.add(new JLabel("Name:"));
        namePanel.add(nameField);
        currencyPanel.add(new JLabel("Currency:"));
        currencyPanel.add(currencyBox);

        JPanel depositPanel = new JPanel();

        depositSpinner = new JSpinner(new SpinnerNumberModel(100,100,Integer.MAX_VALUE,100));

        depositPanel.add(new JLabel("Deposit:"));
        depositPanel.add(depositSpinner);

        initRadioButtons();

        infoPanel.add(namePanel);
        infoPanel.add(currencyPanel);
        infoPanel.add(depositPanel);
        radioPanel.add(savingsRButton);
        radioPanel.add(checkingRButton);
        radioPanel.add(securitiesRButton);
        infoPanel.add(radioPanel);

        JButton confirmButton = new JButton("Confirm");
        JButton cancelButton = new JButton("Cancel");

        confirmButton.addActionListener(new CreateAccountButtonHandler());
        cancelButton.addActionListener(new CancelButtonHandler());

        actionPanel.add(confirmButton);
        actionPanel.add(cancelButton);


        setTitle("Add Account");
        // set up the action selection buttons

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        pack();
        setLocationRelativeTo(null);
    }

    private void initRadioButtons(){
        // set up radio button
        savingsRButton = new JRadioButton("Savings");
        savingsRButton.setActionCommand("Savings");
        savingsRButton.setSelected(true);
        checkingRButton = new JRadioButton("Checking");
        checkingRButton.setActionCommand("Checking");
        checkingRButton.setSelected(false);
        securitiesRButton = new JRadioButton("Securities");
        securitiesRButton.setActionCommand("Securities");
        securitiesRButton.setSelected(false);
        securitiesRButton.setEnabled(false);

        // change the title to match the account
        savingsRButton.addActionListener(new AccountTypeRadioButtonHandler());
        checkingRButton.addActionListener(new AccountTypeRadioButtonHandler());
        securitiesRButton.addActionListener(new AccountTypeRadioButtonHandler());

        ButtonGroup group = new ButtonGroup();
        group.add(savingsRButton);
        group.add(checkingRButton);
        group.add(securitiesRButton);

    }

    /** Update the title to match the account type selected **/
    private class AccountTypeRadioButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            AddAccountDialog.this.titleLabel.setText("Create Account: " + e.getActionCommand());
        }
    }

    /** Update the title to match the account type selected **/
    private class CreateAccountButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            String name = AddAccountDialog.this.nameField.getText();
            String currency = currencyBox.getSelectedItem().toString();

            int accountType = -1;

            // select the right account type
            if(savingsRButton.isSelected()){
                System.out.println("Create savings account!");
                accountType = 0; // = new SavingsAccount(name, "NEED ID", currency);
            }
            else if(checkingRButton.isSelected()) {
                System.out.println("Create checking account!");
                accountType = 1; // = new CheckingAccount();
            }

            else if(securitiesRButton.isSelected()) {
                System.out.println("Create securities account!");
                // not implemented
                accountType = 2;
            }

            if(name.isEmpty() || currency.isEmpty()){
                JOptionPane.showMessageDialog(AddAccountDialog.this, "All fields must be filled", "Empty Field", JOptionPane.ERROR_MESSAGE);
                return;
            }

            name = name.replaceAll("\\s", "-");
            double deposit = 0;
            try {
                 deposit = ((Integer)depositSpinner.getValue()).doubleValue();
            }
            catch(NumberFormatException nfe){
                JOptionPane.showMessageDialog(AddAccountDialog.this, "NUMBER REQUIRED FOR ACCOUNT");
                return;
            }

            BankAccount account = bank.getBankRequestManager().createAccount( bank, customer, name, currency, accountType, deposit);

            // make a direct deposit
            if(account != null){
                System.out.println(account);
                JOptionPane.showMessageDialog(AddAccountDialog.this, "Created account: " + account, "Account Created", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(AddAccountDialog.this, "Failed to create account", "Account Creation Error", JOptionPane.ERROR_MESSAGE);
            }

            AddAccountDialog.this.dispose();
        }
    }

    /** Update the title to match the account type selected **/
    private class CancelButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Cancel");
            AddAccountDialog.this.dispose();
        }
    }
}
