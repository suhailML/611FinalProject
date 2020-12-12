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

    public boolean withdraw(Bank bank, BankAccount account, double money, int day)
    {
        boolean valid = false;
        if (money + bank.getSettings().getTransactionFee() < account.getBalance())
        {
            account.withdraw(money + bank.getSettings().getTransactionFee());
            //Transaction transaction = transactionFactory.getWithdraw(day, money, account);
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
        //Transaction transaction = transactionFactory.getDeposit(day, money, account);
        // Bank.getBankDB().addTransaction(transaction)
        valid = true;
        return valid;
    }

    public boolean loan(Bank bank, BankAccount account, double money, int day)
    {
        boolean valid = false;
        /*
        //transfer(bank, bank, account);
        Loan loan = new Loan(bank, customer, money);
        // Bank.getBankDB().addLoan(loan)
        account.deposit(loan.money - bank.getSettings().getTransactionFee());
        Transaction transaction = transactionFactory.getTransfer(day, money, bank, accountFrom);
        // Bank.getBankDB().addTransaction(transaction)
        */
        valid = true;
        return valid;
    }

    public boolean transfer(Bank bank, Transferable sender, Transferable receiver, double money, int day)
    {
        //TODO transaction
        //Transaction transaction = transactionFactory.getTransfer(day, money, sender, receiver);

        double fee = bank.getSettings().getTransactionFee();

        if(sender.isValidWithdraw(money + fee)){

            // send the fee to the bank
            //      - a bank will send itself the fee if it is the sender
            sender.addMoney(-fee);
            bank.addMoney(fee);

            // send the money to the receiver
            sender.addMoney(-money);
            receiver.addMoney(money);
            // Bank.getBankDB().addTransaction(transaction)
            return true;
        }
        else{
            System.out.println("Invalid transfer");
            return false;
        }

        // Bank.getBankDB().addTransaction(transaction)
    }
}