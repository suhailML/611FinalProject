import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * AccountForm
 * Allows the user to view an account summary and transactions associated with the account.
 *
 * @author ejbosia
 */

public class AccountForm extends JFrame {

    private Bank bank;
    private BankAccount account;

    private JList transactionJList;
    private JTextArea outputJTextArea;

    private JFrame parentFrame;

    public AccountForm(Bank bank, BankAccount account, JFrame parentFrame){

        this.parentFrame = parentFrame;

        this.outputJTextArea = new JTextArea(account.fullOutput());
        this.outputJTextArea.setEnabled(false);

        this.bank = bank;
        this.account = account;

        GridLayout frameLayout = new GridLayout(1,2);
        frameLayout.setHgap(10);
        setLayout(frameLayout);

        GridLayout buttonPanelLayout = new GridLayout(5,1);
        buttonPanelLayout.setHgap(10);
        buttonPanelLayout.setVgap(10);

        JPanel panelOutput = new JPanel(new GridLayout(1,1));
        JPanel panelTransactions = new JPanel(new BorderLayout());

        panelOutput.add(outputJTextArea);

        transactionJList = new JList(account.getTransactionHistory().getTransactions().toArray());

        JButton buttonBack = new JButton("Back");
        buttonBack.addActionListener(new BackActionListener());

        panelTransactions.add(new JLabel("Transaction Picker"), BorderLayout.NORTH);
        panelTransactions.add(transactionJList, BorderLayout.CENTER);
        panelTransactions.add(buttonBack, BorderLayout.SOUTH);

        add(panelOutput);//adding button on frame
        add(panelTransactions);//adding button on frame

        setTitle("User View Form");
        setSize(600,400);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    private class buttonWithdrawActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {

            String input = JOptionPane.showInputDialog(
                    AccountForm.this,
                    "Enter an amount to withdraw:",
                    "");

            if(input == null){
                System.out.println("Cancel selected");
                return;
            }

            double money = Double.parseDouble(input);

            if(bank.getBankRequestManager().withdraw(bank, account, money)){
                JOptionPane.showMessageDialog(AccountForm.this, "You withdrew " + money + " from " + account, "Withdrawal", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(AccountForm.this, "Withdrawal from account failed", "Withdrawal Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }



    private class buttonDepositActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog(
                    AccountForm.this,
                    "Enter an amount to deposit:",
                    "");

            if(input == null){
                System.out.println("Cancel selected");
                return;
            }

            double money = Double.parseDouble(input);

            if(bank.getBankRequestManager().deposit(bank, account, money)){
                JOptionPane.showMessageDialog(AccountForm.this, "You deposited " + money + " to " + account, "Deposit", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(AccountForm.this, "Deposit to account failed", "Deposit Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private class BackActionListener implements  ActionListener{
        public void actionPerformed(ActionEvent e) {
            System.out.println("ACTION: return to user form");
            AccountForm.this.dispose();
            parentFrame.setVisible(true);
        }
    }

    //TODO remove this
    /** Test function ~ output the selected transaction **/
    private class TestActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            // get the index
            int index = transactionJList.getLeadSelectionIndex();
            System.out.println("YOU SELECTED: Transcation: " + index);

            // get the transaction
            Transaction transaction = account.getTransactionHistory().getTransactions().get(index);

            outputJTextArea.setText(transaction.toString());
        }
    }
}
