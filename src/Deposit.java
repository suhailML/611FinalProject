/*
File: Deposit.java
Developer: Tristan Marchand, Evan Bosia, Suhail Singh
Email: tmarch@bu.edu
Last Edited: Wednesday, December 16, 2020

Description: Subclass of Transaction representing a Deposit
*/

public class Deposit extends Transaction 
{
    public Deposit(int day, double money, BankAccount account)
    {
        super(account, money, day);
    }

    public Deposit(int day, double money, String accountID)
    {
        super(accountID, money, day);
    }

    public String toString(){
        return "Day: " + getDay() + " Deposit: " + String.format("%.2f",getMoney()) + " to " + getAccount();
    }
}
