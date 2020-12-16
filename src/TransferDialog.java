import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * TransferDialog
 * The transfer dialog allows the user to transfer money between their accounts.
 *
 * @author ejbosia
 */


public class TransferDialog extends JDialog {

    private JComboBox<Transferable> senderComboBox, receiverComboBox;

    private JSpinner amountSpinner;

    private JLabel senderValueLabel, receiverValueLabel;

    private JLabel titleLabel;

    private Bank bank;
    private Customer customer;


    public TransferDialog(Bank bank, Customer customer, JFrame parentFrame){
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

        senderComboBox = new JComboBox<>();
        receiverComboBox = new JComboBox<>();

        initComboBox(senderComboBox, customer.getAccounts());
        initComboBox(receiverComboBox, customer.getAccounts());

        // update sender when the sender combo box changes
        senderComboBox.addActionListener(e -> {
            senderValueLabel.setText("$" + ((BankAccount)senderComboBox.getSelectedItem()).getBalance());
        });

        // update the receiver when the receiver combo box changes
        receiverComboBox.addActionListener(e -> {
            receiverValueLabel.setText("$" + ((BankAccount)receiverComboBox.getSelectedItem()).getBalance());
        });


        senderValueLabel = new JLabel("$" + ((BankAccount)senderComboBox.getSelectedItem()).getBalance());
        receiverValueLabel = new JLabel("$" + ((BankAccount)receiverComboBox.getSelectedItem()).getBalance());

        GridLayout rowLayout = new GridLayout(2,1, 5,5);

        JPanel labelPanel = new JPanel(rowLayout);
        JPanel dataPanel = new JPanel(rowLayout);
        JPanel valuePanel = new JPanel(rowLayout);


        labelPanel.add(new JLabel("Sender:"));
        dataPanel.add(senderComboBox);
        valuePanel.add(senderValueLabel);
        labelPanel.add(new JLabel("Recepient:"));
        dataPanel.add(receiverComboBox);
        valuePanel.add(receiverValueLabel);

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
                JOptionPane.showMessageDialog(TransferDialog.this, "Transfer must be between two different accounts", "Transfer Error", JOptionPane.ERROR_MESSAGE);
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
                JOptionPane.showMessageDialog(TransferDialog.this, "Transfer completed: " + sender + " --> " + receiver + " " + money, "Delete Account", JOptionPane.INFORMATION_MESSAGE);
                TransferDialog.this.dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(TransferDialog.this, "Transfer Failed", "Transfer Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /** Update the title to match the account type selected **/
    private class CancelButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Cancel");
            TransferDialog.this.dispose();
        }
    }
}
