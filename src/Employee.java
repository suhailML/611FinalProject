public class Employee extends User implements EmployeeActions
{
    /*
    CONSTRUCTORS
    */
    public Employee(){}

    public Employee(String username, String password, String userID, String firstName, String lastName)
    {
        super(username, password, userID, firstName, lastName);
    }

    public String getFullOutput(){
        String output = "First Name: " + getFirstName() + "\n";
        output += "Last Name: " + getLastName() + "\n";
        output += "Username: " + getUsername() + "\n";
        output += "Password: " + getPassword() + "\n";
        return output;
    }
}
