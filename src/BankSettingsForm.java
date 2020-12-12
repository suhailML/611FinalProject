import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankSettingsForm extends JFrame {

    private JTextField transactionFeeField, savingsInterestRateField, loanInterestRateField, minSavingsForInterestField;

    private JLabel titleLabel;

    private Bank bank;
    private Customer customer;


    public BankSettingsForm(JFrame parentFrame, Bank bank, Customer customer){

        this.bank = bank;
        this.customer = customer;

        titleLabel = new JLabel("Create Account: Savings");

        setSize(400,600);

        GridLayout infoPanelLayout = new GridLayout(1,2, 5,5);

        JPanel infoPanel = new JPanel(infoPanelLayout);
        JPanel actionPanel = new JPanel();
        JPanel radioPanel = new JPanel();

        add(titleLabel, BorderLayout.NORTH);
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

        JPanel labelPanel = new JPanel();
        JPanel fieldPanel = new JPanel();

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

        infoPanel.add(radioPanel);

        JButton applyButton = new JButton("Confirm");
        JButton cancelButton = new JButton("Cancel");

        applyButton.addActionListener(new ApplyButtonActionListener());
        cancelButton.addActionListener(new CancelButtonActionListener());

        actionPanel.add(applyButton);
        actionPanel.add(cancelButton);

        add(infoPanel);
        add(actionPanel);

        setTitle("Add Account");
        // set up the action selection buttons

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }



    /** Update the title to match the account type selected **/
    private class ApplyButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Save the bank settings");
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
