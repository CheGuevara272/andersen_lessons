package org.andersen_project.menu;

import org.andersen_project.context.ProgramContext;
import org.andersen_project.context.RepositoryContext;
import org.andersen_project.entity.User;
import org.andersen_project.exception.InputException;
import org.andersen_project.exception.LoginException;
import org.andersen_project.repository.UserRepository;
import org.andersen_project.service.impl.AuthServiceImpl;

import java.util.Scanner;

public class MainMenu {
    static Scanner keyboard = new Scanner(System.in);
    private final RepositoryContext repositoryContext;


    public MainMenu(RepositoryContext repositoryContext) {
        this.repositoryContext = repositoryContext;
    }

    public void run() {
        System.out.println("Welcome to Coworking Space Reservation Application");
        boolean operatingMenu = true;
        while (operatingMenu) {
            System.out.println("""
                    Choose one of the following options:
                     1. Login
                     2. Exit""");
            AuthServiceImpl authService = new AuthServiceImpl((UserRepository) repositoryContext.getRepository(UserRepository.class));
            User user = null;

            try {
                if (keyboard.nextLine().equals("1")) {
                    user = authService.authorization();
                } else {
                    operatingMenu = false;
                }
                switch (user.getRole()) {
                    case CUSTOMER -> new CustomerMenu(repositoryContext).run(user);
                    case ADMIN -> new AdminMenu(repositoryContext).run(user);
                }
            } catch (LoginException | InputException e) {
                System.out.println(e.getMessage());
            }
        }
        ProgramContext.atProgramStop(repositoryContext);
    }
}