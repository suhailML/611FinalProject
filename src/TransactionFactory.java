public class TransactionFactory 
{
    public TransactionFactory(){}

    public Withdraw getWithdraw(int day, double money, BankAccount account)
    {
        Withdraw withdraw = new Withdraw(day, money, account);
        return withdraw;
    }

    public Deposit getDeposit(int day, double money, BankAccount account)
    {
        Deposit deposit = new Deposit(day, money, account);
        return deposit;
    }

    public Transfer getTransfer(int day, double money, Transferable sender, Transferable receiver){
        return new Transfer(day, money, sender, receiver);
    }

}
