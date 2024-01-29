package notebook.controller;

import notebook.model.User;
import notebook.model.repository.GBRepository;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class UserController {
    private static GBRepository repository;

    public UserController(GBRepository repository) {
        UserController.repository = repository;
    }

    public void saveUser(User user) {
        repository.create(user);
    }

    // HM SEM 5
    public static User createUser() {
        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");
        return new User(firstName, lastName, phone);
    }

    public static String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    public User readUser(Long userId) throws Exception {
        List<User> users = repository.findAll();
        for (User user : users) {
            if (Objects.equals(user.getId(), userId)) {
                return user;
            }
        }

        throw new RuntimeException("User not found");
    }

    public void updateUser(Long userId, User update) {
        update.setId(userId);
        repository.update(userId, update);
    }

    public static List<User> readAll() {
        return repository.findAll();
    }

    // HM SEM 5
    public boolean deletUser(Long id) {
        return repository.delete(id);
    }

}
