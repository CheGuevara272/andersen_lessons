package org.andersen_project.menu;

import org.andersen_project.context.RepositoryContext;
import org.andersen_project.entity.User;
import org.andersen_project.exception.InputException;
import org.andersen_project.exception.LoginException;
import org.andersen_project.repository.UserRepository;
import org.andersen_project.service.AuthService;
import org.andersen_project.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class MainMenu {
    //private final RepositoryContext repositoryContext;

    private final AuthServiceImpl authService;
    private final AdminMenu adminMenu;
    private final CustomerMenu customerMenu;

    @Autowired
    public MainMenu(AuthServiceImpl authService, AdminMenu adminMenu, CustomerMenu customerMenu) {
        this.authService = authService;
        this.adminMenu = adminMenu;
        this.customerMenu = customerMenu;
    }

    public void run() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Welcome to Coworking Space Reservation Application");
        boolean operatingMenu = true;
        while (operatingMenu) {
            System.out.println("""
                    Choose one of the following options:
                     1. Login
                     2. Exit""");
            //AuthServiceImpl authService = new AuthServiceImpl((UserRepository) repositoryContext.getRepository(UserRepository.class));
            User user = null;

            try {
                if (keyboard.nextLine().equals("1")) {
                    user = authService.authorization();
                    switch (user.getUserRole()) {
                        case CUSTOMER -> customerMenu.run(user);
                        case ADMIN -> adminMenu.run(user);
                    }
                } else {
                    operatingMenu = false;
                }
            } catch (LoginException | InputException e) {
                System.out.println(e.getMessage());
            }
        }
//        ProgramContext.atProgramStop(repositoryContext);
    }
}