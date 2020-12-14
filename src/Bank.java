import java.util.*;

public class Bank implements Transferable
{
    private ArrayList<Customer> customers;
    private ArrayList<Employee> employees;
    private double reserves;
    private BankSettings settings;
    private BankRequestManager bankRequestManager;
    private BankDB db;

    /*
    CONSTRUCTORS
    */
    public Bank()
    {
        setCustomers(new ArrayList<Customer>());
        setEmployees(new ArrayList<Employee>());
        setReserves(1000000);
        setSettings(new BankSettings());
        setBankRequestManager(BankRequestManager.getSingleInstance());
        setBankDB(BankDB.getSingleInstance());
    }

    public Bank(ArrayList<Customer> customers, ArrayList<Employee> employees, double reserves, BankSettings settings)
    {
        setCustomers(customers);
        setEmployees(employees);
        setReserves(reserves);
        setSettings(settings);
        setBankRequestManager(BankRequestManager.getSingleInstance());
        setBankDB(BankDB.getSingleInstance());
    }

    /*
    SETTERS
    */
    public void setCustomers(ArrayList<Customer> customers)
    {
        this.customers = customers;
    }

    public void setEmployees(ArrayList<Employee> employees)
    {
        this.employees = employees;
    }

    public void setReserves(double reserves)
    {
        this.reserves = reserves;
    }

    public void setSettings(BankSettings settings)
    {
        this.settings = settings;
    }

    public void setBankRequestManager(BankRequestManager bankRequestManager)
    {
        this.bankRequestManager = bankRequestManager;
    }

    public void setBankDB(BankDB db)
    {
        this.db = db;
    }

    /*
    ACCESSORS
    */
    public ArrayList<Customer> getCustomers()
    {
        return customers;
    }

    public ArrayList<Employee> getEmployees()
    {
        return employees;
    }

    public double getReserves()
    {
        return reserves;
    }

    public BankSettings getSettings()
    {
        return settings;
    }

    public BankRequestManager getBankRequestManager()
    {
        return bankRequestManager;
    }

    public BankDB getBankDB()
    {
        return db;
    }

    /*
    MUTATORS
    */

    public void addToReserves(double money)
    {
        reserves += money;
    }

    public void removeFromReserves(double money)
    {
        reserves -= money;
    }

    /*
    Transferable Methods
    */

    public boolean send(double money)
    {
        boolean valid = false;

        if (getReserves() > money)
        {
            removeFromReserves(money);
            valid = true;
        }

        return valid;
    }

    public boolean receive(double money)
    {
        boolean valid = false;
        addToReserves(money);
        valid = true;
        return valid;
    }

    public void incrementDay()
    {

    }

    public Customer getCustomer(String username, String password)
    {
        Customer customer;

        ArrayList<Customer> customers = getCustomers();
        Iterator<Customer> iter = customers.iterator();

        while (iter.hasNext())
        {
            Customer temp = iter.next();
            if (temp.getUsername().equals(username) && temp.getPassword().equals(password))
            {
                customer = temp;
                break;
            }
        }

        return customer;
    }

    public void addCustomer(Customer customer)
    {
        getCustomers().add(customer);
    }

    public void addEmployee(Employee employee)
    {
        getEmployees().add(employee);
    }

    public Employee getEmployee(String username, String password)
    {
        Employee employee;

        ArrayList<Employee> employees = getEmployees();
        Iterator<Employee> iter = employees.iterator();

        while (iter.hasNext())
        {
            Employee temp = iter.next();
            if (temp.getUsername().equals(username) && temp.getPassword().equals(password))
            {
                employee = temp;
                break;
            }
        }

        return employee;
    }
}
