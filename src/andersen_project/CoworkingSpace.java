package andersen_project;

import java.io.Serializable;

public class CoworkingSpace implements Serializable {
    private final String name;

    private final Type type;
    private double price;
    private boolean reserved;

    CoworkingSpace(String name, Type type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
        reserved = false;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public boolean isNotReserved() {
        return !reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    @Override
    public String toString() {
        return "Name = " + name + '\n' +
                "  Type=" + type + '\n' +
                "  Price=" + price;
    }
}
