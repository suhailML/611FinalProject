import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoanPaybackDialog extends JDialog {

    private JComboBox<Transferable> senderComboBox;
    private JComboBox<Loan> loanComboBox;

    private JSpinner amountSpinner;

    private JLabel senderValueLabel, loanValueLabel;

    private JLabel titleLabel;

    private Bank bank;
    private Customer customer;


    public LoanPaybackDialog(Bank bank, Customer customer, JFrame parentFrame){
        super(parentFrame);

        this.bank = bank;
        this.customer = customer;

        titleLabel = new JLabel("Transfer");

        setSize(400,200);

        GridLayout infoPanelLayout = new GridLayout(1,3, 5,5);

        JPanel infoPanel = new JPanel(infoPanelLayout);
        JPanel actionPanel = new JPanel();

        add(titleLabel, BorderLayout.NORTH);
        add(infoPanel, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);

        // initialize combo box
        senderComboBox = new JComboBox<>();
        loanComboBox = new JComboBox<>();

        initComboBox(senderComboBox, customer.getAccounts());
        initComboBox(loanComboBox, customer.getLoans());

        // update sender when the sender combo box changes
        senderComboBox.addActionListener(e -> {
            senderValueLabel.setText("$" + ((BankAccount)senderComboBox.getSelectedItem()).getBalance());
        });

        loanComboBox.addActionListener(e -> {
            senderValueLabel.setText("$" + ((Loan)senderComboBox.getSelectedItem()).getPresentValue());
        });

        senderValueLabel = new JLabel("$" + ((BankAccount)senderComboBox.getSelectedItem()).getBalance());
        loanValueLabel = new JLabel("$" + ((Loan)loanComboBox.getSelectedItem()).getPresentValue());

        GridLayout rowLayout = new GridLayout(2,1, 5,5);

        JPanel labelPanel = new JPanel(rowLayout);
        JPanel dataPanel = new JPanel(rowLayout);
        JPanel valuePanel = new JPanel(rowLayout);


        labelPanel.add(new JLabel("Account:"));
        dataPanel.add(senderComboBox);
        valuePanel.add(senderValueLabel);
        labelPanel.add(new JLabel("Loan:"));
        dataPanel.add(loanComboBox);
        valuePanel.add(loanValueLabel);

        infoPanel.add(labelPanel);
        infoPanel.add(dataPanel);
        infoPanel.add(valuePanel);

        JButton confirmButton = new JButton("Confirm");
        JButton cancelButton = new JButton("Cancel");

        confirmButton.addActionListener(new ConfirmTransactionActionListener());
        cancelButton.addActionListener(new CancelButtonActionListener());

        amountSpinner = new JSpinner(new SpinnerNumberModel(0.0, 0.0, Double.MAX_VALUE, 100.0));

        amountSpinner.setPreferredSize(new Dimension(200, 20));

        actionPanel.add(amountSpinner);
        actionPanel.add(confirmButton);
        actionPanel.add(cancelButton);


        setTitle("Add Account");
        // set up the action selection buttons

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

            int senderIndex = LoanPaybackDialog.this.senderComboBox.getSelectedIndex();
            int loanIndex = LoanPaybackDialog.this.loanComboBox.getSelectedIndex();

            double money = (Double)LoanPaybackDialog.this.amountSpinner.getValue();

            Transferable sender = LoanPaybackDialog.this.senderComboBox.getItemAt(senderIndex);
            Loan loan = LoanPaybackDialog.this.loanComboBox.getItemAt(loanIndex);

            System.out.println("TODO Transfer action");
            System.out.println("\t" + sender);
            System.out.println("\t" + loan);
            System.out.println("\t" + money);

            //TODO payback loan action
            //TODO bank.getBankRequestManager().payBackLoan(bank, bank, sender, money, loan);

        }
    }

    /** Update the title to match the account type selected **/
    private class CancelButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Cancel");
            LoanPaybackDialog.this.dispose();
        }
    }
}