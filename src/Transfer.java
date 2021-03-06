/*
File: Transfer.java
Developer: Tristan Marchand, Evan Bosia
Email: tmarch@bu.edu
Last Edited: Wednesday, December 16, 2020

Description: Subclass of Transaction, representing a Transfer object
*/

public class Transfer extends Transaction
{
    private String senderName;
    private String receiverName;

    /*
    CONSTRUCTORS
    */
    public Transfer(BankAccount account, double money, int day, Transferable sender, Transferable receiver)
    {
        this(account, money, day, sender.getName(), receiver.getName());
    }

    public Transfer (String accountID, double money, int day, String senderName, String receiverName)
    {
        super(accountID, money, day);
        setSender(senderName);
        setReceiver(receiverName);
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
        return "Day: " + getDay() + " Transfer: "+ String.format("%.2f",getMoney()) + " - " + "FROM: " + senderName + " TO: " + receiverName;
    }

}
