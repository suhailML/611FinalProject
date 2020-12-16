/*
File: LoanFactory.java
Developer: Tristan Marchand
Email: tmarch@bu.edu
Last Edited: Wednesday, December 16, 2020

Description: Uses Factory design pattern. Creates loan objects
*/

/*
Imported Libraries
*/
import java.util.*;

public class LoanFactory 
{
    /*
    CONSTRUCTORS
    */
    public LoanFactory() {}

    /*
    createNewLoan - creates a new loan object with info from the GUI
    */
    public Loan createNewLoan(Transferable lender, Transferable lendee, double initialValue, double interestRate, String collateral)
    {
        String ID = UUID.randomUUID().toString();
        Loan loan = new Loan(lender, lendee, ID, initialValue, interestRate, collateral);
        return loan;
    }

    /*
    createExistingLoan - creates a loan object with info from the database
    */
    public Loan createExistingLoan(Transferable lender, Transferable lendee, String loanID, double initialValue, double presentValue, double interestRate, String collateral)
    {
        Loan loan = new Loan(lender, lendee, loanID, initialValue, presentValue, interestRate, collateral);
        return loan;
    }
}
