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
        return "WITHDRAW " + super.toString();
    }
}
