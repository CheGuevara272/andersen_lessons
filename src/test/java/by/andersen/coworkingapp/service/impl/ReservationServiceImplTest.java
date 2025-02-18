package by.andersen.coworkingapp.service.impl;

import by.andersen.coworkingapp.exception.InputException;
import by.andersen.coworkingapp.model.entity.CoworkingSpace;
import by.andersen.coworkingapp.model.entity.Reservation;
import by.andersen.coworkingapp.model.entity.User;
import by.andersen.coworkingapp.repository.CoworkingSpaceRepository;
import by.andersen.coworkingapp.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private CoworkingSpaceRepository coworkingSpaceRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @Test
    void getReservations_ShouldReturnAllReservations() {
        List<Reservation> expected = Arrays.asList(
                new Reservation(),
                new Reservation()
        );
        when(reservationRepository.findAll()).thenReturn(expected);

        List<Reservation> result = reservationService.getReservations();

        assertEquals(expected.size(), result.size());
        verify(reservationRepository).findAll();
    }

    @Test
    void getUserReservations_ShouldFilterByUserId() {
        User user1 = User.builder().userId(1).build();
        User user2 = User.builder().userId(2).build();

        Reservation res1 = Reservation.builder().user(user1).build();
        Reservation res2 = Reservation.builder().user(user2).build();
        Reservation res3 = Reservation.builder().user(user1).build();

        when(reservationRepository.findAll()).thenReturn(Arrays.asList(res1, res2, res3));

        List<Reservation> result = reservationService.getUserReservations(1);

        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(r -> r.getUser().getUserId() == 1));
    }

    @Test
    void cancelReservation_ShouldReturnTrueAndDelete_WhenExists() throws InputException {
        int existingId = 1;
        when(reservationRepository.findById(existingId))
                .thenReturn(Optional.of(new Reservation()));

        boolean result = reservationService.cancelReservation(existingId);

        assertTrue(result);
        verify(reservationRepository).deleteById(existingId);
    }

    @Test
    void cancelReservation_ShouldReturnFalseButStillDelete_WhenNotExists() throws InputException {
        int nonExistingId = 999;
        when(reservationRepository.findById(nonExistingId))
                .thenReturn(Optional.empty());

        boolean result = reservationService.cancelReservation(nonExistingId);

        assertFalse(result);
        verify(reservationRepository).deleteById(nonExistingId);
    }

    @Test
    void makeReservation_ShouldCreateReservation_WhenSpaceExists() {
        User user = User.builder().userId(1).build();
        CoworkingSpace space = CoworkingSpace.builder()
                .coworkingName("Awesome Space")
                .build();

        when(coworkingSpaceRepository.findByCoworkingName("Awesome Space"))
                .thenReturn(Collections.singletonList(space));

        boolean result = reservationService.makeReservation(user, "Awesome Space");

        assertTrue(result);
        ArgumentCaptor<Reservation> captor = ArgumentCaptor.forClass(Reservation.class);
        verify(reservationRepository).save(captor.capture());

        Reservation savedReservation = captor.getValue();
        assertEquals(user, savedReservation.getUser());
        assertEquals(space, savedReservation.getSpace());
    }

    @Test
    void makeReservation_ShouldReturnFalse_WhenSpaceNotFound() {
        User user = User.builder().userId(1).build();
        when(coworkingSpaceRepository.findByCoworkingName("Unknown Space"))
                .thenReturn(Collections.emptyList());

        boolean result = reservationService.makeReservation(user, "Unknown Space");

        assertFalse(result);
        verify(reservationRepository, never()).save(any());
    }

    @Test
    void makeReservation_ShouldUseFirstFoundSpace_WhenMultipleExist() {
        User user = User.builder().userId(1).build();
        CoworkingSpace space1 = CoworkingSpace.builder()
                .coworkingName("Duplicated Space")
                .build();
        CoworkingSpace space2 = CoworkingSpace.builder()
                .coworkingName("Duplicated Space")
                .build();

        when(coworkingSpaceRepository.findByCoworkingName("Duplicated Space"))
                .thenReturn(Arrays.asList(space1, space2));

        boolean result = reservationService.makeReservation(user, "Duplicated Space");

        assertTrue(result);
        ArgumentCaptor<Reservation> captor = ArgumentCaptor.forClass(Reservation.class);
        verify(reservationRepository).save(captor.capture());

        assertEquals(space1, captor.getValue().getSpace());
    }
}