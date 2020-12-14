public interface GUIRequests 
{
    public boolean withdraw(Bank bank, BankAccount account, double money);
    public boolean deposit(Bank bank, BankAccount account, double money);
    public boolean transfer(Bank bank, Transferable sender, Transferable receiver, double money);
    public boolean takeOutLoan(Bank bank, Transferable lendee, Transferable lender, double money, String collateral);
    public boolean payBackLoan(Bank bank, Transferable lendee, Transferable lender, double money, Loan loan);
    public boolean createCustomer(Bank bank, String username, String password, String firstName, String lastName);
    public Customer checkCustomerLogin(Bank bank, String username, String password);
    public Employee checkEmployeeLogin(Bank bank, String username, String password);
    public boolean createAccount(Bank bank, Customer customer, String name, String currency, int accountType);
    public boolean deleteAccount(Bank bank, Customer customer, BankAccount account);

    //TODO add these functions
    
    // - public boolean saveBankSettings(Bank bank, Double transactionFee, Double savingsInterestRate, Double loanInterestRate, Double minSavingsForInterest)
    // - public boolean incrementDay()
    // - public boolean updateCustomer()


    public String queryTransactions(int day);
}
