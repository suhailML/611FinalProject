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
        return "Day: " + getDay() + "\tDeposit: " + getMoney() + " from " + getAccount();
    }
}
