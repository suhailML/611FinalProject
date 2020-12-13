import java.util.*;

public class Customer extends User implements CustomerActions
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

    public Customer(String username, String password, String userID, String firstName, String lastName, ArrayList<BankAccount> accounts, ArrayList<Loan> loans)
    {
        super(username, password, userID, firstName, lastName);
        setAccounts(accounts);
        setLoans(loans);
    }

    /*
    SETTERS
    */
    public void setAccounts(ArrayList<BankAccount> accounts)
    {
        this.accounts = accounts;
    }

    public void setLoans(ArrayList<Loan> loans)
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
    //TODO should this include loans?
    public double getNetWorth(){
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

    public void addAccount(BankAccount account)
    {
        getAccounts().add(account);
    }

    public void deleteAccount(BankAccount account)
    {
        getAccounts().remove(account);
    }

}
