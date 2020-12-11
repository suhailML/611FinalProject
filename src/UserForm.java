import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UserForm extends JFrame {

    private Bank bank;
    private Customer customer;

    private JList<BankAccount> accountJList;
    private JTextArea outputJTextArea;

    public UserForm(Bank bank, Customer customer){

        this.bank = bank;
        this.customer = customer;

        debugInit();

        GridLayout frameLayout = new GridLayout(1,3);
        frameLayout.setHgap(10);
        setLayout(frameLayout);

        GridLayout layout = new GridLayout(3,1);
        layout.setHgap(10);
        layout.setVgap(10);

        JPanel panelOutput = new JPanel(new GridLayout(1,1));
        JPanel panelAccounts = new JPanel(new BorderLayout());
        JPanel panelActions = new JPanel(layout);


        outputJTextArea = new JTextArea(customer.toString());
        outputJTextArea.setEnabled(false);
        panelOutput.add(outputJTextArea);

        accountJList = new JList(customer.getAccounts().toArray());

        panelAccounts.add(new JLabel("Account Picker"), BorderLayout.NORTH);
        panelAccounts.add(accountJList, BorderLayout.CENTER);

        JButton buttonAddAccount = new JButton("Add Account");
        JButton buttonViewAccount = new JButton("View Account");
        JButton buttonDeleteAccount = new JButton("Delete Account");;

        buttonAddAccount.addActionListener(new AddAccountButtonHandler());
        buttonViewAccount.addActionListener(new ViewAccountButtonHandler());
        buttonDeleteAccount.addActionListener(new DeleteAccountButtonHandler());

        panelActions.add(buttonAddAccount);
        panelActions.add(buttonViewAccount);
        panelActions.add(buttonDeleteAccount);

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
        accounts.add(new CheckingAccount("First Checking Account","A","$"));
        accounts.add(new SavingsAccount("First Savings Account","A","$"));

        this.customer.setAccounts(accounts);
    }

    private class AddAccountButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //TODO Create account form

        }
    }

    private class ViewAccountButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {

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
        }
    }

    private class DeleteAccountButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // get the index
            int index = accountJList.getLeadSelectionIndex();
            System.out.println("DELETE ACCOUNT: " + index);

            System.out.println("TODO --> ACTUALLY DELETE ACCOUNT");

        }
    }
}
