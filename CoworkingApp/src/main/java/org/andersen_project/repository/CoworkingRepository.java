package org.andersen_project.repository;

import org.andersen_project.entity.CoworkingSpace;
import org.andersen_project.entity.Reservation;
import org.andersen_project.exception.InputException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CoworkingRepository<T extends Serializable> implements Repository<CoworkingSpace> {
    private final List<CoworkingSpace> coworkingSpaceList = new ArrayList<>();

    public CoworkingRepository(List<CoworkingSpace> coworkingSpaceList) {
        this.coworkingSpaceList.addAll(coworkingSpaceList);
    }

    @Override
    public boolean update(CoworkingSpace space) {
        return coworkingSpaceList.add(space);
    }

    @Override
    public List<CoworkingSpace> findAll() {
        List<CoworkingSpace> spaces = new ArrayList<>(coworkingSpaceList);
        return spaces;
    }

    @Override
    public CoworkingSpace findById(UUID id) throws InputException {
        return coworkingSpaceList.stream()
                .filter(coworkingSpace -> coworkingSpace.getCoworkingId().equals(id))
                .findFirst()
                .orElseThrow(() -> new InputException("Coworking space with that name is reserved or does not exist"));
    }

    @Override
    public CoworkingSpace findByName(String name) throws InputException {
        return coworkingSpaceList.stream()
                .filter(coworkingSpace -> coworkingSpace.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new InputException("Coworking space with that name is reserved or does not exist"));
    }

    @Override
    public boolean deleteById(UUID id) {
        return coworkingSpaceList.removeIf(coworkingSpace -> coworkingSpace.getCoworkingId().equals(id));
    }

    @Override
    public boolean deleteByName(String name) {
        return coworkingSpaceList.removeIf(coworkingSpace -> coworkingSpace.getName().equals(name));
    }
}
