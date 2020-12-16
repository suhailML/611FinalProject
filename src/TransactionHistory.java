/*
File: TransactionHistory.java
Developer: Tristan Marchand, Evan Boria
Email: tmarch@bu.edu
Last Edited: Wednesday, December 16, 2020

Description: Holds a list of all transactions for an account
*/

/*
Imported Libraries
*/
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

    /** Output all of the transactions in the transaction history) **/
    public String toString(){
        String output = "Transaction History\n";

        Collections.sort(transactions);

        for(Transaction transaction : transactions){
            output += " - " + transaction + "\n";
        }

        return output;
    }
}
