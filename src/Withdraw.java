public class Withdraw extends Transaction{

    public Withdraw(int day, double money, BankAccount account){
        System.out.println(day + " " + money + " " + account);
    }

    public String toString(){
        return "Withdraw" + super.toString();
    }
}
