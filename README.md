# CS611 Final Project

Evan Bosia
ejbosia@bu.edu
U23665170

Tristan Marchand
tmarch@bu.edu
U13495035

Suhail Singh
suhails@bu.edu
U05507139

## Compilation Instructions
1. Open folder in command terminal.
2. javac \*.java
3. java main


## Execution Instructions
The first screen is the login screen. Enter the username and password into their respective fields.

1. Bank Login - use the credentials to login as an employee
2. Create User - create a customer user (opens form)
3. User Login - use the credentials to login as an user

If login is successful, depending on the type of login, either the bank homepage or the customer homepage is shown.

### NOTES
 - Users must be created with an account.
 - Accounts must be created with a deposit.
 - Adding and deleting accounts charges a bank fee.
 - Banks control updating the day ~ used to calculate interest.
 - Transfers are between two different accounts or a bank and an account
 - All transactions (withdraw, deposit, transfer) incur a bank fee. 

 - Testing Credentials (can be used to login as a customer or an employee, testing purposes only)
 username: test
 password: t


## Class Structure:

main - entry point to the program.

Program - runs the startup code, which reads all info from the database and creates all objects. Passes the startup() outputed Bank object to the 
login form, and the GUI opens.

### Database

ParseFile - Our program uses .txt files to store data, similar to the .txt files used to store data in the Legends Project. ParseFile is used to parse and edit these text files in order to load the data into the Bank on startup, as well as keep the Bank's data intact after closing and reopening the program. 

BankDatabase - This class serves to connect the codebase to our database. The codebase can use this class to make calls to the database to add new data and update old data.

### Bank

Bank
BankSettings

The Bank is the main object of the program, which holds reference to all information in the system. BankSettings holds variables specific to the bank.

BankRequestManager - Uses Singleton design pattern. Handles all requests from the GUI

### User

User
Customer
Employee

User is an abstract superclass, with subclasses Customer and Employee. Customer has access to all its accounts and loans, and employee has access
to everything in the database.

### Accounts

BankAccount
SavingsAccount 
CheckingAccount 

BankAccount is an abstract superclass, with subclasses SavingsAccount and CheckingAccount. 

TransactionHistory

TransactionHistory is held by the BankAccount object, which stores all transactions for that account.

### Transactions

Loan - an object holding the information for a loan

Transaction
Deposit
Transfer
Withdraw 

Transaction is an abstract superclass, with subclasses Deposit, Transfer, and Withdraw. Holds all relevant information for each transaction.

Transferable interface - classes that implement this interface provide the *boolean send(double money)*, *boolean receive(double money)*, and *String getName()* methods that allow for the transfer of money to and from the object.

### Factories

UserFactory
BankAccountFactory
TransactionFactory
LoanFactory

Factory Design Patterns for Users, BankAccounts, Transactions, and Loans. Methods for creating objects from both the GUI and the database. 

### UI Forms
AccountForm - open an account for the user

AddAccountDialog - dialog that provides fields to add an account for a user

BankEmployeeForm - homepage for the employee - allows the employee to 

BankSettingsForm - allows the employee to change the current settings of the bank

CreateEmployeeDialog - allows an employee to create credentials of a new employee

CreateUserDialog - allows the user to create a customer account - the user is then prompted to create an account

DepositWithdrawDialog - allows the user to withdraw or deposit money into one of their accounts (selectable in window)

EditUserDialog - allows the user to edit their password, firstname, and lastname values

EmployeeViewCustomerForm - allows the employee to view customer specifics, such as a summary of their holdings, and individually selectable accounts and loans

EmployeeViewTransactionsDialog - allows the employee to query transactions for a selected day

GUIRequests interface - used to force certains methods called by the UI in the BankRequestManager ~ easy to read

LoanCreateDialog - allows the user to "request" a loan - *all loans are approved*

LoanPaybackDialog - allows the user to pay back a loan by making a transfer from one of their accounts

LoginForm - entrypoint to the user interface - allows the user to login with employee or user credentials, or create a new user account

TransferDialog - allows the user to transfer money in between accounts, 

UserForm - homepage for the customer - has options to add or delete an account, deposit or withdraw from an account, transfer money between accounts, and request or payback loans

### Unused

StockOrder
BuyOrder
SellOrder

StockOrder is a subclass of Transaction, and is the super class to BuyOrder and SellOrder. These orders would be used for the "Securities Account" part of the program which was unimplemented.

## Extra / Missed Features
We did not attempt the bonus of stocks.