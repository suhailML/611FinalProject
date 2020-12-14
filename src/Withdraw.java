public class Withdraw extends Transaction
{
    public Withdraw(int day, double money, BankAccount account)
    {
        super(account, money, day);
    }

    public String toString(){
        return "Withdraw" + super.toString();
    }
}
