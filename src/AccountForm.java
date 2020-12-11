import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountForm extends JFrame {

    private Bank bank;
    private BankAccount account;

    private JList transactionJList;
    private JTextArea outputJTextArea;


    public AccountForm(Bank bank, BankAccount account){

        this.outputJTextArea = new JTextArea(account.toString());
        this.outputJTextArea.setEnabled(false);

        this.bank = bank;
        this.account = account;

        GridLayout frameLayout = new GridLayout(1,3);
        frameLayout.setHgap(10);
        setLayout(frameLayout);

        GridLayout buttonPanelLayout = new GridLayout(5,1);
        buttonPanelLayout.setHgap(10);
        buttonPanelLayout.setVgap(10);

        JPanel panelOutput = new JPanel(new GridLayout(1,1));
        JPanel panelAccounts = new JPanel(new BorderLayout());
        JPanel panelActions = new JPanel(buttonPanelLayout);


        panelOutput.add(outputJTextArea);

        transactionJList = new JList(account.getTransactions().toArray());

        panelAccounts.add(new JLabel("Transaction Picker"), BorderLayout.NORTH);
        panelAccounts.add(transactionJList, BorderLayout.CENTER);

        JButton buttonWithdraw = new JButton("Withdraw");
        JButton buttonDeposit = new JButton("Deposit");
        JButton buttonTransfer = new JButton("Transfer");
        JButton buttonTakeLoan = new JButton("Request Loan");
        JButton buttonTestJList = new JButton("Test JList");

        buttonTestJList.addActionListener(new TestActionListener());

        panelActions.add(buttonWithdraw);
        panelActions.add(buttonDeposit);
        panelActions.add(buttonTransfer);
        panelActions.add(buttonTakeLoan);
        panelActions.add(buttonTestJList);

        add(panelOutput);//adding button on frame
        add(panelAccounts);//adding button on frame
        add(panelActions);//adding button on frame

        setTitle("User View Form");
        setSize(600,400);
        //setLayout(null);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class TestActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            // get the index
            int index = transactionJList.getLeadSelectionIndex();
            System.out.println("YOU SELECTED: Transcation: " + index);

            // get the transaction
            Transaction transaction = account.getTransactions().get(index);

            outputJTextArea.setText(transaction.toString());

        }
    }
}
