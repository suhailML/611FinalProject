import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

public class BankEmployeeForm extends JFrame {

    private Bank bank;
    private Employee employee;

    private JList<BankAccount> customerJList;
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

        GridLayout layout = new GridLayout(3,1, 10, 10);

        JPanel panelOutput = new JPanel(new GridLayout(1,1));
        JPanel panelAccounts = new JPanel(new BorderLayout());
        JPanel panelActions = new JPanel(layout);


        outputJTextArea = new JTextArea(employee.toString());
        outputJTextArea.setEnabled(false);
        panelOutput.add(outputJTextArea);

        customerJList = new JList(bank.getCustomers().toArray());

        panelAccounts.add(new JLabel("Account Picker"), BorderLayout.NORTH);
        panelAccounts.add(customerJList, BorderLayout.CENTER);

        JButton viewAccountButton = new JButton("View Account");
        JButton signoutButton = new JButton("Sign out");

        viewAccountButton.addActionListener(new ViewAccountButtonHandler());
        signoutButton.addActionListener(new SignoutActionListener());
        panelActions.add(viewAccountButton);


        add(panelOutput);//adding button on frame
        add(panelAccounts);//adding button on frame
        add(panelActions);//adding button on frame

        setTitle("Employee View Form");
        setSize(600,400);
        //setLayout(null);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    private class ViewAccountButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            // get the index
            int index = customerJList.getLeadSelectionIndex();
            System.out.println("ACCOUNT: " + index);

            // get the account
            Customer customer = bank.getCustomers().get(index);

            //TODO Error checking

            // sysout the customer name
            System.out.println("Open customer: " + customer);

            //TODO hide this window
            //BankEmployeeForm.this.setVisible(false);

            //TODO Open the customer history form
            //new CustomerVie(bank, customer, BankEmployeeForm.this).setVisible(true);
        }
    }

    private class SignoutActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            parentFrame.setVisible(true);
            BankEmployeeForm.this.dispose();
        }
    }
}
