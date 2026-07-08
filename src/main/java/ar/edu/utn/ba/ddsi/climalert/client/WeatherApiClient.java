package ar.edu.utn.ba.ddsi.climalert.client;

import ar.edu.utn.ba.ddsi.climalert.dtos.WeatherApiResponse;
import ar.edu.utn.ba.ddsi.climalert.models.entities.Clima;
import ar.edu.utn.ba.ddsi.climalert.models.records.Lugar;
import org.springframework.web.client.RestClient;

import java.time.LocalTime;

public class WeatherApiClient {
    private String apiKey;
    private final RestClient restClient;


    public WeatherApiClient(String apiKey) {
        this(apiKey, RestClient.builder().baseUrl("http://api.weatherapi.com/v1").build());
    }

    // Visible para tests: permite inyectar un RestClient de prueba (MockRestServiceServer).
    WeatherApiClient(String apiKey, RestClient restClient) {
        this.apiKey = apiKey == null ? "" : apiKey.trim();
        this.restClient = restClient;
    }

    public Clima obtenerClima(String lugar) {
        System.out.println("[" + LocalTime.now().withNano(0) + "] pidiendole a weatherapi el clima de " + lugar + "...");

        WeatherApiResponse weatherApiResponse = restClient.get()
                .uri("/current.json?key={key}&q={q}&aqi=no", apiKey, lugar)
                .retrieve()
                .body(WeatherApiResponse.class);

        if (weatherApiResponse == null) {
            throw new IllegalStateException("No se pudo obtener el clima actual");
        }

        Lugar ubicacion = new Lugar(
                weatherApiResponse.location().name(),
                weatherApiResponse.location().lat(),
                weatherApiResponse.location().lon()
        );

        Clima clima = new Clima(
                ubicacion.nombre(),
                ubicacion.latitud(),
                ubicacion.longitud(),
                weatherApiResponse.current().temp_c(),
                weatherApiResponse.current().humidity() == null ? null : weatherApiResponse.current().humidity().doubleValue()
        );

        System.out.println("[" + LocalTime.now().withNano(0) + "] llego el clima de " + lugar + " -> " + clima.temperatura() + "c, " + clima.humidity() + "% humedad");

        return clima;
    }

}

