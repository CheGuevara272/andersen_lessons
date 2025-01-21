package org.andersen_project.service;

import org.andersen_project.entity.CoworkingSpace;
import org.andersen_project.exception.InputException;

import java.util.List;
import java.util.UUID;

public interface CoworkingService {
    boolean addCoworking() throws InputException;
    boolean removeCoworking() throws InputException;
    List<CoworkingSpace> getAllCoworkingSpaces();
    List<CoworkingSpace> getAvailableCoworkingSpaces();
}
