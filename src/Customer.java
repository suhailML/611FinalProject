import java.util.*;

public class Customer extends User
{
    private List<BankAccount> accounts;
    private List<Loan> loans;

    /*
    CONSTRUCTORS
    */
    public Customer(){}

    public Customer(String username, String password, String userID, String firstName, String lastName)
    {
        super(username, password, userID, firstName, lastName);
        setAccounts(new ArrayList<BankAccount>());
        setLoans(new ArrayList<Loan>());
    }

    public Customer(String username, String password, String userID, String firstName, String lastName, List<BankAccount> accounts, List<Loan> loans)
    {
        super(username, password, userID, firstName, lastName);
        setAccounts(accounts);
        setLoans(loans);
    }

    /*
    SETTERS
    */
    public void setAccounts(List<BankAccount> accounts)
    {
        this.accounts = accounts;
    }

    public void setLoans(List<Loan> loans)
    {
        this.loans = loans;
    }

    /*
    ACCESSORS
    */
    public List<BankAccount> getAccounts()
    {
        return accounts;
    }

    public List<Loan> getLoans()
    {
        return loans;
    }


    /** Sum up all of the accounts **/
    public double getTotalBalance(){
        double total = 0;
        for(BankAccount account : accounts){
            total += account.getBalance();
        }
        return total;
    }

    /** Return the total list of loans **/
    public double getTotalLoans(){
        double total = 0;
        for(Loan loan : loans){
            total += loan.getPresentValue();
        }
        return total;
    }

    /** Get the value of accounts - value of debts **/
    public double getNetWorth(){
        return getTotalBalance() - getTotalLoans();
    }

    public void addAccount(BankAccount account)
    {
        getAccounts().add(account);
    }

    public void deleteAccount(BankAccount account)
    {
        getAccounts().remove(account);
    }

    public String fullOutput(){
        String output = super.fullOutput();
        output += " - Balance: " + String.format("%.2f",getTotalBalance()) + "\n";
        output += " - Number loans: " + loans.size() + "\n";
        output += " - Loans debt: " + String.format("%.2f", getTotalLoans()) + "\n";
        output += " - Net worth:  " + String.format("%.2f",getNetWorth()) + "\n";

        return output;
    }

}
