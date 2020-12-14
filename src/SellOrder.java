public class SellAction extends StockOrder {
    
    SellAction(double money, String company, int shares)
    {
        super(money, company, shares);
    }

    public String toString()
    {
        return "SELL: " + "Sold " + Integer.toString(this.shares) + " shares for " + Double.toString(this.money) + " dollars at the company, " + this.company;
    }
}
