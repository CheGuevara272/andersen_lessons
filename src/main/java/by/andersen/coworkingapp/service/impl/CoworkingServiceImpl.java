package by.andersen.coworkingapp.service.impl;

import by.andersen.coworkingapp.exception.InputException;
import by.andersen.coworkingapp.model.dto.CoworkingSpaceRequest;
import by.andersen.coworkingapp.model.entity.CoworkingSpace;
import by.andersen.coworkingapp.repository.CoworkingRepository;
import by.andersen.coworkingapp.service.CoworkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoworkingServiceImpl implements CoworkingService {
    private final CoworkingRepository coworkingRepository;

    @Autowired
    public CoworkingServiceImpl(CoworkingRepository coworkingRepository) {
        this.coworkingRepository = coworkingRepository;
    }

    @Override
    public boolean addCoworking(CoworkingSpaceRequest request) {
        CoworkingSpace space = convertToEntity(request);
        coworkingRepository.update(space);
        return true;
    }

    @Override
    public boolean removeCoworking(Integer coworkingId) throws InputException {
        boolean result = coworkingRepository.findById(coworkingId).isPresent();
        coworkingRepository.deleteById(coworkingId);
        return result;
    }

    @Override
    public List<CoworkingSpace> getAllCoworkingSpaces() {
        return coworkingRepository.findAll();
    }

    @Override
    public List<CoworkingSpace> getAvailableCoworkingSpaces() {
        List<CoworkingSpace> availableCoworkingSpaces = coworkingRepository.findAll();
        return availableCoworkingSpaces.stream().filter(coworkingSpace -> !coworkingSpace.isReserved()).toList();
    }

    private CoworkingSpace convertToEntity(CoworkingSpaceRequest request) {
        return CoworkingSpace.builder()
                .coworkingId(0)
                .coworkingName(request.getCoworkingName())
                .coworkingType(request.getCoworkingType())
                .price(request.getPrice())
                .reserved(false)
                .build();
    }
}
