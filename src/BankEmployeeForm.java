import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * BankEmployeeForm
 * This form allows the Bank Employee to access different functions to control the bank.
 *
 * @author ejbosia
 */

public class BankEmployeeForm extends JFrame {

    private Bank bank;
    private Employee employee;

    private JList<Customer> customerJList;
    private JTextArea outputJTextArea;

    private JFrame parentFrame;

    private JButton incrementDay;

    public BankEmployeeForm(Bank bank, Employee employee, JFrame parentFrame){

        this.parentFrame = parentFrame;

        //TODO show the bad accounts on load

        this.bank = bank;
        this.employee = employee;

        GridLayout frameLayout = new GridLayout(1,3);
        frameLayout.setHgap(10);
        setLayout(frameLayout);

        GridLayout layout = new GridLayout(6,1, 10, 10);

        JPanel panelOutput = new JPanel(new GridLayout(1,1));
        JPanel panelAccounts = new JPanel(new BorderLayout());
        JPanel panelActions = new JPanel(layout);

        outputJTextArea = new JTextArea(employee.getFullOutput());
        outputJTextArea.setEnabled(false);
        panelOutput.add(outputJTextArea);

        customerJList = new JList(bank.getCustomers().toArray());

        panelAccounts.add(new JLabel("Account Picker"), BorderLayout.NORTH);
        panelAccounts.add(customerJList, BorderLayout.CENTER);

        JButton createEmployeeButton = new JButton("Create Employee");
        JButton editSettingsButton = new JButton("Edit Settings");
        JButton viewCustomerButton = new JButton("View Customer");
        JButton viewTransactionsButton = new JButton("View Transactions");
        incrementDay = new JButton("Increment Day: " + bank.getSettings().getDay());
        JButton signoutButton = new JButton("Sign out");

        createEmployeeButton.addActionListener(new CreateEmployeeActionListener());
        editSettingsButton.addActionListener(new EditSettingsActionListener());
        viewCustomerButton.addActionListener(new ViewCustomerActionListener());
        viewTransactionsButton.addActionListener((new ViewTransactionsActionListener()));
        incrementDay.addActionListener(new IncrementDayActionListener());
        signoutButton.addActionListener(new SignoutActionListener());

        panelActions.add(createEmployeeButton);
        panelActions.add(editSettingsButton);
        panelActions.add(viewCustomerButton);
        panelActions.add(viewTransactionsButton);
        panelActions.add(incrementDay);
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

    /** Color accounts that have < X balance **/
    private void colorBadAccounts(){

    }


    /** Open the bank settings editor form **/
    private class CreateEmployeeActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new CreateEmployeeDialog( BankEmployeeForm.this.bank, BankEmployeeForm.this).setVisible(true);
        }
    }


    /** Open the bank settings editor form **/
    private class EditSettingsActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            new BankSettingsForm(BankEmployeeForm.this, BankEmployeeForm.this.bank).setVisible(true);

        }
    }

    /** View the selected account **/
    private class ViewCustomerActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            try{
                // get the index
                Customer customer = customerJList.getSelectedValue();
                System.out.println("Open customer: " + customer);

                BankEmployeeForm.this.setVisible(false);

                new EmployeeViewCustomerForm(bank, customer, BankEmployeeForm.this).setVisible(true);

            }catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                JOptionPane.showMessageDialog(null, "NO ACCOUNT SELECTED", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ViewTransactionsActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            System.out.println("Open transactions view");

            new EmployeeViewTransactionsDialog(BankEmployeeForm.this.bank, BankEmployeeForm.this);
        }
    }

    private class IncrementDayActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            bank.getBankRequestManager().incrementDay(bank);
            System.out.println(bank.getSettings().getDay());
            incrementDay.setText("Increment Day: " + bank.getSettings().getDay());
        }
    }

    private class SignoutActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            parentFrame.setVisible(true);
            BankEmployeeForm.this.dispose();
        }
    }
}
