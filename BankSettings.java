public class BankSettings 
{
    private static final double defaultTransactionFee = 1.00;
    private static final double defaultSavingsInterestRate = 0.01;
    private static final double defaultLoanInterestRate = 0.01;
    private static final double defaultMinSavingsForInterest = 5000.00;
    private double transactionFee;
    private double savingsInterestRate;
    private double loanInterestRate;
    private double minSavingsForInterest;

    /*
    CONSTRUCTORS
    */
    public BankSettings()
    {
        setTransactionFee(defaultTransactionFee);
        setSavingsInterestRate(defaultSavingsInterestRate);
        setLoanInterestRate(defaultLoanInterestRate);
        setMinSavingsForInterest(defaultMinSavingsForInterest);
    }

    public BankSettings(double transactionFee, double savingsInterestRate, double loanInterestRate, double minSavingsForInterest)
    {
        setTransactionFee(transactionFee);
        setSavingsInterestRate(savingsInterestRate);
        setLoanInterestRate(loanInterestRate);
        setMinSavingsForInterest(minSavingsForInterest);
    }

    /*
    SETTERS
    */
    public void setTransactionFee(double transactionFee)
    {
        this.transactionFee = transactionFee;
    }

    public void setSavingsInterestRate(double savingsInterestRate)
    {
        this.savingsInterestRate = savingsInterestRate;
    }

    public void setLoanInterestRate(double loanInterestRate)
    {
        this.loanInterestRate = loanInterestRate;
    }

    public void setMinSavingsForInterest(double minSavingsForInterest)
    {
        this.minSavingsForInterest = minSavingsForInterest;
    }

    /*
    ACCESSORS
    */
    public double getTransactionFee()
    {
        return transactionFee;
    }

    public double getSavingsInterestRate()
    {
        return savingsInterestRate;
    }

    public double getLoanInterestRate()
    {
        return loanInterestRate;
    }

    public double getMinSavingsForInterest()
    {
        return minSavingsForInterest;
    }
}
