package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService us = new UserServiceImpl();
        us.createUsersTable();
        us.saveUser("Ivan", "Ivanov", (byte) 10);
        us.saveUser("Petr", "Petrov", (byte) 5);
        us.saveUser("Vasiliy", "Vasil`ev", (byte) 15);
        us.saveUser("Bulat", "Batullin", (byte) 27);
        us.getAllUsers();
        us.cleanUsersTable();
        us.dropUsersTable();
    }
}
