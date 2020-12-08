import java.util.*;

public class CheckingAccount extends BankAccount
{
    /*
    CONSTRUCTORS
    */
    public CheckingAccount(){}

    public CheckingAccount(String name, int accountNumber, String currencyType, double balance, LinkedList<Transaction> transactions)
    {
        super(name, accountNumber, currencyType, balance, transactions);
    }

    public CheckingAccount(String name, String currencyType)
    {
        super(name, currencyType);
    }

    
}
