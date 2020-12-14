public abstract class Transaction 
{
    private String accountID;
    private double money;
    private int day;



    public Transaction(BankAccount account, double money, int day)
    {
        setAccount(account.getAccountID());
        setMoney(money);
        setDay(day);
    }

    public Transaction(String accountID, double money, int day)
    {
        setAccount(accountID);
        setMoney(money);
        setDay(day);
    }

    /*
    SETTERS
    */

    public void setAccount(String account)
    {
        this.accountID = account;
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

    public String getAccount()
    {
        return accountID;
    }

    public double getMoney()
    {
        return money;
    }

    public int getDay()
    {
        return day;
    }

    /*
    public String toString(){
        return sender.getName() + " --> " + receiver.getName() + " : " + money;
    }
    */
}
