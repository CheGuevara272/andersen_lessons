package by.andersen.coworkingapp.service;


import by.andersen.coworkingapp.exception.InputException;
import by.andersen.coworkingapp.model.dto.CoworkingSpaceRequest;
import by.andersen.coworkingapp.model.entity.CoworkingSpace;

import java.util.List;

public interface CoworkingService {
    boolean addCoworking(CoworkingSpaceRequest request) throws InputException;

    boolean removeCoworking(Integer id) throws InputException;

    List<CoworkingSpace> getAllCoworkingSpaces();

    List<CoworkingSpace> getAvailableCoworkingSpaces();
}
