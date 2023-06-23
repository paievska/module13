package org.example.task3;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CompetedTaskTest {
    public static void main(String[] args) throws IOException {
        fetchAndPrintOpenTasks(1);
    }
    public static void fetchAndPrintOpenTasks(int userId) throws IOException {
        String url = "https://jsonplaceholder.typicode.com/users/" + userId + "/todos";
        String response = Jsoup.connect(url).ignoreContentType(true).get().body().text();
        List<CompletedTaskItems> tasks = new Gson().fromJson(response, new TypeToken<List<CompletedTaskItems>>(){}.getType());
        if (tasks.isEmpty()) {
            System.out.println("No tasks found for the user.");
            return;
        }
        List<CompletedTaskItems> openTasks = tasks.stream()
                .filter(task -> !task.isCompleted())
                .collect(Collectors.toList());

        if (openTasks.isEmpty()) {
            System.out.println("No open tasks found for the user.");
            return;
        }
        for (CompletedTaskItems task : openTasks) {
            System.out.println(task);
        }
    }
}
