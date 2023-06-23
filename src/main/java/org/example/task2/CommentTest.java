package org.example.task2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CommentTest {
    public static void main(String[] args) throws IOException {
        fetchAndSaveComments(1, 10);
    }

    public static void fetchAndSaveComments(int userId, int postNumber) throws IOException {
        String postsUrl = "https://jsonplaceholder.typicode.com/users/" + userId + "/posts";
        String postsResponse = Jsoup.connect(postsUrl).ignoreContentType(true).get().body().text();
        List<CommentItems> posts = new Gson().fromJson(postsResponse, new TypeToken<List<CommentItems>>(){}.getType());

        if (posts.isEmpty()) {
            System.out.println("No posts found for the user.");
            return;
        }

        String commentsUrl = "https://jsonplaceholder.typicode.com/posts/" + postNumber + "/comments";
        String commentsResponse = Jsoup.connect(commentsUrl).ignoreContentType(true).get().body().text();
        List<CommentItems> comments = new Gson().fromJson(commentsResponse, new TypeToken<List<CommentItems>>(){}.getType());

        if (comments.isEmpty()) {
            System.out.println("No comments found for the post.");
            return;
        }

        CommentItems lastComment = Collections.max(comments, Comparator.comparingInt(CommentItems::getId));
        String fileName = "user-" + userId + "-post-" + postNumber + "-comments.json";
        try (FileWriter writer = new FileWriter(fileName)) {
            new Gson().toJson(lastComment, writer);
            System.out.println("Comment saved to " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the comment: " + e.getMessage());
        }
    }
}
