import java.util.*;

public class CheckingAccount extends BankAccount
{
    /*
    CONSTRUCTORS
    */
    public CheckingAccount(){}

    public CheckingAccount(String name, String accountID, String currencyType, double balance, LinkedList<Transaction> transactions)
    {
        super(name, accountID, currencyType, balance, transactions);
    }

    public CheckingAccount(String name, String ID, String currencyType)
    {
        super(name, ID, currencyType);
    }

    
}
