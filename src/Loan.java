/*
File: Loan.java
Developer: Tristan Marchand, Evan Bosia
Email: tmarch@bu.edu
Last Edited: Wednesday, December 16, 2020

Description: An object representing a Loan
*/

/*
Imported Libraries
*/
public class Loan 
{
    private Transferable lender; 
    private Transferable lendee;
    private String loanID;
    private double initialValue;
    private double presentValue;
    private double interestRate;
    private String collateral;
    
    /*
    CONSTRUCTORS
    */
    public Loan(){}

    public Loan(Transferable lender, Transferable lendee, String loanID, double initialValue, double presentValue, double interestRate, String collateral)
    {
        setLender(lender);
        setLendee(lendee);
        setLoanID(loanID);
        setInitialValue(initialValue);
        setPresentValue(presentValue);
        setInterestRate(interestRate);
        setCollateral(collateral);
    }

    public Loan(Transferable lender, Transferable lendee, String loanID, double initialValue, double interestRate, String collateral)
    {
        setLender(lender);
        setLendee(lendee);
        setLoanID(loanID);
        setInitialValue(initialValue);
        setPresentValue(initialValue);
        setInterestRate(interestRate);
        setCollateral(collateral);
    }

    /*
    SETTERS
    */
    public void setLender(Transferable lender)
    {
        this.lender = lender;
    }

    public void setLendee(Transferable lendee)
    {
        this.lendee = lendee;
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

    public void setLoanID(String loanID)
    {
        this.loanID = loanID;
    }

    public void setCollateral(String collateral)
    {
        this.collateral = collateral;
    }

    /*
    ACCESSORS
    */
    public Transferable getLender()
    {
        return lender;
    }

    public Transferable getLendee()
    {
        return lendee;
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

    public String getLoanID()
    {
        return loanID;
    }

    public String getCollateral()
    {
        return collateral;
    }

    /*
    Other Methods
    */

    public void payBack(double money)
    {
        presentValue -= money;
    }

    public void compoundInterest()
    {
        presentValue += presentValue*interestRate;
    }

    public String fullOutput(){
        String output = "Loan: " + loanID + "\n";

        output += " - initial value: " + String.format("%.2f",initialValue) + "\n";
        output += " - present value: " + String.format("%.2f",presentValue) + "\n";
        output += " - interest rate: " + interestRate + "\n";
        output += " - lender name: " + lender.getName() + "\n";
        output += " - lendee name: " + lendee.getName() + "\n";
        output += " - collateral: " + collateral + "\n";

        return output;
    }

    public String toString(){
        return loanID;
    }
}
