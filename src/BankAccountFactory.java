import java.util.*;

/**
 * BankAccountFactory
 * Allows for different types of BankAccount classes to be created.
 */


public class BankAccountFactory 
{
    public BankAccountFactory() {}

    public SavingsAccount createNewSavingsAccount(String name, String currencyType)
    {
        String ID = UUID.randomUUID().toString();
        SavingsAccount account = new SavingsAccount(name, ID, currencyType);
        return account;
    }

    public SavingsAccount createExistingSavingsAccount(String name, String accountID, String currencyType, double balance, TransactionHistory transactions)
    {
        SavingsAccount account = new SavingsAccount(name, accountID, currencyType, balance, transactions);
        return account;
    }

    public CheckingAccount createNewCheckingAccount(String name, String currencyType)
    {
        String ID = UUID.randomUUID().toString();
        CheckingAccount account = new CheckingAccount(name, ID, currencyType);
        return account;
    }

    public CheckingAccount createExistingCheckingAccount(String name, String accountID, String currencyType, double balance, TransactionHistory transactions)
    {
        CheckingAccount account = new CheckingAccount(name, accountID, currencyType, balance, transactions);
        return account;
    }
}
