public class SellAction extends BuyAction {
    
    SellAction(double money, String company, int shares)
    {
        super(money, company, shares);
    }

    public String toString()
    {
        return "SELL: " + "Sold " + Integer.toString(this.shares) + " shares for " + Integer.toString(this.money) + " dollars at the company, " + this.company;
    }
}
