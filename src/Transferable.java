/*
File: Transferable.java
Developer: Tristan Marchand
Email: tmarch@bu.edu
Last Edited: Wednesday, December 16, 2020

Description: Interface for a Transferable object, which allows money to be sent and recieved from an object. Name is required for displaying in GUI.
*/

public interface Transferable 
{
    public boolean send(double money);
    public boolean receive(double money);
    public String getName();
}
