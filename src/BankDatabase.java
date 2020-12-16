/*
File: BankDatabase.java
Developer: Suhail Singh, Evan Bosia (bugsfixes)
Email: suhails@bu.edu
Last Edited: Wednesday, December 16, 2020

Description: This class serves to connect the codebase to our database. The codebase can use this class to make calls to the database to add new data and update old data. 
*/

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BankDatabase  {

    //LINUX File Paths
    private String bankDB = "../src/";
    private String credentials = bankDB + "credentials/";
    private String employeeCredentials = credentials + "employeecredentials.txt";
    private String customerCredentials = credentials + "customercredentials.txt";
    private String loans = bankDB + "loans/";
    private String transactions = bankDB + "transactions/";
    private String userIdToAccountIDs = bankDB + "userIdToAccountIDs/";
    private String accountIDsInfo = bankDB + "accountIDsInfo/";
    private String accountIDsInfoFile = accountIDsInfo + "accountIds.txt";
    private String bankSettings = bankDB + "banksettings/";
    private String bankSettingsFile = bankSettings + "banksettings.txt";

    private static BankDatabase singleInstance;

    private BankDatabase(){

        // Comment this out to use the linux values
        System.out.println(System.getProperty("os.name"));

        if(System.getProperty("os.name").startsWith("Windows")){
            setupWindowsLogin();
        }
    }

    /** Set up the paths for a windows login using current directory as the start of the path **/
    private void setupWindowsLogin(){
        String start = System.getProperty("user.dir");
        bankDB = start + "\\src\\";
        credentials = bankDB + "credentials\\";
        employeeCredentials = credentials + "employeecredentials.txt";
        customerCredentials = credentials + "customercredentials.txt";
        loans = bankDB + "loans\\";
        transactions = bankDB + "transactions\\";
        userIdToAccountIDs = bankDB + "userIdToAccountIDs\\";
        accountIDsInfo = bankDB + "accountIDsInfo\\";
        accountIDsInfoFile = accountIDsInfo + "accountIds.txt";
        bankSettings = bankDB + "banksettings\\";
        bankSettingsFile = bankSettings + "banksettings.txt";
    }

    /**
     * 
     * @return: A single instance of the database for the bank to hold. 
     */
    public static BankDatabase getSingleInstance()
    {
        if (singleInstance == null)
        {
            singleInstance = new BankDatabase();
        }
        return singleInstance;
    }
    
    /**
     * 
     * @param userID: Unique user identifier
     * @return: A list that holds all of the loan information for an individual user. 
     */
    public List<List<String>> getLoans(String userID)
    {
        List<List<String>> parsedLoans = ParseFile.parseRows(loans + userID + ".txt");
        List<List<String>> returnList  = new ArrayList<List<String>>();
        for (List<String> loanInfo: parsedLoans)
        {
            returnList.add(Arrays.asList(loanInfo.get(0).split(",")));
        }
        return returnList;
    }

    /**
     * 
     * @param userID: Unique user identifier
     * @param userType: Either Customer or Employee
     * @return: Returns a list of data for the user once they login to the program. 
     */
    public List<String> getCredentials(String userID, String userType)
    {
        List<List<String>> parsedUsers = new ArrayList<List<String>>();
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

    /**
     * 
     * @param accoundID: Unique account identifier
     * @return: Returns a list containing each row of the transaction history for a specific account in a printable format. 
     */
    public List<List<String>> getTransactionHistory(String accoundID)
    {
        return ParseFile.parseRows(transactions + accoundID + ".txt");
    }
 
    /**
     * Makes a call to the database to add a transaction with inputted details to an accounts transaction history.
     */
    public void addTransactionWDL(String transactionType, String accountID, String money, String day, String sender, String receiver)
    {
        ParseFile.addLine(transactions + accountID + ".txt", transactionType + "\t" + money + "\t" + day + "\t" + sender + "\t" + receiver);
    }

    /**
     * Makes a call to the database to add new credentials with inputted details to the specified user credentials txt file.
     */
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

    /**
     * Makes a call to the database to update old credentials with inputted details to the specified user credentials txt file.
     */
    public void updateCredentials(String userName, String password, String firstName, String lastName, String userId, String userType)
    {    
        if (userType.equals("customer"))
        {
            ParseFile.deleteLine(customerCredentials, userId);
            ParseFile.addLine(customerCredentials, userName + "\t" + password + "\t" + firstName + "\t" + lastName + "\t" + userId);
        }
        else if (userType.equals("employee"))
        {
            ParseFile.deleteLine(employeeCredentials, userId);
            ParseFile.addLine(employeeCredentials, userName + "\t" + password + "\t" + firstName + "\t" + lastName + "\t" + userId);
        }
    }

    /**
     * Makes a call to the database to add a new loan with inputted details to the specified file for tracking loans for each account.
     */
    public void addLoan(String accountID, String lender, String lendeeID, String loanID, String initialValue, String presentValue, String interestRate, String collateral)
    {
        ParseFile.addLine(loans + accountID + ".txt", lender + "," + lendeeID + "," + loanID + "," + initialValue + "," + presentValue + "," + interestRate + "," + collateral);
    }

    /**
     * Makes a call to the database to update a currently stored loan with inputted details to the specified file for tracking loans for each account.
     */
    public void updateLoan(String accountID, String lender, String lendeeID, String loanID, String initialValue, String presentValue, String interestRate, String collateral)
    {
        ParseFile.deleteLine(loans + accountID + ".txt", loanID);
        ParseFile.addLine(loans + accountID + ".txt", lender + "," + lendeeID + "," + loanID + "," + initialValue + "," + presentValue + "," + interestRate + "," + collateral);
    }

    /**
     * Makes a call to the database to delete a currently stored loan in the specified file for tracking loans for each account.
     */
    public void deleteLoan(String loanID, String accountID)
    {
        ParseFile.deleteLine(loans + accountID + ".txt", loanID);
    }

    /**
     * Makes a call to the database to delete an account throughout the entirety of the database.
     */
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

    /**
     * Makes a call to the database to add an new account by mapping it to a userID and storing its details.
     */
    public void addAccount(String userID, String accountID, String name, String currencyType, String balance, String accountType)
    {

        ParseFile.addLine(userIdToAccountIDs + userID + ".txt", accountID);
        ParseFile.addLine(accountIDsInfoFile, accountID + "\t" + name + "\t" + currencyType + "\t" + balance + "\t" + accountType);
    }

    /**
     * Makes a call to the database to update a currently stored account with inputted details to the specified file for storing account info.
     */
    public void updateAccount(String accountID, String name, String currencyType, String balance)
    {
        List<String> row = getAccountInfo(accountID);
        System.out.println(row);
        ParseFile.deleteLine(accountIDsInfoFile, accountID);
        ParseFile.addLine(accountIDsInfoFile, accountID + "\t" + name + "\t" + currencyType + "\t" + balance + "\t" + row.get(4));
    }

    /**
     * Makes a call to the database to add the inputted details of the settings of the bank to the specified file meant for doing so.
     */
    public void addBankSettings(String bankSettingsID, String transactionFee, String savingsInterestRate, String loanInterestRate, String minSavingsForInterest, String reserves, String day)
    {
        ParseFile.addLine(bankSettingsFile, bankSettingsID + "\t" + transactionFee + "\t" + savingsInterestRate + "\t" + loanInterestRate + "\t" + minSavingsForInterest + "\t" + reserves + "\t" + day);
    }

    public void updateBankSettings(String bankSettingsID, String transactionFee, String savingsInterestRate, String loanInterestRate, String minSavingsForInterest, String reserves, String day)
    {
        ParseFile.deleteLine(bankSettingsFile, bankSettingsID);
        ParseFile.addLine(bankSettingsFile, bankSettingsID + "\t" + transactionFee + "\t" + savingsInterestRate + "\t" + loanInterestRate + "\t" + minSavingsForInterest + "\t" + reserves + "\t" + day);

    }

    /**
     * Makes a call to the database to retrieve the inputted details of the settings of the bank from the specified file meant for doing so.
     */
    public List<String> getBankSettings(String bankSettingsID)
    {
        List<List<String>> parsedRows = ParseFile.parseRows(bankSettingsFile);
        for (List<String> row: parsedRows)
        {
            if (row.get(0).equals(bankSettingsID))
            {
                return row;
            }
        }
        return new ArrayList<String>();
    }

    /**
     * Makes a call to the database to retrieve the account info tied to the specified accountID.
     */
    public List<String> getAccountInfo(String accountID)
    {
        List<List<String>> accountInfos = ParseFile.parseRows(accountIDsInfoFile);
        for (List<String> info: accountInfos)
        {
            if (info.get(0).equals(accountID))
            {
                return info;
            }
        }
        return new ArrayList<String>();
    }

    /**
     * Makes a call to the datbase to retrieve all of the users mapped to an array of their associated accounts. 
     */
    public HashMap<String,List<String>> getAllUsersAndAccounts()
    {
        HashMap<String,List<String>> ret = new HashMap<String,List<String>>();
        File dir = new File(userIdToAccountIDs);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File userID : directoryListing) {
                String key = userID.getName().substring(0, userID.getName().length()-4);
                List<String> value = new ArrayList<String>();
                List<List<String>> accountIds = ParseFile.parseRows(userID.getPath());
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

    /**
     * Makes a call to the datbase to retrieve all of the customer credendtials in a readable format. 
     */
    public List<List<String>> getAllCustomerCredentials()
    {
        return ParseFile.parseRows(customerCredentials);
    }   
    
    /**
     * Makes a call to the datbase to retrieve all of the employee credendtials in a readable format. 
     */
    public List<List<String>> getAllEmployeeCredentials()
    {
        return ParseFile.parseRows(employeeCredentials);
    }
}


