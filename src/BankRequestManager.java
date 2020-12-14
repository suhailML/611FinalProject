import java.util.*;

public class BankRequestManager implements GUIRequests
{
    private UserFactory userFactory;
    private BankAccountFactory bankAccountFactory;
    private LoanFactory loanFactory;
    private TransactionFactory transactionFactory;
    private static BankRequestManager singleInstance;

    private BankRequestManager()
    {
        userFactory = new UserFactory();
        bankAccountFactory = new BankAccountFactory();
        loanFactory = new LoanFactory();
        transactionFactory = new TransactionFactory();
    }

    public static BankRequestManager getSingleInstance()
    {
        if (singleInstance == null)
        {
            singleInstance = new BankRequestManager();
        }
        return singleInstance;
    }

    public boolean createCustomer(Bank bank, String username, String password, String firstName, String lastName)
    {
        boolean valid = false;

        ArrayList<Customer> customers = bank.getCustomers();
        for (int i = 0; i < 0; i++)
        {
            if (customers.get(i).getUsername().equals(username))
            {
                return valid;
            }
        }
        valid = true;

        Customer customer = userFactory.createNewCustomer(username, password, firstName, lastName);
        bank.addCustomer(customer);
        bank.getBankDB().addCustomer(customer);

        return valid;
    }


    public boolean createEmployee(Bank bank, String username, String password, String firstName, String lastName)
    {
        boolean valid = false;

        ArrayList<Employee> employees = bank.getEmployees();
        for (int i = 0; i < 0; i++)
        {
            if (employees.get(i).getUsername().equals(username))
            {
                return valid;
            }
        }
        valid = true;

        Employee employee = userFactory.createNewEmployee(username, password, firstName, lastName);
        bank.addEmployee(employee);
        bank.getBankDB().addEmployee(employee);

        return valid;
    }

    public Customer checkCustomerLogin(Bank bank, String username, String password)
    {
        Customer customer = bank.getCustomer(username, password);
        return customer;
    }

    public Employee checkEmployeeLogin(Bank bank, String username, String password)
    {
        Employee employee = bank.getEmployee(username, password);
        return employee;
    }

    public boolean createAccount(Bank bank, Customer customer, String name, String currency, int accountType)
    {
        BankAccount account;
        switch(accountType)
        {
            case 0: 
            account = bankAccountFactory.createNewCheckingAccount(name, currency);
            break;

            case 1: 
            account = bankAccountFactory.createNewSavingsAccount(name, currency);
            break;

            default:
                return false;
        }

        customer.addAccount(account);
        bank.getBankDB().addAccount(account);

        return true;
    }

    public boolean deleteAccount(Bank bank, Customer customer, BankAccount account)
    {
        customer.deleteAccount(account);
        bank.getBankDB().deleteAccount(account);

        return true;
    }

    public boolean withdraw(Bank bank, BankAccount account, double money)
    {
        boolean valid = false;
        if (money + bank.getSettings().getTransactionFee() < account.getBalance())
        {
            account.send(money + bank.getSettings().getTransactionFee());
            //Transaction transaction = transactionFactory.getWithdraw(day, money, account);
            // add to transactions of account
            // Bank.getBankDB().addTransaction(transaction)

            bank.addToReserves(bank.getSettings().getTransactionFee());
            Transaction transaction = transactionFactory.getWithdraw(bank.getSettings().getDay(), money, account);
            account.addTransaction(transaction);
            bank.getBankDB().addTransaction(transaction);
            valid = true;
        }
        return valid;
    }

    public boolean deposit(Bank bank, BankAccount account, double money)
    {
        boolean valid = false;
        account.receive(money - bank.getSettings().getTransactionFee());

        bank.addToReserves(bank.getSettings().getTransactionFee());
        Transaction transaction = transactionFactory.getDeposit(bank.getSettings().getDay(), money, account);
        bank.getBankDB().addTransaction(transaction);
        valid = true;
        return valid;
    }


    /** Take a loan from the lender to the lendee **/
    public boolean takeOutLoan(Bank bank, Transferable lender, Transferable lendee, double money, String collateral)
    {

        Loan loan = loanFactory.createNewLoan(bank, lendee, money, bank.getSettings().getLoanInterestRate(), collateral);

        // if the loan transfer is successful from lender to lendee, add it to the database
        if(transfer(bank, lender, lendee, money)){

            bank.getBankDB().addLoan(loan);

            return true;
        }

        return false;
    }


    /** Payback part of a loan from the lendee to the lender **/
    public boolean payBackLoan(Bank bank, Transferable lendee, Transferable lender, double money, Loan loan)
    {
        if(loan.getPresentValue() < money){
            money = loan.getPresentValue();
        }

        // if the transfer of money is successful from lendee to lender
        if (transfer(bank, lendee, lender, money))
        {
            // remove money from the loan
            loan.payBack(money);

            bank.getBankDB().updateLoan(loan);

            return true;
        }

        return false;
    }

    public boolean transfer(Bank bank, BankAccount account, Transferable sender, Transferable receiver, double money)
    {
        Transaction transaction = transactionFactory.getTransfer(bank.getSettings().getDay(), money, account, sender, receiver);

        double fee = bank.getSettings().getTransactionFee();

        if(sender.send(money + fee)) {
            money -= bank.getSettings().getTransactionFee();
            bank.addToReserves(bank.getSettings().getTransactionFee());

            // if the receiver was able to receive the money, update the database
            if(receiver.receive(money)) {
                sender.addTransaction(transaction);
                receiver.addTransaction(transaction);
                bank.getBankDB().addTransaction(transaction);

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


    public String queryTransactions(int day){

        String output = "TODO - fill in query transactions --> DAY: " + day;

        return output;

    }
}