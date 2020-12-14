import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DepositWithdrawDialog extends JDialog {

    private JComboBox<Transferable> accountComboBox;

    private JSpinner amountSpinner;

    private JLabel accountValueLabel;

    private JLabel titleLabel;

    private Bank bank;
    private Customer customer;


    public DepositWithdrawDialog(Bank bank, Customer customer, JFrame parentFrame){
        super(parentFrame);

        this.bank = bank;
        this.customer = customer;

        titleLabel = new JLabel("Deposit");

        setSize(400,200);


        GridLayout infoPanelLayout = new GridLayout(1,3, 5,5);

        JPanel infoPanel = new JPanel(infoPanelLayout);
        JPanel actionPanel = new JPanel();

        add(titleLabel, BorderLayout.NORTH);
        add(infoPanel, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);

        accountComboBox = new JComboBox<>();

        initComboBox(accountComboBox, customer.getAccounts());

        // update sender when the sender combo box changes
        accountComboBox.addActionListener(e -> {
            accountValueLabel.setText("$" + ((BankAccount)accountComboBox.getSelectedItem()).getBalance());
        });

        accountValueLabel = new JLabel("$" + ((BankAccount)accountComboBox.getSelectedItem()).getBalance());

        GridLayout rowLayout = new GridLayout(2,1, 5,5);

        JPanel labelPanel = new JPanel(rowLayout);
        JPanel dataPanel = new JPanel(rowLayout);
        JPanel valuePanel = new JPanel(rowLayout);


        labelPanel.add(new JLabel("Account:"));
        dataPanel.add(accountComboBox);
        valuePanel.add(accountValueLabel);

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
    private <T extends Transferable> void initComboBox(JComboBox<Transferable> comboBox, List<T> valuesList){

        for(T value : valuesList){
            comboBox.addItem(value);
        }

    }


    /** Update the title to match the account type selected **/
    private class ConfirmTransactionActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            int senderIndex = senderComboBox.getSelectedIndex();
            int recevierIndex = receiverComboBox.getSelectedIndex();

            if(senderIndex == recevierIndex){
                JOptionPane.showMessageDialog(DepositWithdrawDialog.this, "Transfer must be between two different accounts", "Transfer Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Transferable sender = senderComboBox.getItemAt(senderIndex);
            Transferable receiver = receiverComboBox.getItemAt(recevierIndex);
            double money = (Double)amountSpinner.getValue();

            System.out.println("Transfer action");
            System.out.println("\t" + sender);
            System.out.println("\t" + receiver);
            System.out.println("\t" + money);

            if(bank.getBankRequestManager().transfer(bank, sender, receiver, money)){
                JOptionPane.showMessageDialog(DepositWithdrawDialog.this, "Transfer completed: " + sender + " --> " + receiver + " " + money, "Delete Account", JOptionPane.INFORMATION_MESSAGE);
                DepositWithdrawDialog.this.dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(DepositWithdrawDialog.this, "Transfer Failed", "Transfer Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /** Update the title to match the account type selected **/
    private class CancelButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Cancel");
            DepositWithdrawDialog.this.dispose();
        }
    }
}
