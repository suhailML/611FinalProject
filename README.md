3) You should submit a readme. The readme file should be a short txt file with your name, email, BU ID, compilation and execution instructions that you should have tested to make sure they work (because if we follow them and they don’t work you will lose points) and a simple description of each class. Nothing more than two sentences for each class. For example, for the "Player" class you could have something like "A class representing a board game player, it is created in an abstract way so it can be extended for other kind of players as well." or "it is created in a specific way tightly bound to this game to provide better specialization". Lastly, if you have implemented anything that was not required and can be considered as a bonus or if you have skipped implementing a part of the assignment you should mention that too, as well as any other special comments about your submission you may have.


# CS611 Final Project

Evan Bosia
ejbosia@bu.edu
U23665170

## Compilation and Execution Instructions
1. Open folder in command terminal.
2. javac \*.java
3. java main

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

Deposit

Transaction

TransactionFactory

Transfer

Withdraw

Transferable


### UI Forms
AccountForm - open an account for the user

AddAccountDialog - dialog that provides fields to add an account for a user

BankEmployeeForm - homepage for the employee login

BankSettingsForm - allows the employee to change the current settings of the bank


CreateEmployeeDialog
CreateUserDialog


DepositWithdrawDialog
EditUserDialog

EmployeeViewCustomerForm
EmployeeViewTransactionsDialog
GUIRequests

LoanCreateDialog

LoanPaybackDialog
LoginForm





TransferDialog

UserForm

### Unused

StockOrder

BuyOrder

SellOrder



## Extra / Missed Features