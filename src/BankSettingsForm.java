import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankSettingsForm extends JDialog {

    private JTextField transactionFeeField, savingsInterestRateField, loanInterestRateField, minSavingsForInterestField;

    private Bank bank;
    private Customer customer;


    public BankSettingsForm(JFrame parentFrame, Bank bank){

        super(parentFrame);

        this.bank = bank;
        this.customer = customer;

        setSize(400,250);

        GridLayout infoPanelLayout = new GridLayout(1,2, 5,5);

        JPanel infoPanel = new JPanel(infoPanelLayout);
        JPanel actionPanel = new JPanel();

        add(infoPanel, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);

        transactionFeeField = new JTextField();
        savingsInterestRateField = new JTextField();
        loanInterestRateField = new JTextField();
        minSavingsForInterestField = new JTextField();

        transactionFeeField.setPreferredSize(new Dimension(200, 24));
        savingsInterestRateField.setPreferredSize(new Dimension(200, 24));
        loanInterestRateField.setPreferredSize(new Dimension(200, 24));
        minSavingsForInterestField.setPreferredSize(new Dimension(200, 24));

        transactionFeeField.setText(Double.toString(bank.getSettings().getTransactionFee()));
        savingsInterestRateField.setText(Double.toString(bank.getSettings().getSavingsInterestRate()));
        loanInterestRateField.setText(Double.toString(bank.getSettings().getLoanInterestRate()));
        minSavingsForInterestField.setText(Double.toString(bank.getSettings().getMinSavingsForInterest()));


        JPanel labelPanel = new JPanel(new GridLayout(4,1, 5,5));
        JPanel fieldPanel = new JPanel(new GridLayout(4,1, 5,5));

        labelPanel.add(new JLabel("Transaction Fee:"));
        fieldPanel.add(transactionFeeField);
        labelPanel.add(new JLabel("Savings Interest Rate:"));
        fieldPanel.add(savingsInterestRateField);
        labelPanel.add(new JLabel("Loan Interest Rate:"));
        fieldPanel.add(loanInterestRateField);
        labelPanel.add(new JLabel("Min Saving Amount for Interest"));
        fieldPanel.add(minSavingsForInterestField);


        infoPanel.add(labelPanel);
        infoPanel.add(fieldPanel);

        JButton applyButton = new JButton("Confirm");
        JButton cancelButton = new JButton("Cancel");

        applyButton.addActionListener(new ApplyButtonActionListener());
        cancelButton.addActionListener(new CancelButtonActionListener());

        actionPanel.add(applyButton);
        actionPanel.add(cancelButton);

        add(infoPanel, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);

        setTitle("Bank Settings");
        // set up the action selection buttons

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }



    /** Update the title to match the account type selected **/
    private class ApplyButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            try {
                Double transactionFee = Double.parseDouble(BankSettingsForm.this.transactionFeeField.getText());
                Double savingsInterestRate = Double.parseDouble(BankSettingsForm.this.savingsInterestRateField.getText());
                Double loanInterestRate = Double.parseDouble(BankSettingsForm.this.loanInterestRateField.getText());
                Double minSavingsForInterest = Double.parseDouble(BankSettingsForm.this.minSavingsForInterestField.getText());

                //TODO update the bank settings

                System.out.println("TODO - Save the bank settings: ");
                System.out.println("\ttransactionFee " + transactionFee);
                System.out.println("\tsavingsInterestRate " + savingsInterestRate);
                System.out.println("\tloanInterestRate " + loanInterestRate);
                System.out.println("\tminSavingsForInterest " + minSavingsForInterest);


                /* TODO
                if(bank.getBankRequestManager().saveBankSettings(Bank bank, Double transactionFee, Double savingsInterestRate, Double loanInterestRate, Double minSavingsForInterest){
                    JOptionPane.showMessageDialog(BankSettingsForm.this, "Bank settings saved", "Bank Settings", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(BankSettingsForm.this, "Bank settings failed to save.", "Bank Settings Error", JOptionPane.ERROR_MESSAGE);
                }
                */

                BankSettingsForm.this.dispose();

            }
            catch(NumberFormatException numberFormatException){
                JOptionPane.showMessageDialog(BankSettingsForm.this, "All field inputs must be numbers", "NUMBER ERROR", JOptionPane.ERROR_MESSAGE);
            }




        }
    }

    /** Update the title to match the account type selected **/
    private class CancelButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Cancel");
            BankSettingsForm.this.dispose();
        }
    }
}
