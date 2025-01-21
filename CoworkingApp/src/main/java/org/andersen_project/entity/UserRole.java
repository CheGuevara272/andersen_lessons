package org.andersen_project.entity;

public enum UserRole {
    CUSTOMER("customer"),
    ADMIN("admin");

    private final String userType;

    UserRole(String userType) {
        this.userType = userType;
    }

    public String getName() {
        return this.userType;
    }

    @Override
    public String toString() {
        return "User type = " + userType;
    }
}
