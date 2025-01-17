package org.andersen_project;

public enum Type {
    OPEN_SPACE("Open Space"),
    PRIVATE("Private workspace"),
    MINIMAL("Minimal"),
    FULL_SERVICE("Full-service");

    private final String typeName;

    Type(String typeName) {
        this.typeName = typeName;
    }

    public String getName() {
        return this.typeName;
    }

    @Override
    public String toString() {
        return "Type = " + typeName;
    }
}
