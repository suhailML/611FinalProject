import java.util.*;

public class Program 
{
    public static void run()
    {
        Bank bank = startup();
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

        List<List<String>> customerCredentials = db.getAllCustomerCredentials();
        HashMap<String,ArrayList<String>> accountMap = db.getAllUsersAndAccounts();
        System.out.println(customerCredentials);
        for (List<String> credentials : customerCredentials)
        {
            String ID = credentials.get(4);

            ArrayList<String> accountIDs = accountMap.get(ID);
            List<BankAccount> accounts = new ArrayList<BankAccount>();
            for (String accountID : accountIDs)
            {
                List<String> accountCredential = db.getAccount(accountID);
                List<String> allTransactionInfo = db.getTransactionHistory(accountID);

                for (String transactionInfo : allTransactionInfo)
                {
                    
                }



                TransactionHistory transactionHistory = new TransactionHistory();
                if ()
                {
                    bankAccountFactory.createExistingCheckingAccount(name, accountID, currencyType, balance, transactions)
                }
                else if ()
                {
                    bankAccountFactory.createExistingSavingsAccount(name, accountID, currencyType, balance, transactions)
                }
            }
            // create all accounts
            


            ArrayList<List<String>> loanCredentials = db.getLoans(ID);

            List<Loan> loans = new ArrayList<Loan>();
            for (List<String> loanCredential : loanCredentials)
            {
                


                Loan loan = loanFactory.createExistingLoan(bank, lendee, loanID, initialValue, presentValue, interestRate, collateral)
            }




            //Customer customer = userFactory.createExistingCustomer(credentials.get(0), credentials.get(1), credentials.get(4), credentials.get(2), credentials.get(3));
            //employees.add(employee);
        }
        

        return bank;
    }

    public static void main(String[] args)
    {
        startup();
    }
}
