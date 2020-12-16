/**
 * StockOrder extends Transaction
 * This is the super class to the buy and sell stock orders ~ were we to implement stock trading, this would be recorded as a transaction.
 *
 * @author ejbosia
 */

public class StockOrder extends Transaction
{
    private String company;
    private int shares;

    public StockOrder(BankAccount account, double money, String company, int shares, int day){
        super(account, money, day);
        setCompany(company);
        setShares(shares);
    }

    public void setCompany(String company){
        this.company = company;
    }

    public void setShares(int shares){
        this.shares = shares;
    }

    public String getCompany(){
        return company;
    }

    public int getShares(){
        return shares;
    }
}
