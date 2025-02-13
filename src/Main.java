import services.TodoService;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://database:3306/app", "user", "password");
            TodoService todoService = new TodoService(connection);
            todoService.showMenu();
        } catch (Exception e) {
            System.out.println("Erreur de connexion à la base de données : " + e.getMessage());
        }
    }
}