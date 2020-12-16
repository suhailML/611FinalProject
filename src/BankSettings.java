/*
File: BankSettings.java
Developer: Tristan Marchand, Evan Bosia
Email: tmarch@bu.edu
Last Edited: Wednesday, December 16, 2020

Description: Stores all settings for a bank
*/

public class BankSettings 
{
    private static final double defaultTransactionFee = 1.00;
    private static final double defaultSavingsInterestRate = 0.01;
    private static final double defaultLoanInterestRate = 0.01;
    private static final double defaultMinSavingsForInterest = 5000.00;
    private static final int defaultDay = 1;

    private static final Character[] currencyArray = {'$', '\u20AC', '\u00A3', '\u00A5'};
    private double transactionFee;
    private double savingsInterestRate;
    private double loanInterestRate;
    private double minSavingsForInterest;
    private int day;

    /*
    CONSTRUCTORS
    */
    public BankSettings()
    {
        setTransactionFee(defaultTransactionFee);
        setSavingsInterestRate(defaultSavingsInterestRate);
        setLoanInterestRate(defaultLoanInterestRate);
        setMinSavingsForInterest(defaultMinSavingsForInterest);
        setDay(defaultDay);
    }

    public BankSettings(double transactionFee, double savingsInterestRate, double loanInterestRate, double minSavingsForInterest, int day)
    {
        setTransactionFee(transactionFee);
        setSavingsInterestRate(savingsInterestRate);
        setLoanInterestRate(loanInterestRate);
        setMinSavingsForInterest(minSavingsForInterest);
        setDay(day);
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

    public void setDay(int day)
    {
        this.day = day;
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

    public int getDay()
    {
        return day;
    }

    /*
    MUTATORS
    */
    public void incrementDay()
    {
        this.day++;
    }

    public Character[] getCurrencyArray(){
        return currencyArray;
    }

    public String toString(){

        String output = "BANK SETTINGS\n";
        output += "Transaction Fee: " + transactionFee + "\n";
        output += "Savings Interest Rate: " + transactionFee + "\n";
        output += "Loan Interest Rate: " + transactionFee + "\n";
        output += "Min Savings Balance Interest: " + minSavingsForInterest + "\n";
        output += "Current Day: " + day + "\n";
        return output;
    }
}
