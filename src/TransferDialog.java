import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferDialog extends JDialog {

    private JComboBox<Transferable> senderComboBox, receiverComboBox;

    private JLabel titleLabel;

    private Bank bank;
    private Customer customer;


    public TransferDialog(Bank bank, Customer customer, JFrame parentFrame){
        super(parentFrame);

        this.bank = bank;
        this.customer = customer;

        titleLabel = new JLabel("Transfer");

        setSize(400,200);


        GridLayout infoPanelLayout = new GridLayout(1,2, 5,5);

        JPanel infoPanel = new JPanel(infoPanelLayout);
        JPanel actionPanel = new JPanel();

        add(titleLabel, BorderLayout.NORTH);
        add(infoPanel, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);

        senderComboBox = new JComboBox<>();
        receiverComboBox = new JComboBox<>();


        GridLayout rowLayout = new GridLayout(1,2, 5,5);

        JPanel labelPanel = new JPanel(rowLayout);
        JPanel dataPanel = new JPanel(rowLayout);

        labelPanel.add(new JLabel("Sender:"));
        dataPanel.add(senderComboBox);
        labelPanel.add(new JLabel("Recepient:"));
        dataPanel.add(receiverComboBox);

        infoPanel.add(labelPanel);
        infoPanel.add(dataPanel);

        JButton confirmButton = new JButton("Confirm");
        JButton cancelButton = new JButton("Cancel");

        confirmButton.addActionListener(new ConfirmTransactionActionListener());
        cancelButton.addActionListener(new CancelButtonActionListener());

        actionPanel.add(confirmButton);
        actionPanel.add(cancelButton);


        setTitle("Add Account");
        // set up the action selection buttons

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        pack();
        setLocationRelativeTo(null);
    }


    /** Update the title to match the account type selected **/
    private class ConfirmTransactionActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            int senderIndex = TransferDialog.this.senderComboBox.getSelectedIndex();
            int recevierIndex = TransferDialog.this.receiverComboBox.getSelectedIndex();

            Transferable sender = TransferDialog.this.senderComboBox.getItemAt(senderIndex);
            Transferable receiver = TransferDialog.this.receiverComboBox.getItemAt(recevierIndex);

            System.out.println("TODO Transfer action");
            System.out.println("\t" + sender);
            System.out.println("\t" + receiver);

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
