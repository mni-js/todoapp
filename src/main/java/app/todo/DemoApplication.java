package app.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private MyRepository myRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws SQLException {
        List<Map<String, Object>> todos = myRepository.getAllTodos();
        System.out.println(todos);

        myRepository.insertTodo("New Title with pure JDBC", LocalDate.of(2024, 12, 30));

        Map<String, Object> todo = myRepository.getTodoById(1);
        System.out.println(todo);
    }
}
