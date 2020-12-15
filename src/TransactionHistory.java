import java.util.*;

public class TransactionHistory 
{
    private List<Transaction> transactions;

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
    public void setTransactions(List<Transaction> transactions)
    {
        this.transactions = transactions;
    }

    /*
    ACCESSORS
    */
    public List<Transaction> getTransactions()
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


    public String toString(){
        String output = "Transaction History\n";

        for(Transaction transaction : transactions){
            output += " - " + transaction + "\n";
        }

        return output;
    }
}
