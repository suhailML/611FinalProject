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
