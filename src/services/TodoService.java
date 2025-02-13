package services;

import repositories.TodoRepository;

import java.sql.Connection;
import java.util.Scanner;

public class TodoService {
    private TodoRepository todoRepository;
    private Scanner scanner;

    public TodoService(Connection connection) {
        this.todoRepository = new TodoRepository(connection);
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() throws Exception {
        while (true) {
            System.out.println("MENU TODO LIST");
            System.out.println("1 - Afficher les tâches à faire");
            System.out.println("2 - Afficher les tâches terminées");
            System.out.println("3 - Ajouter une tâche");
            System.out.println("4 - Modifier une tâche");
            System.out.println("5 - Supprimer une tâche");
            System.out.println("6 - Quitter");

            System.out.print("Votre choix : ");
            String choix = scanner.nextLine();

            switch (choix) {
                default: System.out.println("Votre action n’est pas autorisée !");
            }
        }
    }
}
