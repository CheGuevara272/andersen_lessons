package org.andersen_project.entity;

import java.io.Serializable;

public class CoworkingSpace implements Serializable {
    private final String name;
    private final CoworkingType coworkingType;
    private Integer coworkingId;
    private double price;
    private boolean reserved;

    public CoworkingSpace(Integer coworkingId, String name, CoworkingType coworkingType, double price) {
        this.coworkingId = coworkingId;
        this.name = name;
        this.coworkingType = coworkingType;
        this.price = price;
        this.reserved = false;
    }

    public Integer getCoworkingId() {
        return coworkingId;
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
                "  Id = " + coworkingId + '\n' +
                "  Type=" + coworkingType + '\n' +
                "  Price=" + price;
    }
}
