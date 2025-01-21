package org.andersen_project.entity;

import org.andersen_project.util.IdGenerator;

import java.io.Serializable;
import java.util.UUID;

public class CoworkingSpace implements Serializable {
    private final String name;
    private final CoworkingType coworkingType;
    private final UUID id;
    private final double price;
    private boolean reserved;

    public CoworkingSpace(String name, CoworkingType coworkingType, double price) {
        this.id = IdGenerator.getInstance().generateUniqueId();
        this.name = name;
        this.coworkingType = coworkingType;
        this.price = price;
        this.reserved = false;
    }

    public UUID getCoworkingId() {
        return id;
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
                "  Id = " + id + '\n' +
                "  Type=" + coworkingType + '\n' +
                "  Price=" + price;
    }
}
