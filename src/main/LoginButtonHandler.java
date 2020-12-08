import java.awt.event.ActionEvent;

public class LoginButtonHandler{
    public void actionPerformed(ActionEvent e) {
        new UserForm().setVisible(true); // Main Form to show after the Login Form..
    }
}