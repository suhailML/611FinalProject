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
}
