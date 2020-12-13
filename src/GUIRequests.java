public interface GUIRequests 
{



    public boolean withdraw(Bank bank, BankAccount account, double money);
    public boolean deposit(Bank bank, BankAccount account, double money);
    public boolean transfer(Bank bank, Transferable sender, Transferable receiver, double money);
    public boolean takeOutLoan(Bank bank, Transferable lendee, Transferable lender, double money, String collateral);
    public boolean payBackLoan(Bank bank, Transferable lendee, Transferable lender, double money, Loan loan);

    //TODO add these functions
    // - public boolean createCustomer(Bank bank, String username, String password, String firstName, String lastName);
    // - public boolean createAccount(Bank bank, Customer customer, String name, String currency, Enum AccountType??) --> we might want to do this to have one account method. Could also make a method for each otherwise
    // - public Customer? checkUserLogin(String username, String password)
    // - public Employee? checkEmployeeLogin(String username, String password)
    // - public boolean deleteAccount(Bank bank, Customer customer, Account account) --> do not delete account when there are loans
    // - public boolean saveBankSettings(Bank bank, Double transactionFee, Double savingsInterestRate, Double loanInterestRate, Double minSavingsForInterest)


}
