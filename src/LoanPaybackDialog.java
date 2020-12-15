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

        if(customer.getLoans().isEmpty()){
            JOptionPane.showMessageDialog(parentFrame, "NO LOANS TO PAYBACK");
        }

        // update sender when the sender combo box changes
        senderComboBox.addActionListener(e -> {
            senderValueLabel.setText("$" + ((BankAccount)senderComboBox.getSelectedItem()).getBalance());
        });

        loanComboBox.addActionListener(e -> {
            loanValueLabel.setText("$" + ((Loan)loanComboBox.getSelectedItem()).getPresentValue());
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


        setTitle("Payback Loan");
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

            int lendeeIndex = LoanPaybackDialog.this.senderComboBox.getSelectedIndex();
            int loanIndex = LoanPaybackDialog.this.loanComboBox.getSelectedIndex();

            double money = (Double)LoanPaybackDialog.this.amountSpinner.getValue();

            Transferable lendee = LoanPaybackDialog.this.senderComboBox.getItemAt(lendeeIndex);
            Loan loan = LoanPaybackDialog.this.loanComboBox.getItemAt(loanIndex);

            System.out.println("TODO Transfer action");
            System.out.println("\t" + lendee);
            System.out.println("\t" + loan);
            System.out.println("\t" + money);

            //TODO payback loan action
            if(bank.getBankRequestManager().payBackLoan(bank, lendee, bank, money, loan)){
                if(loan.getPresentValue() > 0) {
                    JOptionPane.showMessageDialog(LoanPaybackDialog.this, "Loan value remaining: " + loan.getPresentValue(), "Loan Payment", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(LoanPaybackDialog.this, "You have payed off the loan!", "Loan Payment Complete", JOptionPane.INFORMATION_MESSAGE);
                }
                LoanPaybackDialog.this.dispose();
            }
            else{
                JOptionPane.showMessageDialog(LoanPaybackDialog.this, "Payback failed, insufficient funds", "Loan error", JOptionPane.ERROR_MESSAGE);
            }

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
