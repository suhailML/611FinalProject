/*
File: Employee.java
Developer: Tristan Marchand, Evan Boria
Email: tmarch@bu.edu
Last Edited: Wednesday, December 16, 2020

Description: Subclass of User representing an Employee. Has access to all data in bank
*/

public class Employee extends User
{
    /*
    CONSTRUCTORS
    */
    public Employee(){}

    public Employee(String username, String password, String userID, String firstName, String lastName)
    {
        super(username, password, userID, firstName, lastName);
    }

    public String fullOutput(){
        return "Employee:\n" + super.fullOutput()+ "\n";
    }
}
