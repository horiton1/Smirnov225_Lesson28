package task1;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class Practicum {

    static void main() throws IOException, InterruptedException {
        URI uri = URI.create("https://ya.ru/white");

        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
        HttpResponse.BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response;

        try(HttpClient client = HttpClient.newHttpClient()){
            response = client.send(request, handler);
        }

        System.out.println("Код состояния: " + response.statusCode());
        System.out.println("Тело ответа: " + response.body());
    }
}