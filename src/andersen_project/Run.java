package andersen_project;

import andersen_project.exception.AuthorizationException;
import andersen_project.exception.InputException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Run {
    static Scanner keyboard = new Scanner(System.in);
    static List<User> users = new ArrayList<>();
    static List<CoworkingSpace> spaces = new ArrayList<>();
    static List<Reservation> reservationList = new ArrayList<>();

    public static void main(String[] args) {
        ProgramState.atProgramStart();

        String folderPath = "P:\\IdeaProjects\\andersen_lessons\\out\\production\\andersen_lessons";
        String className = "HelloWorld";

        try {
            CustomClassLoader classLoader = new CustomClassLoader(folderPath);

            Class<?> loadedClass = classLoader.loadClass(className);

            Object instance = classLoader.loadClass(className);

            Method method = loadedClass.getMethod("printMessage");
            method.invoke(instance);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("Welcome to Coworking Space Reservation Application");
        boolean operatingMenu = true;
        while (operatingMenu) {
            System.out.println("""
                    Choose one of the following options:
                     1. Admin Login
                     2. Customer Login
                     3. Exit""");
            try {
                switch (keyboard.nextLine()) {
                    case "1" -> Authorization.authorization(Authorization.ADMIN);
                    case "2" -> Authorization.authorization(Authorization.CUSTOMER);
                    default -> operatingMenu = false;
                }
            } catch (AuthorizationException | InputException e) {
                System.out.println(e.getMessage());
            }
        }
        ProgramState.atProgramStop();
    }

}