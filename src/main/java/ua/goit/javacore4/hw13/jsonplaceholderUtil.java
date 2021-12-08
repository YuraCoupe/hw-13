package ua.goit.javacore4.hw13;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class jsonplaceholderUtil {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();

    public static JPHUser createUser(URI uri, JPHUser user) throws IOException, InterruptedException {
        final String requestBody = GSON.toJson(user);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "application/json")
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(), JPHUser.class);
    }

    public static JPHUser updateUser(URI uri, JPHUser user) throws IOException, InterruptedException {
        final String requestBody = GSON.toJson(user);
        URI newUri = URI.create(String.format("%s/%d", uri.toString(), user.getId()));
        HttpRequest request = HttpRequest.newBuilder()
                .uri(newUri)
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "application/json")
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(), JPHUser.class);
    }

    public static void deleteUser(URI uri, int userId) throws IOException, InterruptedException {
        URI newUri = URI.create(String.format("%s/%d", uri.toString(), userId));
        HttpRequest request = HttpRequest.newBuilder()
                .uri(newUri)
                .DELETE()
                .header("Content-type", "application/json")
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static List<JPHUser> getUsers(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .header("Content-type", "application/json")
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        List<JPHUser> users = GSON.fromJson(response.body(), new TypeToken<List<JPHUser>>() {
        }.getType());
        return users;
    }

    public static JPHUser getUserById(URI uri, int userId) throws IOException, InterruptedException {
        URI newUri = URI.create(String.format("%s/%d", uri.toString(), userId));
        HttpRequest request = HttpRequest.newBuilder()
                .uri(newUri)
                .GET()
                .header("Content-type", "application/json")
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        JPHUser user = GSON.fromJson(response.body(), JPHUser.class);
        return user;
    }

    public static JPHUser getUserbyUsername(URI uri, String username) throws IOException, InterruptedException {
        URI newUri = URI.create(String.format("%s?username=%s", uri.toString(), username));
        HttpRequest request = HttpRequest.newBuilder()
                .uri(newUri)
                .GET()
                .header("Content-type", "application/json")
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        List<JPHUser> users = GSON.fromJson(response.body(), new TypeToken<List<JPHUser>>() {
        }.getType());
        return users.get(0);
    }
}
