public class BuyOrder extends StockOrder 
{
    protected String company;
    protected int shares;
    
    BuyOrder(double money, String company, int shares)
    {
        //super(money);
        this.company = company;
        this.shares = shares;
    }
}
