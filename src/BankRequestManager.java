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

    /*
    public boolean createAccount()
    {
        bank.getDB().newAccount(account);
    }

    public boolean editAccount()
    {
        bank.getDB().updateAccount(account);
    }
    

    public boolean loginEmployee( user)
    {

    }

    public boolean loginCustomer()
    {

    }
    */

    public boolean withdraw(Bank bank, BankAccount account, double money)
    {
        boolean valid = false;
        if (money + bank.getSettings().getTransactionFee() < account.getBalance())
        {
            account.withdraw(money + bank.getSettings().getTransactionFee());
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
        account.deposit(money - bank.getSettings().getTransactionFee());
        bank.addToReserves(bank.getSettings().getTransactionFee());
        Transaction transaction = transactionFactory.getDeposit(bank.getSettings().getDay(), money, account);
        bank.getBankDB().addTransaction(transaction);
        valid = true;
        return valid;
    }

    public boolean takeOutLoan(Bank bank, Customer customer, BankAccount account, double money, String collateral)
    {
        boolean valid = false;
        Loan loan = loanFactory.createNewLoan(bank, customer, money, bank.getSettings().getLoanInterestRate(), collateral);
        transfer(bank, (Transferable) bank, (Transferable) account, money);
        bank.getBankDB().addLoan(loan);
        valid = true;
        return valid;
    }

    public boolean payBackLoan(Bank bank, Customer customer, BankAccount account, double money, Loan loan)
    {
        boolean valid = false;
        if (money + bank.getSettings().getTransactionFee() < account.getBalance() && loan.getPresentValue() + bank.getSettings().getTransactionFee() >= money)
        {
            transfer(bank, (Transferable) account, (Transferable) bank, money);
            loan.payBack(money);
            bank.getBankDB.updateLoan(loan);
        }
        
    }

    public boolean transfer(Bank bank, Transferable sender, Transferable receiver, double money)
    {
        Transaction transaction = transactionFactory.getTransfer(bank.getSettings().getDay(), money, sender, receiver);
        sender.send(money);
        money -= bank.getSettings().getTransactionFee();
        bank.addToReserves(bank.getSettings().getTransactionFee());
        receiver.receive(money);

        sender.addTransaction(transaction);
        receiver.addTransaction(transaction);
        bank.getBankDB().addTransaction(transaction);
    }
}