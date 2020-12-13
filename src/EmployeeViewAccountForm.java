import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeViewAccountForm extends JFrame {

    private Bank bank;
    private BankAccount account;

    private JList transactionJList;
    private JTextArea outputJTextArea;

    private JFrame parentFrame;

    public EmployeeViewAccountForm(Bank bank, BankAccount account, JFrame parentFrame){

        this.parentFrame = parentFrame;

        this.outputJTextArea = new JTextArea(account.toString());
        this.outputJTextArea.setEnabled(false);

        this.bank = bank;
        this.account = account;

        GridLayout frameLayout = new GridLayout(1,2, 10, 0);
        setLayout(frameLayout);

        GridLayout buttonPanelLayout = new GridLayout(5,1);
        buttonPanelLayout.setHgap(10);
        buttonPanelLayout.setVgap(10);

        JPanel panelOutput = new JPanel(new GridLayout(1,1));
        JPanel panelTransactions = new JPanel(new BorderLayout());
        JPanel panelActions = new JPanel(buttonPanelLayout);


        panelOutput.add(outputJTextArea);

        transactionJList = new JList(account.getTransactions().toArray());

        panelTransactions.add(new JLabel("Transaction Viewer"), BorderLayout.NORTH);
        panelTransactions.add(transactionJList, BorderLayout.CENTER);


        JLabel blankLabel = new JLabel();
        JButton buttonBack = new JButton("Back");

        buttonBack.addActionListener(new BackActionListener());
        panelActions.add(blankLabel);
        panelActions.add(blankLabel);
        panelActions.add(blankLabel);
        panelActions.add(blankLabel);
        panelActions.add(buttonBack);

        add(panelOutput);//adding button on frame
        add(panelActions);//adding button on frame

        setTitle("Employee View Account Form");
        setSize(600,400);
        //setLayout(null);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    private class BackActionListener implements  ActionListener{
        public void actionPerformed(ActionEvent e) {
            System.out.println("ACTION: return to user form");
            EmployeeViewAccountForm.this.dispose();
            parentFrame.setVisible(true);

        }
    }
}
