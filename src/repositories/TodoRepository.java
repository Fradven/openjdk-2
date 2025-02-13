package repositories;

import entities.Todo;
import factories.ResultSetTodoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TodoRepository {
    private Connection connection;

    public TodoRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Todo> findAll() throws Exception {
        List<Todo> todos = new ArrayList<>();

        PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM todos");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Todo todo = new ResultSetTodoFactory(resultSet).createTodo();
            todos.add(todo);
        }

        return todos;
    }

    public Todo findById(Integer id) throws Exception {
        PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM todos WHERE id = ?");
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        resultSet.next();

        return new ResultSetTodoFactory(resultSet).createTodo();
    }

    public Boolean save(Todo todo) throws Exception {
        return todo.getId() == null
                ? this.store(todo)
                : this.update(todo);
    }

    private Boolean store(Todo todo) throws Exception {
        PreparedStatement statement = this.connection.prepareStatement("INSERT INTO todos (last_name, first_name, email, password) VALUES (?, ?)");

        statement.setString(1, todo.getTitle());
        statement.setBoolean(2, todo.getDone());

        return statement.executeUpdate() > 0;
    }

    private Boolean update(Todo todo) throws Exception {
        // TODO
        return true;
    }

    public Boolean destroy(Todo todo) throws Exception {
        // TODO
        return true;
    }
}