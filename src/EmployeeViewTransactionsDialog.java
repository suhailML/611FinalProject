
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * EmployeeViewTransactionsDialog
 * Allow the employee to view all of the transactions for a given day.
 *
 * @author ejbosia
 */

public class EmployeeViewTransactionsDialog extends JDialog {

    private Bank bank;

    private JTextArea ouputTextArea;

    private JSpinner daySelectSpinner;

    public EmployeeViewTransactionsDialog(Bank bank, JFrame parentFrame){
        super(parentFrame);

        this.bank = bank;

        setSize(700,400);

        JPanel infoPanel = new JPanel();
        ouputTextArea = new JTextArea(20, 50);

        JScrollPane scrollPane = new JScrollPane( ouputTextArea );
        infoPanel.add(scrollPane);


        JPanel actionPanel = new JPanel();

        add(infoPanel, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);

        daySelectSpinner = new JSpinner(new SpinnerNumberModel( bank.getSettings().getDay(),0,  bank.getSettings().getDay(), 1));
        JButton confirmButton = new JButton("Query");
        JButton cancelButton = new JButton("Cancel");


        confirmButton.addActionListener(new QueryTransactionActionListener());
        cancelButton.addActionListener(new CancelActionListener());

        actionPanel.add(new JLabel("Day:"));
        actionPanel.add(daySelectSpinner);
        actionPanel.add(confirmButton);
        actionPanel.add(cancelButton);


        setTitle("Date Transaction Viewer");
        // set up the action selection buttons

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(null);
    }


    /** Update the title to match the account type selected **/
    private class QueryTransactionActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            // casting this ~ there are only ever integers for day
            int day =(int)EmployeeViewTransactionsDialog.this.daySelectSpinner.getValue();

            System.out.println("Query Transactions");

            EmployeeViewTransactionsDialog.this.ouputTextArea.setText(bank.getBankRequestManager().queryTransactions(bank, day));
        }
    }


    /** Update the title to match the account type selected **/
    private class CancelActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Cancel");
            EmployeeViewTransactionsDialog.this.dispose();
        }
    }
}
