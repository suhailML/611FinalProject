public class Transfer extends Transaction
{
    private Transferable sender;
    private Transferable receiver;

    public Transfer(BankAccount account, double money, int day, Transferable sender, Transferable receiver)
    {
        super(account, money, day);
        setSender(sender);
        setReceiver(receiver);
    }

    /*
    SETTERS
    */
    public void setSender(Transferable sender)
    {
        this.sender = sender;
    }

    public void setReceiver(Transferable receiver)
    {
        this.receiver = receiver;
    }

    /*
    ACCESSORS
    */
    public Transferable getSender()
    {
        return sender;
    }

    public Transferable getReceiver()
    {
        return receiver;
    }

    public String toString() {
        return "Transfer: " + getAccount().getCurrencyType() + getMoney() + " - " + "FROM: " + sender + " TO: " + receiver;
    }

}
