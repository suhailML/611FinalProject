import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

public class UserForm extends JFrame {

    private Bank bank;
    private Customer customer;

    private JList<BankAccount> accountJList;
    private JTextArea outputJTextArea;

    private JFrame parentFrame;

    public UserForm(Bank bank, Customer customer, JFrame parentFrame){

        this.parentFrame = parentFrame;

        this.bank = bank;
        this.customer = customer;

        debugInit();

        GridLayout frameLayout = new GridLayout(1,3);
        frameLayout.setHgap(10);
        setLayout(frameLayout);

        GridLayout actionsLayout = new GridLayout(4,1,10,10);

        JPanel panelOutput = new JPanel(new GridLayout(1,1));
        JPanel panelAccounts = new JPanel(new BorderLayout());
        JPanel panelActions = new JPanel(actionsLayout);


        outputJTextArea = new JTextArea(customer.toString());
        outputJTextArea.setEnabled(false);
        panelOutput.add(outputJTextArea);

        accountJList = new JList(customer.getAccounts().toArray());

        panelAccounts.add(new JLabel("Account Picker"), BorderLayout.NORTH);
        panelAccounts.add(accountJList, BorderLayout.CENTER);

        JButton addAccountButton = new JButton("Add Account");
        JButton viewAccountButton = new JButton("View Account");
        JButton deleteAccountButton = new JButton("Delete Account");;
        JButton signoutButton = new JButton("Sign Out");;

        addAccountButton.addActionListener(new AddAccountActionListener());
        viewAccountButton.addActionListener(new ViewAccountActionListener());
        deleteAccountButton.addActionListener(new DeleteAccountActionListener());
        signoutButton.addActionListener(new SignoutActionListener());

        panelActions.add(addAccountButton);
        panelActions.add(viewAccountButton);
        panelActions.add(deleteAccountButton);
        panelActions.add(signoutButton);

        add(panelOutput);//adding button on frame
        add(panelAccounts);//adding button on frame
        add(panelActions);//adding button on frame

        setTitle("User View Form");
        setSize(600,400);
        //setLayout(null);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    private void debugInit(){

        ArrayList<BankAccount> accounts = new ArrayList<>();

        CheckingAccount test = new CheckingAccount("First Checking Account","A","$");
        LinkedList<Transaction> transactionList = new LinkedList<>();
        transactionList.add(new Transaction());
        transactionList.add(new Transaction());
        transactionList.add(new Transaction());

        test.setTransactions(transactionList);
        accounts.add(test);
        accounts.add(new SavingsAccount("First Savings Account","A","$"));

        this.customer.setAccounts(accounts);
    }

    private class AddAccountActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //TODO Create account form
            System.out.println("Add Account");
            new AddAccountDialog(UserForm.this, UserForm.this.bank, UserForm.this.customer).setVisible(true);
        }
    }

    private class ViewAccountActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            try {
                // get the index
                int index = accountJList.getLeadSelectionIndex();
                System.out.println("ACCOUNT: " + index);

                // get the account
                BankAccount account = customer.getAccounts().get(index);

                //TODO Error checking

                // hide this window
                UserForm.this.setVisible(false);

                //TODO Open the Account form
                new AccountForm(bank, account, UserForm.this).setVisible(true);

            }catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                JOptionPane.showMessageDialog(null, "NO ACCOUNT SELECTED", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class DeleteAccountActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // get the index
            try {
                int index = accountJList.getLeadSelectionIndex();
                System.out.println("DELETE ACCOUNT: " + index);

                System.out.println("TODO --> ACTUALLY DELETE ACCOUNT");
                JOptionPane.showMessageDialog(UserForm.this,"DELETE - NEED TO DO - ACCOUNT" + customer.getAccounts().get(index));

            }catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                JOptionPane.showMessageDialog(null, "NO ACCOUNT SELECTED", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class SignoutActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            System.out.println("Signout");
            parentFrame.setVisible(true);
            UserForm.this.dispose();
        }
    }
}
