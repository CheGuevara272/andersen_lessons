package org.andersen_project.repository;

import org.andersen_project.entity.CoworkingSpace;
import org.andersen_project.exception.InputException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CoworkingRepository {
    private static List<CoworkingSpace> spaces = new ArrayList<>();

    public static CoworkingSpace addCoworking(CoworkingSpace space) {
        spaces.add(space);
        return space;
    }

    public static List<CoworkingSpace> findAll() {
        return new ArrayList<>(spaces);
    }

    public static boolean deleteByName(String name) {
        return spaces.removeIf(space -> space.getName().equals(name));
    }

    public static CoworkingSpace findByName(String name) throws InputException {
        return spaces.stream()
                .filter(space -> space.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new InputException("Coworking space with that name is reserved or does not exist"));
    }
}
