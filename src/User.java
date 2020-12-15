public abstract class User
{
    private String username;
    private String password;
    private String userID;
    private String firstName;
    private String lastName;

    /*
    CONSTRUCTORS
    */
    public User(){}

    public User(String username, String password, String userID, String firstName, String lastName)
    {
        setUsername(username);
        setPassword(password);
        setUserID(userID);
        setFirstName(firstName);
        setLastName(lastName);
    }

    /*
    SETTERS
    */
    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setUserID(String userID)
    {
        this.userID = userID;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /*
    ACCESSORS
    */
    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getUserID()
    {
        return userID;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String fullOutput(){
        return " - Username: "+ username + "\n - Password: " + password + "\n - Name: " + firstName + " " + lastName;
    }

    public String toString(){
        return firstName + " " + lastName;
    }
}