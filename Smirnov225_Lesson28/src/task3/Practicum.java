package task3;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Practicum {
    static void main() {
        int requestedStatus = 200;

        URI uri = URI.create("http://httpbin.org/status/" + requestedStatus);
        HttpRequest request = HttpRequest.newBuilder().GET().uri(uri).build();
        HttpResponse<String> response;

        try(HttpClient client = HttpClient.newHttpClient()) {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            switch (statusCode) {
                case 400:
                    System.out.println("В запросе содержится ошибка. Проверьте параметры и повторите запрос.");
                    break;
                case 404:
                    System.out.println("По указанному адресу нет ресурса. Проверьте URL-адрес ресурса и повторите запрос.");
                    break;
                case 500:
                    System.out.println("На стороне сервера произошла непредвиденная ошибка.");
                    break;
                case 503:
                    System.out.println("Сервер временно недоступен. Попробуйте повторить запрос позже.");
                    break;
                default:
                    System.out.println("Код состояния: " + statusCode);
                    System.out.println("Тело ответа: " + response.body());
                    break;
            }
        }
        catch (IOException | InterruptedException e) {
            System.out.println("Во время выполнения запроса ресурса по url-адресу: '" + uri + "' возникла ошибка.\n" +
                    "Проверьте, пожалуйста, адрес и повторите попытку.");
        }
    }
}