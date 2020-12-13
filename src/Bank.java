import java.util.*;

public class Bank implements Lender, Transferable
{
    private ArrayList<Customer> customers;
    private ArrayList<Employee> employees;
    private double reserves;
    private LinkedList<Transaction> transactions;
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
        setTransactions(new LinkedList<Transaction>());
        setSettings(new BankSettings());
        setBankRequestManager(BankRequestManager.getSingleInstance());
        setBankDB(BankDB.getSingleInstance());
    }

    public Bank(ArrayList<Customer> customers, ArrayList<Employee> employees, double reserves, LinkedList<Transaction> transactions, BankSettings settings)
    {
        setCustomers(customers);
        setEmployees(employees);
        setReserves(reserves);
        setTransactions(transactions);
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

    public void setTransactions(LinkedList<Transaction> transactions)
    {
        this.transactions = transactions;
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

    public LinkedList<Transaction> getTransactions()
    {
        return transactions;
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

    public boolean addTransaction(Transaction transaction)
    {
        //getTransactions().add(transaction);
        return true;
    }

    public void incrementDay()
    {

    }
}
