/*
File: TransactionFactory.java
Developer: Tristan Marchand
Email: tmarch@bu.edu
Last Edited: Wednesday, December 16, 2020

Description: Uses Factory design pattern. Creates Transaction subclass objects.
*/

public class TransactionFactory 
{
    public TransactionFactory(){}

    public Withdraw getWithdraw(int day, double money, BankAccount account)
    {
        Withdraw withdraw = new Withdraw(day, money, account);
        return withdraw;
    }

    public Withdraw getExistingWithdraw(int day, double money, String accountID)
    {
        Withdraw withdraw = new Withdraw(day, money, accountID);
        return withdraw;
    }

    public Deposit getDeposit(int day, double money, BankAccount account)
    {
        Deposit deposit = new Deposit(day, money, account);
        return deposit;
    }

    public Deposit getExistingDeposit(int day, double money, String accountID)
    {
        Deposit deposit = new Deposit(day, money, accountID);
        return deposit;
    }

    public Transfer getTransfer(int day, double money, BankAccount account, Transferable sender, Transferable receiver)
    {
        Transfer transfer = new Transfer(account, money, day, sender, receiver);
        return transfer;
    }

    public Transfer getExistingTransfer(int day, double money, String accountID, String senderName, String receiverName)
    {
        Transfer transfer = new Transfer(accountID, money, day, senderName, receiverName);
        return transfer;
    }

}
