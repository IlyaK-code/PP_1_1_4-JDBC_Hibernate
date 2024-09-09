package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Gosha", "Kichenko", (byte) 20);
        userService.saveUser("Ilya", "Kriulyak", (byte) 22);
        userService.saveUser("Artyr", "Pirozhkov", (byte) 40);
        userService.saveUser("Ron", "Ginger", (byte) 45);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
        System.out.println("All");

    }
}
