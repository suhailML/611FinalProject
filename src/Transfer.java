public class Transfer extends Transaction
{
    private String senderName;
    private String receiverName;

    public Transfer(BankAccount account, double money, int day, Transferable sender, Transferable receiver)
    {
        this(account, money, day, sender.getName(), receiver.getName());
    }

    public Transfer (BankAccount account, double money, int day, String senderName, String receiverName)
    {
        super(account, money, day);
        setSender(senderName);
        setReceiver(receiverName);
    }

    /*
    SETTERS
    */
    public void setSender(String senderName)
    {
        this.senderName = senderName;
    }

    public void setReceiver(String receiverName)
    {
        this.receiverName = receiverName;
    }

    /*
    ACCESSORS
    */
    public String getSender()
    {
        return senderName;
    }

    public String getReceiver()
    {
        return receiverName;
    }

    public String toString() {
        return "Transfer: " + getAccount().getCurrencyType() + getMoney() + " - " + "FROM: " + sender + " TO: " + receiver;
    }

}
