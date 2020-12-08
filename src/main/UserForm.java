import javax.swing.*;
import java.awt.*;

public class UserForm extends JFrame {

    public UserForm(){

        GridLayout layout = new GridLayout(1,1);
        layout.setHgap(10);
        layout.setVgap(10);

        JPanel panel = new JPanel(layout);



        panel.add(new JLabel("YOU DID IT"));

        add(panel, BorderLayout.CENTER);//adding button on frame

        setTitle("Login Form");
        setSize(400,300);
        //setLayout(null);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
