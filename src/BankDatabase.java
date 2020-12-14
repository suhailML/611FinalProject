import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BankDatabase  {
    private String bankDB = "../src/";
    private String credentials = bankDB + "credentials/";
    private String employeeCredentials = credentials + "employeecredentials.txt";
    private String customerCredentials = credentials + "customercredentials.txt";
    private String loans = bankDB + "loans/";
    private String transactions = bankDB + "transactions/";
    private String userIdToAccountIDs = bankDB + "userIdToAccountIDs/";
    private String accountIDsInfo = bankDB + "accountIDsInfo/";
    private String accountIDsInfoFile = bankDB + accountIDsInfo + "accountIds.txt";

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

    public void updateLoan(String accountID, String lender, String lendeeID, String loanID, String initialValue, String presentValue, String interestRate, String collateral)
    {
        ParseFile.deleteLine(loans + accountID + ".txt", loanID);
        ParseFile.addLine(loans + accountID + ".txt", lender + "," + lendeeID + "," + loanID + "," + initialValue + "," + presentValue + "," + interestRate + "," + collateral);
    }

    public void deleteAccount(String userID, String accountID)
    {
        File file = new File(transactions + accountID + ".txt");
        if (file.delete()) { 
            System.out.println("Deleted the file: " + file.getName());
          } else {
            System.out.println("Failed to delete the file.");
          } 
        file = new File(loans + accountID + ".txt");
        if (file.delete()) { 
            System.out.println("Deleted the file: " + file.getName());
          } else {
            System.out.println("Failed to delete the file.");
          } 
        ParseFile.deleteLine(userIdToAccountIDs + userID + ".txt", accountID);
        ParseFile.deleteLine(accountIDsInfoFile, accountID);
    }

    public void addAccount(String userID, String accountID, String name, String currencyType, String balance)
    {
        ParseFile.addLine(userIdToAccountIDs + userID + ".txt", accountID);
        ParseFile.addLine(accountIDsInfoFile, accountID + "\t" + name + "\t" + currencyType + "\t" + balance);
    }

    public HashMap<String,ArrayList<String>> getAllUsersAndAccounts()
    {
        HashMap<String,ArrayList<String>> ret = new HashMap<String,ArrayList<String>>();
        File dir = new File(userIdToAccountIDs);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File userID : directoryListing) {
                String key = userID.getName().substring(0, userID.getName().length()-3);
                ArrayList<String> value = new ArrayList<String>();
                ArrayList<List<String>> accountIds = ParseFile.parseRows(userID.getPath());
                for (List<String> s: accountIds)
                {
                    value.add(s.get(0));
                }
                ret.put(key, value);
            }
            return ret;
        } else {
            return null;
        }
    }

    public ArrayList<List<String>> getAllCustomerCredentials()
    {
        return ParseFile.parseRows(customerCredentials);
    }   
    
    public ArrayList<List<String>> getAllEmployeeCredentials()
    {
        return ParseFile.parseRows(employeeCredentials);
    }  

}
