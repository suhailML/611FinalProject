
public interface Transferable 
{
    public boolean send(double money);
    public boolean receive(double money);
    public boolean addTransaction(Transaction transaction);
}
