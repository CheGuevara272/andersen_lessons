package lesson_1;

import java.util.List;

public class User {
    private String name;
    private final String login;
    private final String password;
    private final boolean admin;
    private List<Reservation> userReservations;

    public User(String login, String password, boolean admin) {
        this.login = login;
        this.password = password;
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
