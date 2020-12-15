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



public class EmployeeViewCustomerForm extends JFrame {

    private JFrame parentFrame;

    private Bank bank;
    private Customer customer;

    private JList accountJList, loanJList;

    private JTextArea accountTextArea, loanTextArea;

    public EmployeeViewCustomerForm(Bank bank, Customer customer, JFrame parentFrame){

        this.parentFrame = parentFrame;

        this.bank = bank;
        this.customer = customer;

        GridLayout frameLayout = new GridLayout(1,3, 10, 0);
        setLayout(frameLayout);

        GridLayout actionsLayout = new GridLayout(3,1,10,10);
        GridLayout infoPanelLayout = new GridLayout(2,1,10,10);

        JPanel panelLoan = new JPanel(new BorderLayout());
        JPanel panelAccounts = new JPanel(new BorderLayout());
        JPanel panelActions = new JPanel(actionsLayout);

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

        panelAccounts.add(new JLabel("Account Picker"), BorderLayout.NORTH);
        panelAccounts.add(panelAccountsInfo, BorderLayout.CENTER);

        JPanel panelLoanInfo = new JPanel(infoPanelLayout);
        panelLoanInfo.add(loanScroll);
        panelLoanInfo.add(loanTextArea);

        panelLoan.add(new JLabel("Loan Picker"), BorderLayout.NORTH);
        panelLoan.add(panelLoanInfo, BorderLayout.CENTER);

        JButton returnButton = new JButton("Back");

        returnButton.addActionListener(new ReturnActionListener());

        panelActions.add(returnButton);

        add(panelAccounts);
        add(panelLoan);
        add(panelActions);

        setTitle("Bank View of User");
        setSize(600,400);
        //setLayout(null);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        setVisible(true);
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

    private class ReturnActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            System.out.println("Signout");
            parentFrame.setVisible(true);
            EmployeeViewCustomerForm.this.dispose();
        }
    }



}
