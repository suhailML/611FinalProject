import java.util.*;

public class SavingsAccount extends BankAccount 
{
    /*
    CONSTRUCTORS
    */
    public SavingsAccount(){}

    public SavingsAccount(String name, int accountNumber, String currencyType, double balance, LinkedList<Transaction> transactions)
    {
        super(name, accountNumber, currencyType, balance, transactions);
    }

    public SavingsAccount(String name, String currencyType)
    {
        super(name, currencyType);
    }
}
