package app.todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class UpInTheAir1 {
    private Map<String, LocalDate> todoList = new HashMap<>();

    public void registerTodo(String title, LocalDate deadLine) {
        todoList.put(title, deadLine);
    }

    public void searchTodo(String title) {
        if (todoList.containsKey(title)) {
            System.out.println(todoList.get(title));
        } else {
            System.out.println("존재하지 않는 제목입니다.");
        }
    }

    public void modifyTodo(String title, LocalDate newDeadLine) {
        todoList.replace(title, newDeadLine);
    }

    public void deleteTodo(String title) {
        todoList.remove(title);
    }

    public Map<String, LocalDate> getTodoList() {
        return todoList;
    }

    public void saveTodo() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\jseom\\Desktop\\todo.txt"));
            bw.write(todoList.toString());
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadTodo() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\jseom\\Desktop\\todo.txt"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
