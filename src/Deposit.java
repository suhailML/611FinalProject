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
        return "Day: " + getDay() + " Deposit: " + String.format("%.2f",getMoney()) + " from " + getAccount();
    }
}
