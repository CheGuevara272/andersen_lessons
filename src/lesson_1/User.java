package lesson_1;

import java.util.List;

public class User {
    private String login;
    private String password;
    private boolean admin;
    private List<CoworkingSpace> reservedCoworkingSpace;

    public User(String login, String password, boolean admin) {
        this.login = login;
        this.password = password;
        this.admin = admin;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return admin;
    }
}
