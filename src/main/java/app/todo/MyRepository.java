package app.todo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MyRepository {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    public List<Map<String, Object>> getAllTodos() throws SQLException {
        List<Map<String, Object>> todos = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM todos")) {

            while (resultSet.next()) {
                Map<String, Object> todo = new HashMap<>();
                todo.put("id", resultSet.getInt("id"));
                todo.put("title", resultSet.getString("title"));
                todo.put("deadline", resultSet.getDate("deadline"));
                todos.add(todo);
            }
        }
        return todos;
    }

    public void insertTodo(String title, LocalDate deadline) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO todos (title, deadline) VALUES (?, ?)")) {

            preparedStatement.setString(1, title);
            preparedStatement.setDate(2, Date.valueOf(deadline));
            preparedStatement.executeUpdate();
        }
    }

    public Map<String, Object> getTodoByTitle(String title) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM todos WHERE title = ?")) {

            preparedStatement.setString(1, title);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Map<String, Object> todo = new HashMap<>();
                    todo.put("id", resultSet.getInt("id"));
                    todo.put("title", resultSet.getString("title"));
                    todo.put("deadline", resultSet.getDate("deadline"));
                    return todo;
                }
            }
        }
        return null;
    }

    public void modifyTodo(String title, LocalDate deadline) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE todos SET deadline = ? WHERE title = ?")) {

            preparedStatement.setDate(1, Date.valueOf(deadline));
            preparedStatement.setString(2, title);
            preparedStatement.executeUpdate();
        }
    }

    public void deleteTodo(String title) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM todos WHERE title = ?")) {

            preparedStatement.setString(1, title);
            preparedStatement.executeUpdate();
        }
    }
}
