package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        ////////////////////////  OK  //////////////////////
        System.out.println("Создание таблицы");
        usi.createUsersTable();
        System.out.println();
        ////////////////////////  OK  //////////////////////
        System.out.println("Добавление 4 юзеров в таблицу");
        for (int i = 0; i < 4; i++) {
            usi.saveUser("New", "Test", (byte) 20);
        }

        ////////////////////////  OK  //////////////////////
        System.out.println("Получение всех данных из таблицы");
        usi.getAllUsers();
        System.out.println();

        ////////////////////////  OK  //////////////////////
        System.out.println("удаление user с id = n");
        usi.removeUserById(20);
        usi.getAllUsers();
        System.out.println();

        ////////////////////////  OK  //////////////////////
        System.out.println("Отчистка таблицы");
        usi.cleanUsersTable();
        usi.getAllUsers();

        ////////////////////////  OK  //////////////////////
        System.out.println("Удаление таблицы");
        usi.dropUsersTable();
        System.out.println();
    }

    private static final UserService usi = new UserServiceImpl();
}
