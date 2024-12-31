package app.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private MyRepository myRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("옵션을 선택해주세요. [0. 전체 확인 | 1. 추가 | 2. 검색 | 3. 변경 | 4. 삭제 | 5. 종료]");
            System.out.print("옵션: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 5) {
                break;
            }

            switch (option) {
                case 0:
                    List<Map<String, Object>> todos = myRepository.getAllTodos();
                    System.out.println(todos);
                    break;
                case 1:
                    System.out.print("추가할 제목: ");
                    String titleToAdd = scanner.nextLine();

                    System.out.print("추가할 데드라인: ");
                    LocalDate deadline = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);
                    myRepository.insertTodo(titleToAdd, deadline);
                    break;
                case 2:
                    System.out.print("검색할 제목: ");
                    String titleToSearch = scanner.nextLine();
                    System.out.println(myRepository.getTodoByTitle(titleToSearch));
                    break;
                case 3:
                    System.out.print("검색할 제목: ");
                    String titleToModifyDeadline = scanner.nextLine();

                    System.out.print("변경할 데드라인: ");
                    LocalDate newDeadline = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);
                    myRepository.modifyTodo(titleToModifyDeadline, newDeadline);
                    break;
                case 4:
                    System.out.print("삭제할 제목: ");
                    String titleToDelete = scanner.nextLine();
                    myRepository.deleteTodo(titleToDelete);
                    break;
                default:
                    System.out.println("올바른 옵션을 입력해주세요.");
                    break;
            }
        }
    }
}
