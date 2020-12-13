import java.util.ArrayList;

public class main {

    public static void main(String[] args) {



        //new AccountForm();
        new LoginForm(initDebugBank());
    }


    private static Bank initDebugBank(){
        Bank bank = new Bank();
        UserFactory factory = new UserFactory();
        Customer test = factory.createNewCustomer("test", "1234", "Evan", "Bosia");
        Customer test2 = factory.createNewCustomer("test2", "1234", "Foo", "Bar");
        Customer test3 = factory.createNewCustomer("test3", "1234", "John", "Doe");

        ArrayList<Customer> customers = new ArrayList<Customer>();

        customers.add(test);
        customers.add(test2);
        customers.add(test3);

        bank.setCustomers(customers);

        return bank;
    }

}
