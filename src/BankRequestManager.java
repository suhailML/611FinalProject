/*
File: BankRequestManager.java
Developer: Tristan Marchand, Evan Boria
Email: tmarch@bu.edu
Last Edited: Wednesday, December 16, 2020

Description: Uses Singleton design pattern. Handles all actions requested by the GUI
*/

/*
Imported Libraries
*/
import java.util.*;

public class BankRequestManager implements GUIRequests
{
    private UserFactory userFactory;
    private BankAccountFactory bankAccountFactory;
    private LoanFactory loanFactory;
    private TransactionFactory transactionFactory;
    private static BankRequestManager singleInstance;

    /*
    CONSTRUCTORS (private)
    */
    private BankRequestManager()
    {
        userFactory = new UserFactory();
        bankAccountFactory = new BankAccountFactory();
        loanFactory = new LoanFactory();
        transactionFactory = new TransactionFactory();
    }

    /*
    Singleton getSingleInstance
    */
    public static BankRequestManager getSingleInstance()
    {
        if (singleInstance == null)
        {
            singleInstance = new BankRequestManager();
        }
        return singleInstance;
    }

    /*
    createCustomer - creates a new customer
    */
    public Customer createCustomer(Bank bank, String username, String password, String firstName, String lastName)
    {
        List<Customer> customers = bank.getCustomers();
        for (int i = 0; i < customers.size(); i++)
        {
            if (customers.get(i).getUsername().equals(username))
            {
                return null;
            }
        }

        Customer customer = userFactory.createNewCustomer(username, password, firstName, lastName);
        bank.addCustomer(customer);
        bank.getBankDB().addCredentials(customer.getUsername(), customer.getPassword(), customer.getFirstName(), customer.getLastName(), customer.getUserID(), "customer");

        return customer;
    }

    /*
    updateCustomer - updates the information of a customer
    */
    public boolean updateCustomer(Bank bank, Customer customer, String firstName, String lastName, String password)
    {
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPassword(password);
        bank.getBankDB().updateCredentials(customer.getUsername(), customer.getPassword(), customer.getFirstName(), customer.getLastName(), customer.getUserID(), "customer");
        return true;
    }

    /*
    createEmployee - creates a new employee
    */
    public boolean createEmployee(Bank bank, String username, String password, String firstName, String lastName)
    {
        boolean valid = false;

        List<Employee> employees = bank.getEmployees();
        for (int i = 0; i < employees.size(); i++)
        {
            if (employees.get(i).getUsername().equals(username))
            {
                return valid;
            }
        }
        valid = true;

        Employee employee = userFactory.createNewEmployee(username, password, firstName, lastName);
        bank.addEmployee(employee);
        bank.getBankDB().addCredentials(employee.getUsername(), employee.getPassword(), employee.getFirstName(), employee.getLastName(), employee.getUserID(), "employee");

        return valid;
    }

    /*
    checkCustomerLogin - checks the login credentials of a customer, and returns that object if the credentials are correct
    */
    public Customer checkCustomerLogin(Bank bank, String username, String password)
    {
        Customer customer = bank.getCustomer(username, password);
        return customer;
    }

    /*
    checkEmployeeLogin - checks the login credentials of an employee, and returns that object if the credentials are correct
    */
    public Employee checkEmployeeLogin(Bank bank, String username, String password)
    {
        Employee employee = bank.getEmployee(username, password);
        return employee;
    }

    /*
    createAccount - creates a new account for a customer
    */
    public BankAccount createAccount(Bank bank, Customer customer, String name, String currency, int accountType, double deposit)
    {
        BankAccount account;
        String accountTypeString;

        if(deposit < bank.getSettings().getTransactionFee()){
            System.out.println("Deposit is too low");
            return null;
        }


        switch(accountType)
        {
            case 0:
                account = bankAccountFactory.createNewCheckingAccount(name, currency);
                accountTypeString = "CHECKING";
                break;

            case 1:
                account = bankAccountFactory.createNewSavingsAccount(name, currency);
                accountTypeString = "SAVINGS";
                break;

            default:
                return null;
        }

        customer.addAccount(account);
        bank.getBankDB().addAccount(customer.getUserID(), account.getAccountID(), account.getName(), account.getCurrencyType(), Double.toString(account.getBalance()), accountTypeString);

        deposit(bank, account, deposit);

        return account;
    }

    /*
    saveBankSettings - updates the bank settings
    */
    public boolean saveBankSettings(Bank bank, double transactionFee, double savingsInterestRate, double loanInterestRate, double minSavingsForInterest)
    {
        BankSettings settings = bank.getSettings();
        settings.setTransactionFee(transactionFee);
        settings.setSavingsInterestRate(savingsInterestRate);
        settings.setLoanInterestRate(loanInterestRate);
        settings.setMinSavingsForInterest(minSavingsForInterest);
        System.out.println(bank.getReserves());
        System.out.println(String.format("%.2f", bank.getReserves()));
        bank.getBankDB().updateBankSettings("1", String.format("%.2f", settings.getTransactionFee()), Double.toString(settings.getSavingsInterestRate()), Double.toString(settings.getLoanInterestRate()), Double.toString(settings.getMinSavingsForInterest()), String.format("%.2f", bank.getReserves()), Integer.toString(settings.getDay()));;
        return true;
    }

    /*
    deleteAccount - deletes an account
    */
    public boolean deleteAccount(Bank bank, Customer customer, BankAccount account)
    {
        customer.deleteAccount(account);
        bank.getBankDB().deleteAccount(customer.getUserID(), account.getAccountID());

        return true;
    }

    /*
    withdraw - withdraws money from a bank account
    */
    public boolean withdraw(Bank bank, BankAccount account, double money)
    {
        boolean valid = false;
        BankSettings settings = bank.getSettings();
        if (money + settings.getTransactionFee() < account.getBalance())
        {
            account.send(money + settings.getTransactionFee());
            bank.addToReserves(settings.getTransactionFee());
            Transaction transaction = transactionFactory.getWithdraw(settings.getDay(), money, account);
            account.addTransaction(transaction);
            bank.getBankDB().addTransactionWDL("WITHDRAW", account.getAccountID(), Double.toString(transaction.getMoney()), Integer.toString(transaction.getDay()), "NA", "NA");  
            bank.getBankDB().updateBankSettings("1", Double.toString(settings.getTransactionFee()), Double.toString(settings.getSavingsInterestRate()), Double.toString(settings.getLoanInterestRate()), Double.toString(settings.getMinSavingsForInterest()), Double.toString(bank.getReserves()), Integer.toString(settings.getDay()));;

            bank.getBankDB().updateAccount(account.getAccountID(), account.getName(), account.getCurrencyType(), Double.toString(account.getBalance()));
            valid = true;
        }
        return valid;
    }

    /*
    deposit - deposits money to a bank account
    */
    public boolean deposit(Bank bank, BankAccount account, double money)
    {
        boolean valid = false;
        BankSettings settings = bank.getSettings();
        account.receive(money - bank.getSettings().getTransactionFee());

        bank.addToReserves(bank.getSettings().getTransactionFee());
        Transaction transaction = transactionFactory.getDeposit(bank.getSettings().getDay(), money, account);
        bank.getBankDB().addTransactionWDL("DEPOSIT", account.getAccountID(), Double.toString(transaction.getMoney()), Integer.toString(transaction.getDay()), "NA", "NA");  
        bank.getBankDB().updateBankSettings("1", Double.toString(settings.getTransactionFee()), Double.toString(settings.getSavingsInterestRate()), Double.toString(settings.getLoanInterestRate()), Double.toString(settings.getMinSavingsForInterest()), Double.toString(bank.getReserves()), Integer.toString(settings.getDay()));;


        bank.getBankDB().updateAccount(account.getAccountID(), account.getName(), account.getCurrencyType(), Double.toString(account.getBalance()));

        valid = true;
        return valid;
    }

    /*
    takeOutLoan - Take a loan from the lender to the lendee
    */
    public boolean takeOutLoan(Bank bank, Transferable lender, Transferable lendee, double money, String collateral)
    {
        Loan loan = loanFactory.createNewLoan(bank, lendee, money, bank.getSettings().getLoanInterestRate(), collateral);

        // if the loan transfer is successful from lender to lendee, add it to the database
        if(transfer(bank, lender, lendee, money))
        {
            bank.getBankDB().addLoan(((BankAccount) loan.getLendee()).getAccountID(), loan.getLender().getName(), loan.getLendee().getName(), loan.getLoanID(), Double.toString(loan.getInitialValue()), Double.toString(loan.getPresentValue()), Double.toString(loan.getInterestRate()), loan.getCollateral());
            return true;
        }

        return false;
    }


    /*
    payBackLoan - Payback part of a loan from the lendee to the lender
    */
    public boolean payBackLoan(Bank bank, Transferable lendee, Transferable lender, double money, Loan loan)
    {
        if(loan.getPresentValue() < money)
        {
            money = loan.getPresentValue();
        }

        // if the transfer of money is successful from lendee to lender
        if (transfer(bank, lendee, lender, money))
        {
            // remove money from the loan
            loan.payBack(money);

            if(loan.getPresentValue() <= 0){
                bank.getBankDB().deleteLoan(loan.getLoanID(), ((BankAccount) loan.getLendee()).getAccountID());
            }
            else {
                bank.getBankDB().updateLoan(((BankAccount) loan.getLendee()).getAccountID(), loan.getLender().getName(), loan.getLendee().getName(), loan.getLoanID(), Double.toString(loan.getInitialValue()), Double.toString(loan.getPresentValue()), Double.toString(loan.getInterestRate()), loan.getCollateral());
            }
            return true;
        }

        return false;
    }

    /*
    transfer - transfers funds from one Transferable object to another
    */
    public boolean transfer(Bank bank, Transferable sender, Transferable receiver, double money)
    {
        BankSettings settings = bank.getSettings();
        double fee = settings.getTransactionFee();

        if(sender.send(money + fee)) {
            money -= settings.getTransactionFee();
            bank.addToReserves(settings.getTransactionFee());

            // if the receiver was able to receive the money, update the database
            if(receiver.receive(money)) {

                if (sender instanceof BankAccount)
                {
                    Transaction transaction = transactionFactory.getTransfer(bank.getSettings().getDay(), money, (BankAccount) sender, sender, receiver);
                    BankAccount senderAccount = ((BankAccount) sender);
                    senderAccount.addTransaction(transaction);
                    bank.getBankDB().addTransactionWDL("TRANSFER", senderAccount.getAccountID(), Double.toString(transaction.getMoney()), Integer.toString(transaction.getDay()), sender.getName(), receiver.getName());
                    bank.getBankDB().updateAccount(senderAccount.getAccountID(), senderAccount.getName(), senderAccount.getCurrencyType(), Double.toString(senderAccount.getBalance()));
                }
                if (receiver instanceof BankAccount)
                {
                    Transaction transaction = transactionFactory.getTransfer(bank.getSettings().getDay(), money, (BankAccount) receiver, sender, receiver);
                    BankAccount receiverAccount = ((BankAccount) receiver);
                    receiverAccount.addTransaction(transaction);
                    bank.getBankDB().addTransactionWDL("TRANSFER", receiverAccount.getAccountID(), Double.toString(transaction.getMoney()), Integer.toString(transaction.getDay()), sender.getName(), receiver.getName());
                    bank.getBankDB().updateAccount(receiverAccount.getAccountID(), receiverAccount.getName(), receiverAccount.getCurrencyType(), Double.toString(receiverAccount.getBalance()));
                }
                bank.getBankDB().updateBankSettings("1", String.format("%.2f", settings.getTransactionFee()), Double.toString(settings.getSavingsInterestRate()), Double.toString(settings.getLoanInterestRate()), String.format("%.2f", settings.getMinSavingsForInterest()), String.format("%.2f", bank.getReserves()), Integer.toString(settings.getDay()));;

                return true;
            }
            else{
                System.out.println("Transaction failed from receiver - return all money to sender");
                sender.receive(money + fee);
                return false;
            }
        }
        else{
            System.out.println("Transaction failed from sender - insufficient funds");
            return false;
        }
    }

    /*
    incrementDay - moves the day forward, and updates all loans and savings accounts
    */
    public boolean incrementDay(Bank bank)
    {
        BankSettings settings = bank.getSettings();
        settings.incrementDay();
        bank.getBankDB().updateBankSettings("1", Double.toString(settings.getTransactionFee()), Double.toString(settings.getSavingsInterestRate()), Double.toString(settings.getLoanInterestRate()), Double.toString(settings.getMinSavingsForInterest()), Double.toString(bank.getReserves()), Integer.toString(settings.getDay()));;
        
        List<Customer> customers = bank.getCustomers();

        for (Customer customer : customers)
        {
            List<Loan> loans = customer.getLoans();
            for (Loan loan : loans)
            {
                loan.compoundInterest();
                bank.getBankDB().updateLoan(((BankAccount) loan.getLendee()).getAccountID(), loan.getLender().getName(), loan.getLendee().getName(), loan.getLoanID(), Double.toString(loan.getInitialValue()), Double.toString(loan.getPresentValue()), Double.toString(loan.getInterestRate()), loan.getCollateral());
            }

            List<BankAccount> accounts = customer.getAccounts();
            for (BankAccount account : accounts)
            {
                if (account instanceof SavingsAccount && account.getBalance() > bank.getSettings().getMinSavingsForInterest())
                {
                    SavingsAccount temp = (SavingsAccount) account;
                    transfer(bank, bank, temp, bank.getSettings().getSavingsInterestRate() * temp.getBalance());
                }
            }
        }

        return true;
    }

    /*
    queryTransactions - returns all transactions for the given day
    */
    public String queryTransactions(Bank bank, int day)
    {
        StringBuilder string = new StringBuilder();

        List<Customer> customers = bank.getCustomers();

        for (Customer customer : customers)
        {
            List<BankAccount> accounts = customer.getAccounts();
            for (BankAccount account: accounts)
            {
                List<Transaction> history = account.getTransactionHistory().getTransactions();
                for (Transaction transaction : history)
                {
                    if (transaction.getDay() == day)
                    {
                        string.append(transaction.toString() + "\n");
                    }
                }
            }
        }

        return string.toString();
    }

}