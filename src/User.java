/**
 * Project 4 - User
 *
 * A class to contain all the functionality of Users
 *
 * @author Yash Ashtekar
 *
 * @version 13/06/2023
 *
 */
public class User {
    private String email;
    private String password;

    public User() {

    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}