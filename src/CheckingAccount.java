/*
File: CheckingAccount.java
Developer: Tristan Marchand, Evan Bosia
Email: tmarch@bu.edu
Last Edited: Wednesday, December 16, 2020

Description: Subclass of BankAccount, representing a Checking account
*/

public class CheckingAccount extends BankAccount {
    
    /*
    CONSTRUCTORS
    */
    public CheckingAccount(String name, String accountID, String currencyType, double balance, TransactionHistory transactions) {
        super(name, accountID, currencyType, balance, transactions);
    }

    public CheckingAccount(String name, String ID, String currencyType) {
        super(name, ID, currencyType);
    }

    public String fullOutput() {
        return "CHECKING " + super.fullOutput();
    }
}
