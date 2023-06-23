package org.example.task1;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UsersTest {
    private static final HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
    private static final String url = "https://jsonplaceholder.typicode.com/users";
    public static void main(String[] args) throws IOException, InterruptedException {
        createUser("{ \"id\": 11, \"name\": \"Line \", \"username\": \"Anne\", \"email\": \"ann@april.biz\", \"address\": { \"street\": \"Baker street\", \"suite\": \"Apt. 5\", \"city\": \"London\", \"zipcode\": \"22430\", \"geo\": { \"uk\": \"-37.3159\", \"lng\": \"81.1496\" } }, \"phone\": \"1-770-736-8031 x56442\", \"website\": \"annorg.org\", \"company\": { \"name\": \"Orange-Juice\", \"catchPhrase\": \"Juicy juicy\", \"bs\": \"Market with drinks\" }}");
        updateUser(2);
        getUsers();
        deleteUser(1);
        getUserById(1);
        getUserByUsername("Bret");
    }
    public static void createUser(String requestBody) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            System.out.println("New user created successfully.");
            System.out.println(response.body());
        } else {
            System.out.println("Failed to create new user.");
            System.out.println(response.body());
        }
    }
    private static void updateUser(int id) throws IOException, InterruptedException {
        String requestBody = "{ \"name\": \"Update User \", \"username\": \"Username\", \"email\": \"update@april.biz\", \"address\": { \"street\": \"Update street\", \"suite\": \"update Apt.\", \"city\": \"Update city\", \"zipcode\": \"Update zipcode\", \"geo\": { \"uk\": \"-37.3159\", \"lng\": \"update lng\" } }, \"phone\": \"update phone\", \"website\": \"update website\", \"company\": { \"name\": \"Update name\", \"catchPhrase\": \"Update phrase\", \"bs\": \"update bs\" }}";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/" + id))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            System.out.println("User updated successfully.");
            System.out.println(response.body());
        } else {
            System.out.println("Failed to update user.");
            System.out.println(response.body());
        }
    }

    private static void deleteUser(int id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/" + id))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();

        HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            System.out.println("User deleted successfully.");
            System.out.println(response.statusCode());
        } else {
            System.out.println("Failed to delete user.");
            System.out.println(response.statusCode());
        }
    }

    private static void getUsers() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users"))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            System.out.println("Users:");
            System.out.println(response.body());
        } else {
            System.out.println("Failed to get users.");
            System.out.println(response.body());
        }
    }

    private static void getUserById(int id) throws IOException, InterruptedException {
        String url = "https://jsonplaceholder.typicode.com/users/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            System.out.println("User with ID " + id + ":");
            System.out.println(response.body());
        } else {
            System.out.println("Failed to get user with ID " + id + ".");
            System.out.println(response.body());
        }
    }

    private static void getUserByUsername(String username) throws IOException, InterruptedException {
        String url = "https://jsonplaceholder.typicode.com/users?username=" + username;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            System.out.println("User with username \"" + username + "\":");
            System.out.println(response.body());
        } else {
            System.out.println("Failed to get user with username \"" + username + "\".");
            System.out.println(response.body());
        }
    }
}
