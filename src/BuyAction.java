public class BuyAction extends TransactionAction {
    protected String company;
    protected int shares;
    
    BuyAction(double money, String company, int shares)
    {
        super(money);
        this.company = company;
        this.shares = shares;
    }
}
