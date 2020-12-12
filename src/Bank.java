import java.util.*;

public class Bank implements Lender, Transferable
{
    private ArrayList<Customer> customers;
    private ArrayList<Employee> employees;
    private double reserves;
    private BankSettings settings;
    private BankRequestManager bankRequestManager;
    //private BankDB db;

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
        //setBankDB(BankDB.getSingleInstance);
    }

    public Bank(ArrayList<Customer> customers, ArrayList<Employee> employees, double reserves, BankSettings settings)
    {
        setCustomers(customers);
        setEmployees(employees);
        setReserves(reserves);
        setSettings(settings);
        setBankRequestManager(BankRequestManager.getSingleInstance());
        //setBankDB(BankDB.getSingleInstance);
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

    public something lend()
    {

    }
}
