import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoanCreateDialog extends JDialog {

    private JComboBox<Transferable> senderComboBox;

    private JSpinner amountSpinner;
    private JTextField collateralField;

    JLabel senderValueLabel;

    private JLabel titleLabel;

    private Bank bank;
    private Customer customer;

    public LoanCreateDialog(Bank bank, Customer customer, JFrame parentFrame){
        super(parentFrame);

        this.bank = bank;
        this.customer = customer;

        titleLabel = new JLabel("Request a Loan");

        setSize(400,200);

        GridLayout infoPanelLayout = new GridLayout(1,2, 5,5);

        JPanel infoPanel = new JPanel(infoPanelLayout);
        JPanel actionPanel = new JPanel();

        add(titleLabel, BorderLayout.NORTH);
        add(infoPanel, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);

        // initialize combo box
        senderComboBox = new JComboBox<>();

        initComboBox(senderComboBox, customer.getAccounts());

        // update sender when the sender combo box changes
        senderComboBox.addActionListener(e -> {
            senderValueLabel.setText("$" + ((BankAccount)senderComboBox.getSelectedItem()).getBalance());
        });


        senderValueLabel = new JLabel("$" + ((BankAccount)senderComboBox.getSelectedItem()).getBalance());

        collateralField = new JTextField();

        GridLayout rowLayout = new GridLayout(2,1, 5,5);

        JPanel labelPanel = new JPanel(rowLayout);
        JPanel dataPanel = new JPanel(rowLayout);

        labelPanel.add(new JLabel("Receiving Account:"));
        dataPanel.add(senderComboBox);
        labelPanel.add(new JLabel("Collateral:"));
        labelPanel.add(collateralField);

        infoPanel.add(labelPanel);
        infoPanel.add(dataPanel);

        JButton confirmButton = new JButton("Confirm");
        JButton cancelButton = new JButton("Cancel");

        confirmButton.addActionListener(new ConfirmTransactionActionListener());
        cancelButton.addActionListener(new CancelButtonActionListener());


        System.out.println("\tSET THE LOAN MAX VALUE TO A REAL NUMBER! RIGHT NOW IT IS 10000");
        amountSpinner = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 10000, 100.0));

        amountSpinner.setPreferredSize(new Dimension(200, 20));

        actionPanel.add(amountSpinner);
        actionPanel.add(confirmButton);
        actionPanel.add(cancelButton);

        setTitle("Request Loan");

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        pack();
        setLocationRelativeTo(null);

    }

    /** Initialize the combo box with the right values **/
    private void initComboBox(JComboBox comboBox, List valuesList){

        for(Object value : valuesList){
            comboBox.addItem(value);
        }
    }


    /** Update the title to match the account type selected **/
    private class ConfirmTransactionActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            int lendeeIndex = LoanCreateDialog.this.senderComboBox.getSelectedIndex();

            double money = (Double) LoanCreateDialog.this.amountSpinner.getValue();

            Transferable lendee = LoanCreateDialog.this.senderComboBox.getItemAt(lendeeIndex);

            String collateral = collateralField.getText();

            System.out.println("Create LOAN action");
            System.out.println("\t" + lendee);
            System.out.println("\t" + money);
            System.out.println("\t" + collateral);

            if(bank.getBankRequestManager().takeOutLoan(bank, lendee, bank, money, collateral)){
                JOptionPane.showMessageDialog(LoanCreateDialog.this, "Loan Requested", "Loan Request Success", JOptionPane.INFORMATION_MESSAGE);

                LoanCreateDialog.this.dispose();
            }
            else{
                JOptionPane.showMessageDialog(LoanCreateDialog.this, "Loan Request Failure", "Loan Request Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    /** Update the title to match the account type selected **/
    private class CancelButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Cancel");
            LoanCreateDialog.this.dispose();
        }
    }
}
