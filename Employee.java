public class Employee extends User implements EmployeeActions
{
    /*
    CONSTRUCTORS
    */
    public Employee(){}

    public Employee(String username, String password, int userId, String firstName, String lastName)
    {
        super(username, password, userId, firstName, lastName);
    }
}
