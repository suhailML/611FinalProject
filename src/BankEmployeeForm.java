import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

public class BankEmployeeForm extends JFrame {

    private Bank bank;
    private Employee employee;

    private JList<Customer> customerJList;
    private JTextArea outputJTextArea;

    private JFrame parentFrame;

    public BankEmployeeForm(Bank bank, Employee employee, JFrame parentFrame){

        this.parentFrame = parentFrame;

        //TODO show the bad accounts on load

        this.bank = bank;
        this.employee = employee;

        GridLayout frameLayout = new GridLayout(1,3);
        frameLayout.setHgap(10);
        setLayout(frameLayout);

        GridLayout layout = new GridLayout(4,1, 10, 10);

        JPanel panelOutput = new JPanel(new GridLayout(1,1));
        JPanel panelAccounts = new JPanel(new BorderLayout());
        JPanel panelActions = new JPanel(layout);


        outputJTextArea = new JTextArea(employee.toString());
        outputJTextArea.setEnabled(false);
        panelOutput.add(outputJTextArea);

        customerJList = new JList(bank.getCustomers().toArray());

        panelAccounts.add(new JLabel("Account Picker"), BorderLayout.NORTH);
        panelAccounts.add(customerJList, BorderLayout.CENTER);


        JButton editSettingsButton = new JButton("Edit Settings");
        JButton viewAccountButton = new JButton("View Account");
        JButton viewTransactionsButton = new JButton("View Transactions");
        JButton signoutButton = new JButton("Sign out");


        editSettingsButton.addActionListener(new EditSettingsActionListener());
        viewAccountButton.addActionListener(new ViewAccountActionListener());
        viewTransactionsButton.addActionListener((new ViewTransactionsActionListener()));
        signoutButton.addActionListener(new SignoutActionListener());

        panelActions.add(editSettingsButton);
        panelActions.add(viewAccountButton);
        panelActions.add(viewTransactionsButton);
        panelActions.add(signoutButton);

        add(panelOutput);//adding button on frame
        add(panelAccounts);//adding button on frame
        add(panelActions);//adding button on frame

        setTitle("Employee View Form");
        setSize(600,400);
        //setLayout(null);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /** Open the bank settings editor form **/
    private class EditSettingsActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            new BankSettingsForm(BankEmployeeForm.this, BankEmployeeForm.this.bank).setVisible(true);

        }
    }

    /** View the selected account **/
    private class ViewAccountActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            try{
                // get the index
                Customer customer = customerJList.getSelectedValue();
                System.out.println("Open customer: " + customer);

                BankEmployeeForm.this.setVisible(false);

                //TODO Open the customer history form
                new EmployeeViewCustomerForm(bank, customer, BankEmployeeForm.this).setVisible(true);
            }catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                JOptionPane.showMessageDialog(null, "NO ACCOUNT SELECTED", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ViewTransactionsActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            // output to console
            System.out.println("Open transactions view");

            new EmployeeViewTransactionsDialog(BankEmployeeForm.this.bank, BankEmployeeForm.this);
        }
    }

    private class SignoutActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            parentFrame.setVisible(true);
            BankEmployeeForm.this.dispose();
        }
    }
}
