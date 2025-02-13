package services;

import entities.Todo;
import repositories.TodoRepository;

import java.sql.Connection;
import java.util.List;
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
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    showTodos(false);
                    break;
                case "2":
                    showTodos(true);
                    break;
                case "3":
                    addTodo();
                    break;
                default: System.out.println("Votre action n’est pas autorisée !");
            }
        }
    }

    private void showTodos(boolean isDone) {
        try {
            List<Todo> todos = todoRepository.findAll();
            System.out.println("\n📜 Liste des tâches " + (isDone ? "faites" : "à faire") + " :");
            for (Todo todo : todos) {
                if (isDone) {
                    if (todo.getDone()) {
                        System.out.println(todo.getId() + ": " + todo.getTitle());
                    }
                }
                else {
                    System.out.println(todo.getId() + ": " + todo.getTitle());
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des tâches : " + e.getMessage());
        }
    }

    private void addTodo() {
        try {
            System.out.print("Titre de la tâche : ");
            String title = scanner.nextLine();

            System.out.print("La tâche est-elle terminée ? (true/false) : ");
            boolean isDone = Boolean.parseBoolean(scanner.nextLine());

            System.out.print("🔹 Confirmer l'ajout de la tâche \"" + title + "\" (" + isDone + ") ? (oui/non) : ");
            if (scanner.nextLine().equalsIgnoreCase("oui")) {
                Todo todo = new Todo(title, isDone, null);
                if (todoRepository.save(todo)) {
                    System.out.println("La tâche a bien été créée !");
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
