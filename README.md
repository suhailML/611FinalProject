# CS611 Final Project

Evan Bosia
ejbosia@bu.edu
U23665170

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


## Class Structure:

main - entry point to the program.

### Database

ParseFile

Program

### Bank

Bank

BankDatabase

BankRequestManager

BankSettings

### User

User

UserFactory

Customer

Employee

### Accounts

BankAccount

BankAccountFactory

SavingsAccount extends BankAccount

CheckingAccount extends BankAccount

TransactionHistory

### Transactions

Loan

LoanFactory

Deposit extends Transaction

Transaction

TransactionFactory

Transfer extends Transaction

Withdraw extends Transaction

Transferable interface - classes that implement this interface provide the *boolean send(double money)*, *boolean receive(double money)*, and *String getName()* methods that allow for the transfer of money to and from the object.


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

StockOrder extends Transaction

BuyOrder extends StockOrder

SellOrder extends StockOrder

## Extra / Missed Features
We did not attempt the bonus of stocks.