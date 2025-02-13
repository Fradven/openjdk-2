package factories;

import entities.Todo;
import interfaces.factories.TodoFactory;

import java.sql.ResultSet;

public class ResultSetTodoFactory implements TodoFactory {
    private ResultSet resultSet;

    public ResultSetTodoFactory(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    @Override
    public Todo createTodo() throws Exception {
        Integer id = this.resultSet.getInt("id");
        String title = this.resultSet.getString("title");
        Boolean isDone = this.resultSet.getBoolean("is_done");

        return new Todo(title, isDone, id);
    }
}
