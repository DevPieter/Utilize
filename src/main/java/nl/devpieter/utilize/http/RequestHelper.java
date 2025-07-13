package nl.devpieter.utilize.http;

import nl.devpieter.utilize.Utilize;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class RequestHelper {

    public static final String USER_AGENT_HEADER = "User-Agent";

    protected static final HttpClient CLIENT = HttpClient.newHttpClient();

    public static @NotNull HttpResponse<String> simpleGet(@NotNull URI uri) throws IOException, InterruptedException {
        HttpRequest request = createRequestBuilder(uri).GET().build();
        return CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static @NotNull HttpResponse<String> simplePost(@NotNull URI uri, @NotNull HttpRequest.BodyPublisher bodyPublisher) throws IOException, InterruptedException {
        HttpRequest request = createRequestBuilder(uri).POST(bodyPublisher).build();
        return CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static @NotNull HttpResponse<String> simplePut(@NotNull URI uri, @NotNull HttpRequest.BodyPublisher bodyPublisher) throws IOException, InterruptedException {
        HttpRequest request = createRequestBuilder(uri).PUT(bodyPublisher).build();
        return CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static @NotNull HttpResponse<String> simpleDelete(@NotNull URI uri) throws IOException, InterruptedException {
        HttpRequest request = createRequestBuilder(uri).DELETE().build();
        return CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static HttpRequest.Builder createRequestBuilder(@NotNull URI uri) {
        return HttpRequest.newBuilder()
                .header(USER_AGENT_HEADER, Utilize.getInstance().getUserAgent())
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .uri(uri);
    }
}