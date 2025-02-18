package by.andersen.coworkingapp.service.impl;

import by.andersen.coworkingapp.exception.InputException;
import by.andersen.coworkingapp.model.dto.CoworkingSpaceRequest;
import by.andersen.coworkingapp.model.entity.CoworkingSpace;
import by.andersen.coworkingapp.model.enums.CoworkingType;
import by.andersen.coworkingapp.repository.CoworkingSpaceRepository;
import by.andersen.coworkingapp.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CoworkingServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private CoworkingSpaceRepository coworkingSpaceRepository;

    @InjectMocks
    private CoworkingServiceImpl coworkingService;

    @Test
    void addCoworking_ShouldSaveEntityAndReturnTrue_WhenValidRequest() throws InputException {
        CoworkingSpaceRequest request = new CoworkingSpaceRequest("Workspace 1", 25.0, CoworkingType.OPEN_SPACE);

        boolean result = coworkingService.addCoworking(request);

        ArgumentCaptor<CoworkingSpace> captor = ArgumentCaptor.forClass(CoworkingSpace.class);
        verify(coworkingSpaceRepository).save(captor.capture());

        CoworkingSpace savedEntity = captor.getValue();
        assertEquals(request.getCoworkingName(), savedEntity.getCoworkingName());
        assertEquals(request.getCoworkingType(), savedEntity.getCoworkingType());
        assertEquals(request.getPrice(), savedEntity.getPrice());
        assertFalse(savedEntity.isReserved());
        assertTrue(result);
    }

    @Test
    void removeCoworking_ShouldReturnTrueAndDelete_WhenIdExists() throws InputException {
        int existingId = 1;
        when(coworkingSpaceRepository.findById(existingId))
                .thenReturn(Optional.of(new CoworkingSpace()));

        boolean result = coworkingService.removeCoworking(existingId);

        assertTrue(result);
        verify(coworkingSpaceRepository).deleteById(existingId);
    }

    @Test
    void removeCoworking_ShouldReturnFalse_WhenIdNotExists() throws InputException {
        int nonExistingId = 999;
        when(coworkingSpaceRepository.findById(nonExistingId))
                .thenReturn(Optional.empty());

        boolean result = coworkingService.removeCoworking(nonExistingId);

        assertFalse(result);
        verify(coworkingSpaceRepository).deleteById(nonExistingId);
    }

    @Test
    void getAllCoworkingSpaces_ShouldReturnAllSpacesFromRepository() {
        List<CoworkingSpace> expected = Arrays.asList(
                new CoworkingSpace(),
                new CoworkingSpace()
        );
        when(coworkingSpaceRepository.findAll()).thenReturn(expected);

        List<CoworkingSpace> result = coworkingService.getAllCoworkingSpaces();

        assertEquals(expected.size(), result.size());
        assertSame(expected, result);
    }

    @Test
    void getAvailableCoworkingSpaces_ShouldFilterReservedSpaces() {
        CoworkingSpace reserved = CoworkingSpace.builder().reserved(true).build();
        CoworkingSpace available = CoworkingSpace.builder().reserved(false).build();
        when(coworkingSpaceRepository.findAll()).thenReturn(Arrays.asList(reserved, available));

        List<CoworkingSpace> result = coworkingService.getAvailableCoworkingSpaces();

        assertEquals(1, result.size());
        assertFalse(result.get(0).isReserved());
    }
}