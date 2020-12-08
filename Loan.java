public class Loan 
{
    private Bank bank; // create Lender interface, Bank implements Lender
    private Customer customer; // create Lendee interface, Customer implements Lendee
    private int loanID; // implement
    private double initialValue;
    private double presentValue;
    private double interestRate;
    private String collateral; // implement
    
    /*
    CONSTRUCTORS
    */
    public Loan(){}

    public Loan(Bank bank, Customer customer, double initialValue, double presentValue, double interestRate)
    {
        setBank(bank);
        setCustomer(customer);
        setInitialValue(initialValue);
        setPresentValue(presentValue);
        setInterestRate(interestRate);
    }

    public Loan(Bank bank, Customer customer, double initialValue, double interestRate)
    {
        setBank(bank);
        setCustomer(customer);
        setInitialValue(initialValue);
        setPresentValue(initialValue);
        setInterestRate(interestRate);
    }

    /*
    SETTERS
    */
    public void setBank(Bank bank)
    {
        this.bank = bank;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public void setInitialValue(double value)
    {
        this.initialValue = value;
    }

    public void setPresentValue(double value)
    {
        this.presentValue = value;
    }

    public void setInterestRate(double interestRate)
    {
        this.interestRate = interestRate;
    }

    /*
    ACCESSORS
    */
    public Bank getBank()
    {
        return bank;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public double getInitialValue()
    {
        return initialValue;
    }

    public double getPresentValue()
    {
        return presentValue;
    }

    public double getInterestRate()
    {
        return interestRate;
    }
}
