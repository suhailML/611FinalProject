public class Deposit extends Transaction {

    public Deposit(int day, double money, BankAccount account){
        System.out.println(day + " " + money + " " + account);
    }

    public String toString(){
        return "DEPOSIT" + super.toString();
    }
}
