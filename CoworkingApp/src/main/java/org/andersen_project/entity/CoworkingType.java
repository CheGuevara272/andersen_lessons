package org.andersen_project.entity;

public enum CoworkingType {
    OPEN_SPACE("Open Space"),
    PRIVATE("Private workspace"),
    MINIMAL("Minimal"),
    FULL_SERVICE("Full-service");

    private final String coworkingType;

    CoworkingType(String coworkingType) {
        this.coworkingType = coworkingType;
    }

    public String getName() {
        return this.coworkingType;
    }

    @Override
    public String toString() {
        return "Coworking type = " + coworkingType;
    }
}
