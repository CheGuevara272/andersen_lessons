package by.andersen.coworkingapp.service.impl;

import by.andersen.coworkingapp.exception.InputException;
import by.andersen.coworkingapp.model.dto.CoworkingSpaceRequest;
import by.andersen.coworkingapp.model.entity.CoworkingSpace;
import by.andersen.coworkingapp.repository.CoworkingSpaceRepository;
import by.andersen.coworkingapp.repository.ReservationRepository;
import by.andersen.coworkingapp.service.CoworkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoworkingServiceImpl implements CoworkingService {
    private final ReservationRepository reservationRepository;
    private final CoworkingSpaceRepository coworkingSpaceRepository;

    @Autowired
    public CoworkingServiceImpl(ReservationRepository reservationRepository, CoworkingSpaceRepository coworkingSpaceRepository) {
        this.reservationRepository = reservationRepository;
        this.coworkingSpaceRepository = coworkingSpaceRepository;
    }

    @Override
    public boolean addCoworking(CoworkingSpaceRequest request) throws InputException {
        CoworkingSpace space = convertToEntity(request);
        coworkingSpaceRepository.save(space);
        return true;
    }

    @Override
    public boolean removeCoworking(Integer coworkingId) throws InputException {
        boolean result = coworkingSpaceRepository.findById(coworkingId).isPresent();
        coworkingSpaceRepository.deleteById(coworkingId);
        return result;
    }

    @Override
    public List<CoworkingSpace> getAllCoworkingSpaces() {
        return coworkingSpaceRepository.findAll();
    }

    @Override
    public List<CoworkingSpace> getAvailableCoworkingSpaces() {
        List<CoworkingSpace> availableCoworkingSpaces = coworkingSpaceRepository.findAll();
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
