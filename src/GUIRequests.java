public interface GUIRequests 
{

    //TODO add these functions --> USER
    // - public boolean createAccount(Bank bank, Customer customer, -- input values -- )
    // ----> input_values --> account name:string?, type:String?, currency:String?
    // - public boolean deleteAccount(Bank bank, Customer customer, Account account)
    // ? - public boolean createLoan(Bank bank, Account account)


    //TODO add these functions --> BANK
    // -

    public boolean withdraw(Bank bank, BankAccount account, double money);
    public boolean deposit(Bank bank, BankAccount account, double money);
    public boolean transfer(Bank bank, Transferable sender, Transferable receiver, double money);
    public boolean takeOutLoan(Bank bank, Transferable lendee, Transferable lender, double money, String collateral);
    public boolean payBackLoan(Bank bank, Transferable lendee, Transferable lender, double money, Loan loan);
}
