import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * EmployeeViewCustomerForm
 * Allows the employee to view overall view of the employee accounts, and sort accounts by different metrics.
 *
 * @author ejbosia
 */



public class EmployeeViewCustomerForm extends JFrame {

    private JFrame parentFrame;

    private Bank bank;
    private Customer customer;

    private JList accountJList;

    public EmployeeViewCustomerForm(Bank bank, Customer customer, JFrame parentFrame){

        this.parentFrame = parentFrame;

        this.bank = bank;
        this.customer = customer;

        debugInit();

        GridLayout frameLayout = new GridLayout(1,2, 10, 0);
        setLayout(frameLayout);

        GridLayout actionsLayout = new GridLayout(3,1,10,10);

        JPanel panelAccounts = new JPanel(new BorderLayout());
        JPanel panelActions = new JPanel(actionsLayout);



        accountJList = new JList(customer.getAccounts().toArray());

        panelAccounts.add(new JLabel("Account Picker"), BorderLayout.NORTH);
        panelAccounts.add(accountJList, BorderLayout.CENTER);

        JButton sortAccountsButton = new JButton("Sort Accounts");
        JButton viewAccountButton = new JButton("View Account");
        JButton signoutButton = new JButton("Sign Out");;

        sortAccountsButton.addActionListener(new SortAccountsActionListener());
        viewAccountButton.addActionListener(new ViewAccountActionListener());
        signoutButton.addActionListener(new SignoutActionListener());

        panelActions.add(sortAccountsButton);
        panelActions.add(viewAccountButton);
        panelActions.add(signoutButton);

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

    private class SortAccountsActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //TODO Create account form
            System.out.println("SORT ACCOUNTS");
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

                // TODO hide this window
                //EmployeeViewCustomerForm.this.setVisible(false);

                //TODO Open the EmployeeViewAccount form

            }catch(IndexOutOfBoundsException indexOutOfBoundsException){
                JOptionPane.showMessageDialog(null, "NO ACCOUNT SELECTED", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private class SignoutActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            System.out.println("Signout");
            parentFrame.setVisible(true);
            EmployeeViewCustomerForm.this.dispose();
        }
    }



}
