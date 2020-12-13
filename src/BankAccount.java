import java.util.*;

public abstract class BankAccount implements Transferable
{
    private String name;
    private String accountID;
    private String currencyType;
    private double balance;
    //TODO remove this
    private LinkedList<Transaction> transactions;

    //TODO implement this
    //private TransactionHistory history;
    private Customer customer;

    /*
    CONSTRUCTORS
    */
    public BankAccount(){}

    public BankAccount(String name, String accountID, String currencyType, double balance, LinkedList<Transaction> transactions)
    {
        setName(name);
        setAccountID(accountID);
        setCurrencyType(currencyType);
        setBalance(balance);
        setTransactions(transactions);
    }

    public BankAccount(String name, String accountID, String currencyType)
    {
        setName(name);
        setAccountID(accountID);
        setCurrencyType(currencyType);
        setBalance(0);
        setTransactions(new LinkedList<Transaction>());
    }

    /*
    SETTERS
    */
    public void setName(String name)
    {
        this.name = name;
    }

    public void setAccountID(String accountID)
    {
        this.accountID = accountID;
    }

    public void setCurrencyType(String currencyType)
    {
        this.currencyType = currencyType;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    public void setTransactions(LinkedList<Transaction> transactions)
    {
        this.transactions = transactions;
    }

    /*
    ACCESSORS
    */
    public String getName()
    {
        return name;
    }

    public String getAccountID()
    {
        return accountID;
    }

    public String getCurrencyType()
    {
        return name;
    }

    public double getBalance()
    {
        return balance;
    }

    public LinkedList<Transaction> getTransactions()
    {
        return transactions;
    }

    /*
    MUTATORS
    */

    public boolean addTransaction(Transaction transaction)
    {
        getTransactions().add(transaction);
        return true;
    }

    /*
    Bank Account Actions Methods
    */
    public boolean send(double value)
    {
        if (value > getBalance())
        {
            return false;
        }
        setBalance(getBalance()-value);
        return true;
    }

    public boolean receive(double value)
    {
        setBalance(getBalance() + value);
        return true;
    }


    // TODO maybe we want to check this first
    public boolean isValidWithdraw(double money){
        System.out.println("NOT IMPLEMENTED - isValidWithdraw BankAccount");
        return true;
    }

    public String fullOutput(){
        return "ACCOUNT: " + name + "\n\t" + accountID + "\nCurrency: " + currencyType;
    }

    public String toString(){
        return name;
    }
}