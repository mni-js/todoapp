package app.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class TodoApplication implements CommandLineRunner {

    @Autowired
    private View view;

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

    @Override
    public void run(String... args) throws SQLException {
        while (true) {
            int option = view.choiceOption();

            if (option == 5) {
                System.out.println("프로그램을 종료합니다");
                break;
            }
            view.executeProgram(option);
        }
    }
}
