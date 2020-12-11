public interface GUIRequests 
{
    public boolean withdraw(Bank bank, BankAccount account, double money, int day);
    public boolean deposit(Bank bank, BankAccount account, double money, int day);
    //public boolean transfer
    //public boolean loan
}
