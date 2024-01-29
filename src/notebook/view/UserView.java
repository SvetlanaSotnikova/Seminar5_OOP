package notebook.view;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.util.Commands;

public class UserView {
    private final UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void run() {
        Commands com;

        while (true) {
            String command = UserController.prompt("Введите команду: ");
            com = Commands.valueOf(command);
            if (com == Commands.EXIT)
                return;
            switch (com) {
                case CREATE:
                    User u = UserController.createUser();
                    userController.saveUser(u);
                    break;
                case READ:
                    String id = UserController.prompt("Идентификатор пользователя: ");
                    try {
                        User user = userController.readUser(Long.parseLong(id));
                        System.out.println(user);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case UPDATE:
                    Long userId = Long.parseLong(UserController.prompt("Enter user id: "));
                    userController.updateUser(userId, UserController.createUser());
                    break;
                case DELETE: // HM SEM 5
                    Long deleteId = Long.parseLong(UserController.prompt("Enter user id: "));
                    boolean deleted = userController.deletUser(deleteId);
                    if (deleted) {
                        System.out.println("Успешно удален пользователь с id:" + deleteId);
                    } else {
                        System.out.println("Пользователь с id:" + deleteId + " не найден");
                    }
                    break;
                case LIST:
                    System.out.println(UserController.readAll());
                    break;
                default:
                    break;
            }
        }
    }
}