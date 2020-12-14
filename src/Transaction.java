public abstract class Transaction 
{
    private BankAccount account;
    private double money;
    private int day;



    public Transaction(BankAccount account, double money, int day)
    {
        setAccount(account);
        setMoney(money);
        setDay(day);
    }

    /*
    SETTERS
    */

    public void setAccount(BankAccount account)
    {
        this.account = account;
    }

    public void setMoney(double money)
    {
        this.money = money;
    }

    public void setDay(int day)
    {
        this.day = day;
    }

    /*
    ACCESSORS
    */

    public BankAccount getAccount()
    {
        return account;
    }

    public double getMoney()
    {
        return money;
    }

    public int getDay()
    {
        return day;
    }


    public String toString(){
        return account.getCurrencyType() + money + " - " + account.getName() + " DAY: " + day;
    }

}
