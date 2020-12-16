/**
 * BuyOrder extends StockOrder
 * Records the order to buy stock ~ not implemented.
 *
 * @author ejbosia
 */


public class BuyOrder extends StockOrder
{
    
    BuyOrder(BankAccount account, double money, String company, int shares, int day)
    {
        super(account, money, company, shares, day);
    }

    public String toString()
    {
        return "BUY: " + "Bought " + getShares() + " shares for " + getMoney() + " dollars at the company, " + getCompany();
    }
}
