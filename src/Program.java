import java.util.*;

public class Program 
{
    public static void run()
    {
        Bank bank = startup();

        // open the login form
        new LoginForm(bank);
    }

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
                            transaction = transactionFactory.getExistingWithdraw(Integer.parseInt(transactionInfo.get(3)), Double.parseDouble(transactionInfo.get(2)), accountID);
                            break;
                        case "DEPOSIT":
                            transaction = transactionFactory.getExistingDeposit(Integer.parseInt(transactionInfo.get(3)), Double.parseDouble(transactionInfo.get(2)), accountID);
                            break;
                        case "TRANSFER":
                            transaction = transactionFactory.getExistingTransfer(Integer.parseInt(transactionInfo.get(3)), Double.parseDouble(transactionInfo.get(2)), accountID, transactionInfo.get(4), transactionInfo.get(5));
                            break;
                    }

                    transactions.add(transaction);
                }
                transactionHistory.setTransactions(transactions);

                BankAccount account = null;
                if (accountCredential.get(3).equalsIgnoreCase("CHECKING"))
                {
                    account = bankAccountFactory.createExistingCheckingAccount(accountCredential.get(0), accountID, accountCredential.get(1), Double.parseDouble(accountCredential.get(2)), transactionHistory);
                }
                else if (accountCredential.get(3).equalsIgnoreCase("SAVINGS"))
                {
                    account = bankAccountFactory.createExistingSavingsAccount(accountCredential.get(0), accountID, accountCredential.get(1), Double.parseDouble(accountCredential.get(2)), transactionHistory);
                }

                accounts.add(account);
            }
            
            List<List<String>> loanCredentials = db.getLoans(ID);

            List<Loan> loans = new ArrayList<Loan>();
            for (List<String> loanCredential : loanCredentials)
            {
                Loan loan = loanFactory.createExistingLoan(bank, getAccountById(accounts, loanCredential.get(1)), loanCredential.get(2), Double.parseDouble(loanCredential.get(3)), Double.parseDouble(loanCredential.get(4)), Double.parseDouble(loanCredential.get(5)), loanCredential.get(6));
                loans.add(loan);
            }

            Customer customer = userFactory.createExistingCustomer(credentials.get(0), credentials.get(1), credentials.get(4), credentials.get(2), credentials.get(3), accounts, loans);
            customers.add(customer);
        }
        bank.setCustomers(customers);
        // initialize settings and reserves

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
