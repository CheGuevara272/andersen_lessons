package lesson_1;

public enum Type {
    OpenSpace("Open Space"),
    Private("Private workspace"),
    Minimal("Minimal"),
    FullService("Full-service");

    private String typeName;

    Type(String typeName) {
        this.typeName = typeName;
    }

    public String getName() { return this.typeName; }

    @Override
    public String toString() {
        return "Type = " + typeName;
    }
}
