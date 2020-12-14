public class Deposit extends Transaction 
{
    public Deposit(int day, double money, BankAccount account)
    {
        super(account, money, day);
    }

    public String toString(){
        return "DEPOSIT" + super.toString();
    }
}
