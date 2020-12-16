/*
File: Withdraw.java
Developer: Tristan Marchand, Evan Boria, Suhail Singh 
Email: tmarch@bu.edu
Last Edited: Wednesday, December 16, 2020

Description: Subclass of Transaction representing a Withdraw
*/

public class Withdraw extends Transaction
{
    public Withdraw(int day, double money, BankAccount account)
    {
        super(account, money, day);
    }

    public Withdraw(int day, double money, String accountID)
    {
        super(accountID, money, day);
    }

    public String toString(){
        return "Day: " + getDay() + " Withdraw: " + String.format("%.2f",getMoney()) + " from " + getAccount();
    }
}
