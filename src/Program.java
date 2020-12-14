import java.util.List;

public class Program 
{
    public static void run()
    {
        Bank bank = startup();
    }

    private static Bank startup()
    {
        Bank bank = new Bank();
        //bank.getBankDB().getBankSettings();
        BankDatabase db = bank.getBankDB();
        
        List<List<String>> employeeCredentials = db.getAllEmployeeCredentials();
        System.out.println(employeeCredentials);

        return bank;
    }

    public static void main(String[] args)
    {
        startup();
    }
}
