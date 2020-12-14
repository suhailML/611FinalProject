/** THis is a dummy class - need to fill in **/
public class BankDB {


    private static BankDB bankDB;

    private BankDB(){
        System.out.println("DUMMY BANK DB");
    }

    public static BankDB getSingleInstance(){
        if(bankDB == null){
            bankDB = new BankDB();
        }
        return bankDB;
    }

    public void addTransaction(Transaction t){
        System.out.println("DUMMY ADD " + t);
    }

    public void updateLoan(Loan loan){
        System.out.println("DUMMY UPDATE " + loan);
    }

    public void addAccount(BankAccount account){
        System.out.println("DUMMY ADD " + account);
    }

    public void addCustomer(Customer customer){
        System.out.println("DUMMY ADD " + customer);
    }

    public void deleteAccount(BankAccount account){
        System.out.println("DUMMY DELETE " + account);
    }


    public void addEmployee(Employee employee){
        System.out.println("DUMMY ADD" + employee);
    }

    public void addLoan(Loan loan){
        System.out.println("DUMMY UPDATE " + loan);
    }
}
