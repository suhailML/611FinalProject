public interface GUIRequests 
{
    public boolean withdraw(Bank bank, BankAccount account, double money);
    public boolean deposit(Bank bank, BankAccount account, double money);
    public boolean transfer(Bank bank, Transferable sender, Transferable receiver, double money);
    public boolean takeOutLoan(Bank bank, Customer customer, BankAccount account, double money, String collateral);
    public boolean payBackLoan(Bank bank, Customer customer, BankAccount account, double money, Loan loan);
}
