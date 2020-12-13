import java.util.*;

public class LoanFactory 
{
    public LoanFactory() {}

    public Loan createNewLoan(Transferable lender, Transferable lendee, double initialValue, double interestRate, String collateral)
    {
        String ID = UUID.randomUUID().toString();
        Loan loan = new Loan(lender, lendee, ID, initialValue, interestRate, collateral);
        return loan;
    }

    public Loan createExistingLoan(Transferable lender, Transferable lendee, String loanID, double initialValue, double presentValue, double interestRate, String collateral)
    {
        Loan loan = new Loan(lender, lendee, loanID, initialValue, presentValue, interestRate, collateral);
        return loan;
    }
}
