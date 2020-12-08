public abstract class User
{
    private String username;
    private String password;
    private int userId;
    private String firstName;
    private String lastName;

    /*
    CONSTRUCTORS
    */
    public User(){}

    public User(String username, String password, int userId, String firstName, String lastName)
    {
        setUsername(username);
        setPassword(password);
        setUserId(userId);
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

    public void setUserId(int userId)
    {
        this.userId = userId;
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

    public int getUserId()
    {
        return userId;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }
}