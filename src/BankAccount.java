/**
 * BankAccount implements Transferable
 * The BankAccount abstract class provides the framework to create different types of BankAccount subclasses
 *
 *
 */


public abstract class BankAccount implements Transferable
{
    private String name;
    private String accountID;
    private String currencyType;
    private double balance;
    private TransactionHistory transactionHistory;

    /*
    CONSTRUCTORS
    */
    public BankAccount(){}

    public BankAccount(String name, String accountID, String currencyType, double balance, TransactionHistory transactionHistory)
    {
        setName(name);
        setAccountID(accountID);
        setCurrencyType(currencyType);
        setBalance(balance);
        setTransactionHistory(transactionHistory);
    }

    public BankAccount(String name, String accountID, String currencyType)
    {
        setName(name);
        setAccountID(accountID);
        setCurrencyType(currencyType);
        setBalance(0);
        setTransactionHistory(new TransactionHistory());
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

    public void setTransactionHistory(TransactionHistory transactionHistory)
    {
        this.transactionHistory = transactionHistory;
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
        return currencyType;
    }

    public double getBalance()
    {
        return balance;
    }

    public TransactionHistory getTransactionHistory()
    {
        return transactionHistory;
    }

    /*
    MUTATORS
    */

    public boolean addTransaction(Transaction transaction)
    {
        transactionHistory.addTransaction(transaction);
        //getTransactionHistory().addTransaction(transaction);
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

    public String fullOutput(){

        String output = "ACCOUNT\n";
        output += " - Account name: " + name + "\n";
        output += " - Account ID: " + accountID + "\n";
        output += " - Balance: " + currencyType + balance;
        return output;
    }

    public String toString(){
        return name + " - " + currencyType + balance;
    }
}
