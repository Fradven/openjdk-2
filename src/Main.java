import entities.Todo;
import repositories.TodoRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://database:3306/app", "user", "password");
            TodoRepository todoRepository = new TodoRepository(connection);
            List<Todo> todos = todoRepository.findAll();

            Todo todo = new Todo("testing", false, null);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}