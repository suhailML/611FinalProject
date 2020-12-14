import java.util.*;

public class UserFactory 
{
    public UserFactory(){}

    /*
    createNewCustomer - used during program runtime to create new customer
    */
    public Customer createNewCustomer(String username, String password, String firstName, String lastName)
    {
        String ID = UUID.randomUUID().toString();
        Customer customer = new Customer(username, password, ID, firstName, lastName);
        return customer;
    }

    /*
    createExistingCustomer - used to create customer already in database
    */
    public Customer createExistingCustomer(String username, String password, String userID, String firstName, String lastName, List<BankAccount> accounts, List<Loan> loans)
    {
        Customer customer = new Customer(username, password, userID, firstName, lastName, accounts, loans);
        return customer;
    }

    /*
    createNewEmployee - used during program runtime to create new employee
    */
    public Employee createNewEmployee(String username, String password, String firstName, String lastName)
    {
        String ID = UUID.randomUUID().toString();
        Employee employee = new Employee(username, password, ID, firstName, lastName);
        return employee;
    }

    /*
    createExistingEmployee - used to create employee already in database
    */
    public Employee createExistingEmployee(String username, String password, String userID, String firstName, String lastName)
    {
        Employee employee = new Employee(username, password, userID, firstName, lastName);
        return employee;
    }
}
