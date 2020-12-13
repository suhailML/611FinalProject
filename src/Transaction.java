public class Transaction {

    BankAccount sender;
    BankAccount receiver;
    int money;

    public Transaction(){
        sender = new CheckingAccount("TEST", "A", "$");
        receiver = new CheckingAccount("TEST2", "B", "$");
        money = 100;
    }

    public String toString(){
        return sender.getName() + " --> " + receiver.getName() + " : " + money;
    }
}
