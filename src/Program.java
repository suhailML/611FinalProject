/*
File: Program.java
Developer: Tristan Marchand, Evan Boria
Email: tmarch@bu.edu
Last Edited: Wednesday, December 16, 2020

Description: Uses Factory design pattern. Creates loan objects
*/

/*
Imported Libraries
*/
import java.util.*;

public abstract class Program 
{
    /*
    run - static method to run the atm program
    */
    public static void run()
    {
        Bank bank = startup();

        // open the login form
        new LoginForm(bank);
    }

    /*
    startup - creates all objects from the database to run the program
    */
    private static Bank startup()
    {
        UserFactory userFactory = new UserFactory();
        BankAccountFactory bankAccountFactory = new BankAccountFactory();
        LoanFactory loanFactory = new LoanFactory();
        TransactionFactory transactionFactory = new TransactionFactory();

        Bank bank = new Bank();
        //bank.getBankDB().getBankSettings();
        BankDatabase db = bank.getBankDB();
        
        List<List<String>> employeeCredentials = db.getAllEmployeeCredentials();
        List<Employee> employees = new ArrayList<Employee>();
        for (List<String> credentials : employeeCredentials)
        {
            Employee employee = userFactory.createExistingEmployee(credentials.get(0), credentials.get(1), credentials.get(4), credentials.get(2), credentials.get(3));
            employees.add(employee);
        }
        bank.setEmployees(employees);
        System.out.println(employees);

        List<List<String>> customerCredentials = db.getAllCustomerCredentials();
        Map<String,List<String>> accountMap = db.getAllUsersAndAccounts();
        List<Customer> customers = new ArrayList<Customer>();
        for (List<String> credentials : customerCredentials)
        {
            String ID = credentials.get(4);

            List<String> accountIDs = accountMap.get(ID);

            List<BankAccount> accounts = new ArrayList<BankAccount>();

            List<Loan> loans = new ArrayList<Loan>();

            for (String accountID : accountIDs)
            {
                List<String> accountCredential = db.getAccountInfo(accountID);
                List<List<String>> allTransactionInfo = db.getTransactionHistory(accountID);

                TransactionHistory transactionHistory = new TransactionHistory();
                List<Transaction> transactions = new ArrayList<Transaction>();
                for (List<String> transactionInfo : allTransactionInfo)
                {
                    Transaction transaction = null;
                    switch(transactionInfo.get(0).toUpperCase())
                    {
                        case "WITHDRAW":
                            transaction = transactionFactory.getExistingWithdraw(Integer.parseInt(transactionInfo.get(2)), Double.parseDouble(transactionInfo.get(1)), accountID);
                            break;
                        case "DEPOSIT":
                            transaction = transactionFactory.getExistingDeposit(Integer.parseInt(transactionInfo.get(2)), Double.parseDouble(transactionInfo.get(1)), accountID);
                            break;
                        case "TRANSFER":
                            transaction = transactionFactory.getExistingTransfer(Integer.parseInt(transactionInfo.get(2)), Double.parseDouble(transactionInfo.get(1)), accountID, transactionInfo.get(3), transactionInfo.get(4));
                            break;
                    }
                    transactions.add(transaction);
                }
                transactionHistory.setTransactions(transactions);

                BankAccount account = null;
                System.out.println("\n" + accountID);
                System.out.println(accountCredential);
                if (accountCredential.get(4).equalsIgnoreCase("CHECKING"))
                {
                    //accountID/name/currencyType/money/Type
                    account = bankAccountFactory.createExistingCheckingAccount(accountCredential.get(1), accountCredential.get(0), accountCredential.get(2), Double.parseDouble(accountCredential.get(3)), transactionHistory);
                }
                else if (accountCredential.get(4).equalsIgnoreCase("SAVINGS"))
                {
                    account = bankAccountFactory.createExistingSavingsAccount(accountCredential.get(1), accountCredential.get(0), accountCredential.get(2), Double.parseDouble(accountCredential.get(3)), transactionHistory);
                }

                accounts.add(account);

                //ADD LOANS
                List<List<String>> loanCredentials = db.getLoans(accountID);

                for (List<String> loanCredential : loanCredentials)
                {
                    System.out.println("LOAN " + loanCredential);
                    Loan loan = loanFactory.createExistingLoan(bank, account, loanCredential.get(2), Double.parseDouble(loanCredential.get(3)), Double.parseDouble(loanCredential.get(4)), Double.parseDouble(loanCredential.get(5)), loanCredential.get(6));
                    loans.add(loan);
                }
            }
            Customer customer = userFactory.createExistingCustomer(credentials.get(0), credentials.get(1), credentials.get(4), credentials.get(2), credentials.get(3), accounts, loans);
            customers.add(customer);
        }
        bank.setCustomers(customers);


        // initialize settings and reserves
        List<String> rawBankSettings =  db.getBankSettings("1");

        bank.setSettings(new BankSettings(
                            Double.parseDouble(rawBankSettings.get(1)),
                            Double.parseDouble(rawBankSettings.get(2)),
                            Double.parseDouble(rawBankSettings.get(3)),
                            Double.parseDouble(rawBankSettings.get(4)),
                            Integer.parseInt(rawBankSettings.get(6)))
                        );

        bank.setReserves(Double.parseDouble(rawBankSettings.get(5)));

        System.out.println(bank.getSettings());
        return bank; 
    }

    private static BankAccount getAccountById(List<BankAccount> accounts, String accountID)
    {
        for (BankAccount account : accounts)
        {
            if (accountID.equals(account.getAccountID()))
            {
                return account;
            }
        }

        return null;
    }
}
