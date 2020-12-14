import java.util.ArrayList;

public class main {

    public static void main(String[] args) {

        //new AccountForm();
        //new LoginForm(initDebugBank());
        Program.run();
    }


    private static Bank initDebugBank(){
        Bank bank = new Bank();
        UserFactory factory = new UserFactory();
        Customer test = factory.createNewCustomer("test", "1234", "Evan", "Bosia");
        Customer test2 = factory.createNewCustomer("test2", "1234", "Foo", "Bar");
        Customer test3 = factory.createNewCustomer("test3", "1234", "John", "Doe");


        ArrayList<BankAccount> bankAccounts = new ArrayList<>();

        BankAccount accountChecking = new CheckingAccount("Checking", "123C", "$");
        accountChecking.receive(10000);

        BankAccount accountSavings = new SavingsAccount("Savings", "123", "$");
        accountSavings.receive(10000);


        bankAccounts.add(accountChecking);
        bankAccounts.add(accountSavings);

        ArrayList<Loan> testLoans = new ArrayList<>();
        testLoans.add(new Loan(bank, accountChecking, "LOAN1", 1000, 1000, 5, "FOO"));

        test.setLoans(testLoans);

        ArrayList<Customer> customers = new ArrayList<Customer>();

        customers.add(test);
        customers.add(test2);
        customers.add(test3);

        bank.setCustomers(customers);

        ArrayList<Employee> employees = new ArrayList<Employee>();
        Employee employee = factory.createNewEmployee("test2", "1234", "Foo", "Bar");
        employees.add(employee);

        bank.setEmployees(employees);

        return bank;
    }

}
