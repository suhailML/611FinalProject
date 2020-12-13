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

    public void addLoan(Loan loan){
        System.out.println("DUMMY UPDATE " + loan);
    }
}
