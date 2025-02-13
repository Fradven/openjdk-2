package repositories;

import entities.Todo;
import factories.ResultSetTodoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TodoRepository {
    private final Connection connection;

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

    public List<Todo> findByStatus(boolean isDone) throws Exception {
        List<Todo> todos = new ArrayList<>();

        PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM todos WHERE is_done = ?");
        statement.setBoolean(1, isDone);
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
        if (!resultSet.next()) {
            return null;
        }

        return new ResultSetTodoFactory(resultSet).createTodo();
    }

    public Boolean save(Todo todo) throws Exception {
        return todo.getId() == null ? this.store(todo) : this.update(todo);
    }

    private Boolean store(Todo todo) throws Exception {
        PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO todos (title, is_done) VALUES (?, ?)"
        );

        statement.setString(1, todo.getTitle());
        statement.setBoolean(2, todo.getDone());

        return statement.executeUpdate() > 0;
    }

    private Boolean update(Todo todo) throws Exception {
        PreparedStatement statement = this.connection.prepareStatement(
                "UPDATE todos SET title = ?, is_done = ? WHERE id = ?"
        );

        statement.setString(1, todo.getTitle());
        statement.setBoolean(2, todo.getDone());
        statement.setInt(3, todo.getId());

        return statement.executeUpdate() > 0;
    }

    public Boolean destroy(Integer id) throws Exception {
        PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM todos WHERE id = ?"
        );

        statement.setInt(1, id);
        return statement.executeUpdate() > 0;
    }
}
