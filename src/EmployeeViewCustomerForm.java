import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * EmployeeViewCustomerForm
 * Allows the employee to view overall view of the employee accounts, and sort accounts by different metrics.
 *
 * @author ejbosia
 */

public class EmployeeViewCustomerForm extends JDialog {

    private JFrame parentFrame;

    private Bank bank;
    private Customer customer;

    private JList accountJList, loanJList;

    private JTextArea accountTextArea, loanTextArea;

    public EmployeeViewCustomerForm(Bank bank, Customer customer, JFrame parentFrame){

        super(parentFrame);

        this.bank = bank;
        this.customer = customer;

        GridLayout frameLayout = new GridLayout(1,3, 10, 0);
        setLayout(frameLayout);

        GridLayout actionsLayout = new GridLayout(3,1,10,10);
        GridLayout infoPanelLayout = new GridLayout(2,1,10,10);

        JPanel panelLoan = new JPanel(new BorderLayout());
        JPanel panelAccounts = new JPanel(new BorderLayout());

        ScrollPane accountScroll = new ScrollPane();
        ScrollPane loanScroll = new ScrollPane();


        accountJList = new JList(customer.getAccounts().toArray(new BankAccount[0]));
        loanJList = new JList(customer.getLoans().toArray(new Loan[0]));

        accountJList.addListSelectionListener(new AccountListActionListener());
        loanJList.addListSelectionListener(new LoanListActionListener());

        accountScroll.add(accountJList);
        loanScroll.add(loanJList);

        accountTextArea = new JTextArea();
        loanTextArea = new JTextArea();

        JPanel panelAccountsInfo = new JPanel(infoPanelLayout);
        panelAccountsInfo.add(accountScroll);
        panelAccountsInfo.add(accountTextArea);

        JButton viewTransactionsButton = new JButton("View Transactions");
        viewTransactionsButton.addActionListener(new ViewTransactionsActionListener());

        panelAccounts.add(new JLabel("Accounts"), BorderLayout.NORTH);
        panelAccounts.add(panelAccountsInfo, BorderLayout.CENTER);
        panelAccounts.add(viewTransactionsButton, BorderLayout.SOUTH);

        JPanel panelLoanInfo = new JPanel(infoPanelLayout);
        panelLoanInfo.add(loanScroll);
        panelLoanInfo.add(loanTextArea);


        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new CloseActionListener());

        panelLoan.add(new JLabel("Loans"), BorderLayout.NORTH);
        panelLoan.add(panelLoanInfo, BorderLayout.CENTER);
        panelLoan.add(closeButton, BorderLayout.SOUTH);

        add(panelAccounts);
        add(panelLoan);

        setTitle("Customer: " + customer);
        setSize(600,400);
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private class AccountListActionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            if(e.getValueIsAdjusting()){
                // do nothing until the selection is finalized
            }
            else {
                BankAccount account = (BankAccount)accountJList.getSelectedValue();
                System.out.println("Account " + account);
                accountTextArea.setText(account.fullOutput());
            }
        }
    }

    private class LoanListActionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {

            if(e.getValueIsAdjusting()){
                // do nothing until the selection is finalized
            }
            else {
                Loan loan = (Loan)loanJList.getSelectedValue();
                System.out.println("Loan " + loan);
                loanTextArea.setText(loan.fullOutput());
            }
        }
    }


    private class ViewTransactionsActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                BankAccount account = (BankAccount) accountJList.getSelectedValue();

                // OPEN A MESSAGE BOX
                JOptionPane.showMessageDialog(EmployeeViewCustomerForm.this, account.getTransactionHistory());
            }
            catch(IndexOutOfBoundsException indexOutOfBoundsException){
                JOptionPane.showMessageDialog(EmployeeViewCustomerForm.this, "NO ACCOUNT SELECTED", "ACCOUNT ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class CloseActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Close Employee Customer View");
            EmployeeViewCustomerForm.this.dispose();
        }
    }

}
