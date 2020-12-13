import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BankDatabase  {
    private String bankDB = "../src/";
    private String credentials = bankDB + "credentials/";
    private String employeeCredentials = credentials + "employeecredentials.txt";
    private String customerCredentials = credentials + "customercredentials.txt";
    private String loans = bankDB + "loans/";
    private String transactions = bankDB + "transactions/";

    public ArrayList<List<String>> getLoans(String userID)
    {
        ArrayList<List<String>> parsedLoans = ParseFile.parseRows(loans + userID + ".txt");
        ArrayList<List<String>> returnList  = new ArrayList<List<String>>();
        for (List<String> loanInfo: parsedLoans)
        {
            returnList.add(Arrays.asList(loanInfo.get(0).split(",")));
        }
        return returnList;
    }

    public ArrayList<String> getCredentials(String userID, String userType)
    {
        ArrayList<List<String>> parsedUsers = new ArrayList<List<String>>();
        if (userType.equals("customer"))
        {
            parsedUsers = ParseFile.parseRows(customerCredentials);
        }
        else if (userType.equals("employee"))
        {
            parsedUsers = ParseFile.parseRows(employeeCredentials);
        }
        for (List<String> user: parsedUsers)
        {
            if (user.get(4).equals(userID))
            {
                return (ArrayList<String>) user;
            }
        }
        return new ArrayList<String>();
    }

    public ArrayList<String> getTransactionHistory(String accoundID)
    {
        ArrayList<List<String>> parsedTransactions = ParseFile.parseRows(transactions + accoundID + ".txt");
        ArrayList<String> transactionHistory = new ArrayList<String>();
        for (List<String> transaction: parsedTransactions)
        {
            transactionHistory.add(transaction.get(0));
        }
        return transactionHistory;
    }

    public void addTransaction(String accountID, String transaction)
    {
        ParseFile.addLine(transactions + accountID + ".txt", transaction);
    }

    public void addCredentials(String userName, String password, String firstName, String lastName, String userId, String userType)
    {
        if (userType.equals("customer"))
        {
            ParseFile.addLine(customerCredentials, userName + "\t" + password + "\t" + firstName + "\t" + lastName + "\t" + userId);
        }
        else if (userType.equals("employee"))
        {
            ParseFile.addLine(employeeCredentials, userName + "\t" + password + "\t" + firstName + "\t" + lastName + "\t" + userId);
        }
    }

    public void addLoan(String accountID, String lender, String lendeeID, String loanID, String initialValue, String presentValue, String interestRate, String collateral)
    {
        ParseFile.addLine(loans + accountID + ".txt", lender + "," + lendeeID + "," + loanID + "," + initialValue + "," + presentValue + "," + interestRate + "," + collateral);
    }
}
