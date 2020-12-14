import java.util.*;

public class Bank implements Transferable
{
    private List<Customer> customers;
    private List<Employee> employees;
    private double reserves;
    private BankSettings settings;
    private BankRequestManager bankRequestManager;
    private BankDatabase db;

    /*
    CONSTRUCTORS
    */
    public Bank()
    {
        setCustomers(new ArrayList<Customer>());
        setEmployees(new ArrayList<Employee>());
        setReserves(0);
        setSettings(new BankSettings());
        setBankRequestManager(BankRequestManager.getSingleInstance());
        setBankDB(BankDatabase.getSingleInstance());
    }

    public Bank(ArrayList<Customer> customers, ArrayList<Employee> employees, double reserves, BankSettings settings)
    {
        setCustomers(customers);
        setEmployees(employees);
        setReserves(reserves);
        setSettings(settings);
        setBankRequestManager(BankRequestManager.getSingleInstance());
        setBankDB(BankDatabase.getSingleInstance());
    }

    /*
    SETTERS
    */
    public void setCustomers(List<Customer> customers)
    {
        this.customers = customers;
    }

    public void setEmployees(List<Employee> employees)
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

    public void setBankDB(BankDatabase db)
    {
        this.db = db;
    }

    /*
    ACCESSORS
    */
    public List<Customer> getCustomers()
    {
        return customers;
    }

    public List<Employee> getEmployees()
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

    public BankDatabase getBankDB()
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

    public String getName()
    {
        return "Bank";
    }


    public Customer getCustomer(String username, String password)
    {
        Customer customer = null;

        List<Customer> customers = getCustomers();
        Iterator<Customer> iter = customers.iterator();


        while (iter.hasNext())
        {

            Customer temp = iter.next();
            System.out.println(temp.getUsername() + " " + temp.getPassword());
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
        Employee employee = null;

        List<Employee> employees = getEmployees();
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
