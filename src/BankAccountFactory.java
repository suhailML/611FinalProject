/*
File: BankAccountFactory.java
Developer: Tristan Marchand
Email: tmarch@bu.edu
Last Edited: Wednesday, December 16, 2020

Description: Uses Factory design pattern. Allows for different types of BankAccount classes to be created.
*/

/*
Imported Libraries
*/
import java.util.*;

public class BankAccountFactory 
{
    /*
    CONSTRUCTORS
    */
    public BankAccountFactory() {}

    /*
    createNewSavingsAccount - creates a new SavingsAccount object using a name and currencyType
    */
    public SavingsAccount createNewSavingsAccount(String name, String currencyType)
    {
        String ID = UUID.randomUUID().toString();
        SavingsAccount account = new SavingsAccount(name, ID, currencyType);
        return account;
    }

    /*
    createExistingSavingsAccount - creates a SavingsAccount object based on info from database
    */
    public SavingsAccount createExistingSavingsAccount(String name, String accountID, String currencyType, double balance, TransactionHistory transactions)
    {
        SavingsAccount account = new SavingsAccount(name, accountID, currencyType, balance, transactions);
        return account;
    }

    /*
    createNewCheckingAccount - creates a new CheckingAccount object using a name and currencyType
    */
    public CheckingAccount createNewCheckingAccount(String name, String currencyType)
    {
        String ID = UUID.randomUUID().toString();
        CheckingAccount account = new CheckingAccount(name, ID, currencyType);
        return account;
    }

    /*
    createExistingCheckingAccount - creates a CheckingAccount object based on info from database
    */
    public CheckingAccount createExistingCheckingAccount(String name, String accountID, String currencyType, double balance, TransactionHistory transactions)
    {
        CheckingAccount account = new CheckingAccount(name, accountID, currencyType, balance, transactions);
        return account;
    }
}
