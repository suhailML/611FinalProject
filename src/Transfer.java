public class Transfer extends Transaction{

    private Transferable receiver;

    public Transfer(int day, double money, Transferable sender, Transferable receiver){
        super();
        this.receiver = receiver;
    }
}
