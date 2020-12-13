import java.util.*;

public class BankAccountFactory 
{
    public BankAccountFactory() {}

    public SavingsAccount createNewSavingsAccount(String name, String accountID, String currencyType)
    {
        String ID = UUID.randomUUID().toString();
        SavingsAccount account = new SavingsAccount(name, accountID, currencyType);
        return account;
    }

    public SavingsAccount createExistingSavingsAccount(String name, String accountID, String currencyType, double balance, LinkedList<Transaction> transactions)
    {
        SavingsAccount account = new SavingsAccount(name, accountID, currencyType, balance, transactions);
        return account;
    }

    public CheckingAccount createNewCheckingAccount(String name, String accountID, String currencyType)
    {
        String ID = UUID.randomUUID().toString();
        CheckingAccount account = new CheckingAccount(name, accountID, currencyType);
        return account;
    }

    public CheckingAccount createExistingCheckingAccount(String name, String accountID, String currencyType, double balance, LinkedList<Transaction> transactions)
    {
        CheckingAccount account = new CheckingAccount(name, accountID, currencyType, balance, transactions);
        return account;
    }
}
