package app.todo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class UpInTheAir2 {
    public static void main(String[] args) {
        UpInTheAir1 todoApp = new UpInTheAir1();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("옵션을 선택해주세요: ");
            String option = scanner.nextLine();
            if (option.equals("exit")) {
                break;
            } else if (option.equals("0")) {
                System.out.println(todoApp.getTodoList());
                continue;
            }

            System.out.print("제목을 말씀해주세요: ");
            String title = scanner.nextLine();
            if (option.equals("1")) {
                System.out.print("마감일을 말씀해주세요: ");
                int[] ymd = Arrays.stream(scanner.nextLine().split("-"))
                        .mapToInt(Integer::parseInt)
                        .toArray();

                todoApp.registerTodo(title, LocalDate.of(ymd[0], ymd[1], ymd[2]));
            } else if (option.equals("2")) {
                todoApp.searchTodo(title);
            } else if (option.equals("3")) {
                System.out.print("새로운 마감일을 말씀해주세요: ");
                int[] ymd = Arrays.stream(scanner.nextLine().split("-"))
                        .mapToInt(Integer::parseInt)
                        .toArray();

                todoApp.modifyTodo(title, LocalDate.of(ymd[0], ymd[1], ymd[2]));
            } else if (option.equals("4")) {
                todoApp.deleteTodo(title);
            } else if (option.equals("5")) {
                todoApp.saveTodo();
            } else if (option.equals("6")) {
                todoApp.loadTodo();
            }
        }
    }
}
