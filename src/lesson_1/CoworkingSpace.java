package lesson_1;

import java.util.Date;

public class CoworkingSpace {
    private String name;

    private Type type;
    private double price;
    private boolean reserved;

    CoworkingSpace(String name, Type type, double price) {
        this.name = name;
        this.type = type;
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

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    @Override
    public String toString() {
        return "    Name = " + name + '\n' +
                "    Type=" + type + '\n' +
                "    Price=" + price;
    }
}
