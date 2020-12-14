public class SellOrder extends StockOrder {
    
    SellOrder(BankAccount account, double money, String company, int shares, int day)
    {
        super(account, money, company, shares, day);
    }

    public String toString()
    {
        return "SELL: " + "Sold " + getShares() + " shares for " + getMoney() + " dollars at the company, " + getCompany();
    }
}
