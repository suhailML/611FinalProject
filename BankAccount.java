import java.util.*;

public abstract class BankAccount implements BankAccountActions
{
    private static int nextAccountNum = 00000; // move to BankSettings
    private String name;
    private int accountNumber;
    private String currencyType;
    private double balance;
    private LinkedList<Transaction> transactions;

    /*
    CONSTRUCTORS
    */
    public BankAccount(){}

    public BankAccount(String name, int accountNumber, String currencyType, double balance, LinkedList<Transaction> transactions)
    {
        setName(name);
        setAccountNumber(accountNumber);
        setCurrencyType(currencyType);
        setBalance(balance);
        setTransactions(transactions);
    }

    public BankAccount(String name, String currencyType)
    {
        setName(name);
        setAccountNumber(nextAccountNum);
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

    public void setAccountNumber(int accountNumber)
    {
        this.accountNumber = accountNumber;
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

    public int getAccountNumber()
    {
        return accountNumber;
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
    Bank Account Actions Methods
    */
    public boolean withdraw(double value)
    {
        if (value > getBalance())
        {
            return false;
        }
        setBalance(getBalance()-value);
        return true;
    }

    public boolean deposit(double value)
    {
        setBalance(getBalance() + value);
        return true;
    }
}
