import java.util.*;

public class SavingsAccount extends BankAccount 
{
    /*
    CONSTRUCTORS
    */
    public SavingsAccount(){}

    public SavingsAccount(String name, String accountID, String currencyType, double balance, LinkedList<Transaction> transactions)
    {
        super(name, accountID, currencyType, balance, transactions);
    }

    public SavingsAccount(String name, String accountID, String currencyType)
    {
        super(name, accountID, currencyType);
    }
}
