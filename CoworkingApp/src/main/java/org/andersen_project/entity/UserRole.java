package org.andersen_project.entity;

public enum UserRole {
    CUSTOMER("customer"),
    ADMIN("admin");

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
