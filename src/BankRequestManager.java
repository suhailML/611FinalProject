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

    public boolean withdraw(Bank bank, Customer customer, BankAccount account, double money, int day)
    {
        boolean valid = false;
        if (money + bank.getSettings().getTransactionFee() < account.getBalance())
        {
            account.withdraw(money + bank.getSettings().getTransactionFee());
            Transaction transaction = transactionFactory.getWithdraw(day, money, account);
            // add to transactions of account
            // Bank.getBankDB().addTransaction(transaction)

            valid = true;
        }
        return valid;
    }

    public boolean deposit(Bank bank, BankAccount account, double money, int day)
    {
        boolean valid = false;
        account.deposit(money - bank.getSettings().getTransactionFee());
        Transaction transaction = transactionFactory.getDeposit(day, money, account);
        // Bank.getBankDB().addTransaction(transaction)
        valid = true;
        return valid;
    }

    public boolean loan(Bank bank, BankAccount account, double money, int day)
    {
        boolean valid = false;
        transfer(bank, bank, account)
        Loan loan = new Loan(bank, customer, money);
        // Bank.getBankDB().addLoan(loan)
        account.deposit(loan.money - bank.getSettings().getTransactionFee());
        Transaction transaction = transactionFactory.getTransfer(day, money, bank, accountFrom);
        // Bank.getBankDB().addTransaction(transaction)
        valid = true;
        return valid;
    }

    public boolean transfer(Bank bank, Transferable sender, Transferable receiver, double money)
    {
        Transaction transaction = transactionFactory.getTransfer(day, money, sender, receiver);
        sender.withdraw(money);
        money -= transactionfee
        bank.money += transactionfee
        receiver.deposit(money);
        // add to transactions of account
        // Bank.getBankDB().addTransaction(transaction)
    }
}