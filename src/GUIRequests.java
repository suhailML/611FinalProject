public interface GUIRequests 
{
    public boolean withdraw(Bank bank, BankAccount account, double money, int day);
    public boolean deposit(Bank bank, BankAccount account, double money, int day);
    public boolean transfer(Bank bank, Transferable Sender, Transferable receiver, double money, int day);
    //public boolean transfer
    //public boolean loan

    //TODO add these functions --> USER
    // - public boolean createAccount(Bank bank, Customer customer, -- input values -- )
    // ----> input_values --> account name:string?, type:String?, currency:String?
    // - public boolean deleteAccount(Bank bank, Customer customer, Account account)
    // ? - public boolean createLoan(Bank bank, Account account)


    //TODO add these functions --> BANK
    // -

}
