package by.andersen.coworkingapp.model.enums;

public enum UserRole {
    CUSTOMER("CUSTOMER"),
    ADMIN("ADMIN");

    private final String userRole;

    UserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getName() {
        return this.userRole;
    }

    @Override
    public String toString() {
        return "User role = " + userRole;
    }
}
