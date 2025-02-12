package by.andersen.coworkingapp.service.impl;

import by.andersen.coworkingapp.exception.InputException;
import by.andersen.coworkingapp.model.dto.CoworkingSpaceRequest;
import by.andersen.coworkingapp.model.entity.CoworkingSpace;
import by.andersen.coworkingapp.model.enums.CoworkingType;
import by.andersen.coworkingapp.repository.CoworkingRepository;
import by.andersen.coworkingapp.repository.ReservationRepository;
import by.andersen.coworkingapp.service.CoworkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoworkingServiceImpl implements CoworkingService {
    private final ReservationRepository reservationRepository;
    private final CoworkingRepository coworkingRepository;

    @Autowired
    public CoworkingServiceImpl(ReservationRepository reservationRepository, CoworkingRepository coworkingRepository) {
        this.reservationRepository = reservationRepository;
        this.coworkingRepository = coworkingRepository;
    }

    @Override
    public boolean addCoworking(CoworkingSpaceRequest request) throws InputException {
        CoworkingSpace space = convertToEntity(request);
        coworkingRepository.update(space);
        return true;
    }

    @Override
    public boolean removeCoworking(Integer coworkingId) throws InputException {
        boolean result = false;
        if (coworkingRepository.findById(coworkingId).isPresent()) {
            result = true;
        }
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
