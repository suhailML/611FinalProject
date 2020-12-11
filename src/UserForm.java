import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class UserForm extends JFrame {

    private Bank bank;
    private Customer customer;

    private JList<BankAccount> accountJList;

    public UserForm(Bank bank, Customer customer){

        GridLayout frameLayout = new GridLayout(1,3);
        frameLayout.setHgap(10);
        setLayout(frameLayout);

        GridLayout layout = new GridLayout(3,1);
        layout.setHgap(10);
        layout.setVgap(10);

        JPanel panelOutput = new JPanel(new GridLayout(1,1));
        JPanel panelAccounts = new JPanel(new BorderLayout());
        JPanel panelActions = new JPanel(layout);


        panelOutput.add(new JTextArea(customer.toString()));

        accountJList = new JList(customer.getAccounts().toArray());

        panelAccounts.add(new JLabel("Account Picker"), BorderLayout.NORTH);
        panelAccounts.add(accountJList, BorderLayout.CENTER);

        JButton buttonAddAccount = new JButton("Add Account");
        JButton buttonViewAccount = new JButton("View Account");
        JButton buttonDeleteAccount = new JButton("Delete Account");;

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

            //TODO Open the Account form

        }
    }

    private class DeleteAccountButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {

        }
    }
}
