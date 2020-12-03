import javax.swing.*;
import java.awt.*;

public class AccountForm extends JFrame {

    public AccountForm(){

        add(new JButton("AHAHA"), BorderLayout.CENTER);

        setTitle("ACCOUNT FORM");
        setSize(600,300);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        System.out.println("DONE");
    }
}
