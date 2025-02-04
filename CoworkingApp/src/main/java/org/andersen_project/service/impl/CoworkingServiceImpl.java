package org.andersen_project.service.impl;

import org.andersen_project.entity.CoworkingSpace;
import org.andersen_project.entity.CoworkingType;
import org.andersen_project.exception.InputException;
import org.andersen_project.repository.CoworkingRepository;
import org.andersen_project.repository.ReservationRepository;
import org.andersen_project.service.CoworkingService;

import java.util.List;
import java.util.Scanner;

public class CoworkingServiceImpl implements CoworkingService {
    private final ReservationRepository reservationRepository;
    private final CoworkingRepository coworkingRepository;

    public CoworkingServiceImpl(ReservationRepository reservationRepository, CoworkingRepository coworkingRepository) {
        this.reservationRepository = reservationRepository;
        this.coworkingRepository = coworkingRepository;
    }

    @Override
    public boolean addCoworking() throws InputException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter coworking space name");
        String spaceName = keyboard.nextLine();
        System.out.println("Choose coworking space type");
        printTypes();
        CoworkingType coworkingType = null;
        boolean typeNotChosen = true;
        while (typeNotChosen) {
            switch (keyboard.nextLine()) {
                case "1" -> {
                    coworkingType = CoworkingType.OPEN_SPACE;
                    typeNotChosen = false;
                }
                case "2" -> {
                    coworkingType = CoworkingType.PRIVATE;
                    typeNotChosen = false;
                }
                case "3" -> {
                    coworkingType = CoworkingType.MINIMAL;
                    typeNotChosen = false;
                }
                case "4" -> {
                    coworkingType = CoworkingType.FULL_SERVICE;
                    typeNotChosen = false;
                }
            }
        }

        System.out.println("Enter coworking price");
        double price = keyboard.nextDouble();
        CoworkingSpace space = CoworkingSpace.builder()
                .coworkingId(0)
                .coworkingName(spaceName)
                .coworkingType(coworkingType)
                .price(price)
                .build();
        coworkingRepository.update(space);
        return true;
    }

    @Override
    public boolean removeCoworking() throws InputException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter name of coworking space that you want to delete");
        coworkingRepository.findAll().forEach(System.out::println);
        Integer coworkingId = keyboard.nextInt();

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

    private void printTypes() {
        int i = 0;
        for (CoworkingType coworkingType : CoworkingType.values()) {
            System.out.println(++i + ") " + coworkingType.toString());
        }
    }
}
