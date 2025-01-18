package org.andersen_project.entity;

public enum UserType {
    CUSTOMER("customer"),
    ADMIN("admin");

    private final String userType;

    UserType(String userType) {
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
