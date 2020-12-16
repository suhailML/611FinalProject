/*
File: GUIRequest.java
Developer: Tristan Marchand, Evan Bosia
Email: tmarch@bu.edu
Last Edited: Wednesday, December 16, 2020

Description: Stores all function calls made by the GUI
*/

public interface GUIRequests 
{
    public boolean withdraw(Bank bank, BankAccount account, double money);
    public boolean deposit(Bank bank, BankAccount account, double money);
    public boolean transfer(Bank bank, Transferable sender, Transferable receiver, double money);
    public boolean takeOutLoan(Bank bank,Customer customer, Transferable lender, Transferable lendee, double money, String collateral);
    public boolean payBackLoan(Bank bank, Transferable lendee, Transferable lender, double money, Loan loan);
    public Customer createCustomer(Bank bank, String username, String password, String firstName, String lastName);
    public Customer checkCustomerLogin(Bank bank, String username, String password);
    public Employee checkEmployeeLogin(Bank bank, String username, String password);
    public BankAccount createAccount(Bank bank, Customer customer, String name, String currency, int accountType, double deposit);
    public boolean deleteAccount(Bank bank, Customer customer, BankAccount account);
    public boolean saveBankSettings(Bank bank, double transactionFee, double savingsInterestRate, double loanInterestRate, double minSavingsForInterest);
    public boolean updateCustomer(Bank bank, Customer customer, String firstName, String lastName, String password);
    public boolean incrementDay(Bank bank);
    public String queryTransactions(Bank bank, int day);
}
