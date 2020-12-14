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

    private JFrame parentFrame;

    public UserForm(Bank bank, Customer customer, JFrame parentFrame){

        this.parentFrame = parentFrame;

        this.bank = bank;
        this.customer = customer;

        System.out.println("OPEN CUSTOMER " + customer);

        debugInit();

        GridLayout frameLayout = new GridLayout(1,3);
        frameLayout.setHgap(10);
        setLayout(frameLayout);

        GridLayout actionsLayout = new GridLayout(6,1,10,10);

        JPanel panelOutput = new JPanel(new BorderLayout(0,5));
        JPanel panelAccounts = new JPanel(new BorderLayout(0, 5));
        JPanel panelActions = new JPanel(actionsLayout);


        outputJTextArea = new JTextArea(customer.toString());
        outputJTextArea.setEnabled(false);

        JButton editUserButton = new JButton("Edit User");
        editUserButton.addActionListener(new EditUserActionListener());

        panelOutput.add(outputJTextArea, BorderLayout.CENTER);
        panelOutput.add(editUserButton, BorderLayout.SOUTH);

        accountJList = new JList(customer.getAccounts().toArray());

        JButton viewAccountButton = new JButton("View Account");

        panelAccounts.add(new JLabel("Account Picker"), BorderLayout.NORTH);
        panelAccounts.add(accountJList, BorderLayout.CENTER);
        panelAccounts.add(viewAccountButton, BorderLayout.SOUTH);

        viewAccountButton.addActionListener(new ViewAccountActionListener());


        // Action buttons
        JButton addAccountButton = new JButton("Add Account");
        JButton deleteAccountButton = new JButton("Delete Account");
        JButton transferButton = new JButton("Make Transfer");
        JButton requestLoanButton = new JButton("Request Loan");
        JButton paybackLoanButton = new JButton("Payback Loan");
        JButton signoutButton = new JButton("Sign Out");;

        addAccountButton.addActionListener(new AddAccountActionListener());
        deleteAccountButton.addActionListener(new DeleteAccountActionListener());
        transferButton.addActionListener(new TransferActionListener());
        requestLoanButton.addActionListener(new RequestLoanActionListener());
        paybackLoanButton.addActionListener(new PaybackLoanActionListener());
        signoutButton.addActionListener(new SignoutActionListener());

        panelActions.add(addAccountButton);
        panelActions.add(deleteAccountButton);
        panelActions.add(transferButton);
        panelActions.add(requestLoanButton);
        panelActions.add(paybackLoanButton);
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
        accounts.add(test);
        accounts.add(new SavingsAccount("First Savings Account","A","$"));

        this.customer.setAccounts(accounts);
    }

    private class EditUserActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            //TODO Create account form
            System.out.println("Edit User");
            new EditUserDialog(UserForm.this.bank, UserForm.this.customer, UserForm.this).setVisible(true);
        }
    }

    /** Open the window to add an account **/
    private class AddAccountActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //TODO Create account form
            System.out.println("Add Account");
            new AddAccountDialog(UserForm.this.bank, UserForm.this.customer, UserForm.this).setVisible(true);
        }
    }

    /** Open the account view of a selected account **/
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

    /** Allow the user to delete a selected account **/
    private class DeleteAccountActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // get the index
            try {
                BankAccount account = accountJList.getSelectedValue();
                System.out.println("DELETE ACCOUNT: " + account);

                System.out.println("TODO --> ACTUALLY DELETE ACCOUNT");
                JOptionPane.showMessageDialog(UserForm.this,"DELETE - NEED TO DO - ACCOUNT" + account);


                //TODO
                /*
                if(bank.getBankRequestManager().deleteAccount(bank, customer, account)){
                    JOptionPane.showMessageDialog(UserForm.this, "Account deleted: " + account, "Delete Account", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(UserForm.this, "Could not delete account: " + account, "Delete Account Error", JOptionPane.ERROR_MESSAGE);
                }
                 */

            }catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                JOptionPane.showMessageDialog(null, "NO ACCOUNT SELECTED", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /** Open the window to make a transfer into an account **/
    private class TransferActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            System.out.println("OPEN TRANSFER DIALOG");
            new TransferDialog(UserForm.this.bank, UserForm.this.customer, UserForm.this).setVisible(true);
        }
    }


    /** Open the window to allow the user to payback a loan **/
    private class RequestLoanActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            System.out.println("OPEN CREATE LOAN DIALOG");
            new LoanCreateDialog(UserForm.this.bank, UserForm.this.customer, UserForm.this).setVisible(true);
        }
    }

    /** Open the window to allow the user to payback a loan **/
    private class PaybackLoanActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            System.out.println("OPEN PAYBACK LOAN DIALOG");
            new LoanPaybackDialog(UserForm.this.bank, UserForm.this.customer, UserForm.this).setVisible(true);
        }
    }


    /** Return to the login form - "log out" of the app **/
    private class SignoutActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            System.out.println("Signout");
            parentFrame.setVisible(true);
            UserForm.this.dispose();
        }
    }
}
