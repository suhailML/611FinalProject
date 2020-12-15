public class Employee extends User
{
    /*
    CONSTRUCTORS
    */
    public Employee(){}

    public Employee(String username, String password, String userID, String firstName, String lastName)
    {
        super(username, password, userID, firstName, lastName);
    }

    public String fullOutput(){
        return "Employee:\n" + super.fullOutput()+ "\n";
    }
}
