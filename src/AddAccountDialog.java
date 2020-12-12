import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAccountDialog extends JDialog {

    private JTextField nameField, currencyField;
    private JRadioButton savingsRButton;
    private JRadioButton checkingRButton;
    private JRadioButton securitiesRButton;

    private JLabel titleLabel;

    JPanel content;


    public AddAccountDialog(JFrame parentFrame){
        super(parentFrame);

        titleLabel = new JLabel("Create Account: Savings");
        content = new JPanel(new BorderLayout());

        JPanel infoPanel = new JPanel();
        JPanel actionPanel = new JPanel();

        content.add(titleLabel, BorderLayout.NORTH);
        content.add(infoPanel, BorderLayout.CENTER);
        content.add(actionPanel, BorderLayout.SOUTH);

        ButtonGroup group = initRadioButtons();

        infoPanel.add(nameField);
        infoPanel.add(currencyField);
        infoPanel.add(group);

        actionPanel.add()


        setTitle("Add Account");
        // set up the action selection buttons

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        getContentPane().add(content);
        pack();
        setLocationRelativeTo(null);
    }


    public boolean createAccount(Bank bank, Customer customer){
        setVisible(true);





        return ;
    }

    private ButtonGroup initRadioButtons(){
        // set up radio button
        savingsRButton = new JRadioButton("Savings");
        savingsRButton.setActionCommand("Savings");
        savingsRButton.setSelected(true);
        checkingRButton = new JRadioButton("Checking");
        checkingRButton.setActionCommand("Checking");
        checkingRButton.setSelected(false);
        securitiesRButton = new JRadioButton("Securities");
        securitiesRButton.setActionCommand("Securities");
        securitiesRButton.setSelected(false);
        securitiesRButton.setEnabled(false);

        ButtonGroup group = new ButtonGroup();
        group.add(savingsRButton);
        group.add(checkingRButton);
        group.add(securitiesRButton);


    }

    /** Update the title to match the account type selected **/
    private class AccountTypeRadioButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            AddAccountDialog.this.titleLabel.setText("Create Account: " + e.getActionCommand());
        }
    }
}
