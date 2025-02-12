package by.andersen.coworkingapp.model.enums;

public enum CoworkingType {
    OPEN_SPACE("Open space"),
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
