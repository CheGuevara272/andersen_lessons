package org.andersen_project.entity;

import java.io.Serializable;

public class CoworkingSpace implements Serializable {
    private final String name;

    private final CoworkingType coworkingType;
    private double price;
    private boolean reserved;

    public CoworkingSpace(String name, CoworkingType coworkingType, double price) {
        this.name = name;
        this.coworkingType = coworkingType;
        this.price = price;
        this.reserved = false;
    }

    public String getName() {
        return name;
    }

    public CoworkingType getType() {
        return coworkingType;
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
                "  Type=" + coworkingType + '\n' +
                "  Price=" + price;
    }
}
