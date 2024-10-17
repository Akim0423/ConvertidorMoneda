import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
 
public class ApiMoneda {
    public Moneda buscarMoneda(String base, String cambio) {
        URI direccion =URI.create("https://v6.exchangerate-api.com/v6/d51691cc61aadbfe27e1029a/pair/"+base+"/"+cambio);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();
        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Moneda.class);
        } catch (Exception e) {
            throw new RuntimeException("No se encontro moneda"+e.getMessage());
        }
    }
}
