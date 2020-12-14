import java.util.*;

public class TransactionHistory 
{
    private ArrayList<Transaction> transactions;

    /*
    CONSTRUCTORS
    */
    public TransactionHistory()
    {
        setTransactions(new ArrayList<Transaction>());
    }

    /*
    SETTERS
    */
    public void setTransactions(ArrayList<Transaction> transactions)
    {
        this.transactions = transactions;
    }

    /*
    ACCESSORS
    */
    public ArrayList<Transaction> getTransactions()
    {
        return transactions;
    }

    /*
    MUTATORS
    */
    public void addTransaction(Transaction transaction)
    {
        getTransactions().add(transaction);
    }
}
