package task2;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Practicum {
    static void main() {
        String url = "https://www.ya.ru/";

        URI uri = URI.create(url);

        HttpRequest request = HttpRequest.newBuilder().GET().uri(uri).build();
        HttpResponse<String> response;

        try(HttpClient client = HttpClient.newHttpClient()) {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Код состояния: " + response.statusCode());
            System.out.println("Тело ответа: " + response.body());
        }
        catch (IllegalArgumentException _){
            System.out.println("Введённый вами адрес не соответствует формату URL. Попробуйте, пожалуйста, снова");
        }
        catch (IOException | InterruptedException _){
            System.out.println("Во время выполнения запроса возникла ошибка." +
                    " Проверьте, пожалуйста, URL-адрес и повторите попытку.");
        }
    }
}