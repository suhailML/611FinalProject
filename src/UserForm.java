import javax.swing.*;
import java.awt.*;

public class UserForm extends JFrame {

    public UserForm(String text){

        GridLayout frameLayout = new GridLayout(1,3);
        frameLayout.setHgap(10);
        setLayout(frameLayout);

        GridLayout layout = new GridLayout(3,1);
        layout.setHgap(10);
        layout.setVgap(10);

        JPanel panelOutput = new JPanel(new GridLayout(1,1));
        JPanel panelAccounts = new JPanel();
        JPanel panelActions = new JPanel(layout);


        panelOutput.add(new JTextArea("USERNAME: " + text));

        String[] test = {"A", "B", "C", "D", "E"};
        JList<String> comboTest = new JList<String>(test);

        panelAccounts.add(new JLabel("Account Picker"));
        panelAccounts.add(comboTest);

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
}
