package ar.edu.utn.ba.ddsi.climalert.client;

import ar.edu.utn.ba.ddsi.climalert.models.entities.Clima;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestToUriTemplate;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import org.springframework.http.HttpMethod;

class WeatherApiClientTest {

    @Test
    void obtenerClimaMapeaLaRespuestaDeWeatherApiAUnClima() {
        RestClient.Builder builder = RestClient.builder().baseUrl("http://api.weatherapi.com/v1");
        MockRestServiceServer server = MockRestServiceServer.bindTo(builder).build();
        RestClient restClient = builder.build();
        WeatherApiClient client = new WeatherApiClient("test-key", restClient);

        String jsonRespuesta = """
                {
                  "location": { "name": "CABA", "lat": -34.6, "lon": -58.4 },
                  "current": { "temp_c": 36.5, "humidity": 61 }
                }
                """;

        server.expect(requestToUriTemplate(
                        "http://api.weatherapi.com/v1/current.json?key={key}&q={q}&aqi=no", "test-key", "CABA"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonRespuesta, MediaType.APPLICATION_JSON));

        Clima clima = client.obtenerClima("CABA");

        assertThat(clima.ubicacion()).isEqualTo("CABA");
        assertThat(clima.latitud()).isEqualTo(-34.6);
        assertThat(clima.longitud()).isEqualTo(-58.4);
        assertThat(clima.temperatura()).isEqualTo(36.5);
        assertThat(clima.humidity()).isEqualTo(61.0);
        server.verify();
    }

    @Test
    void siLaRespuestaEsVaciaLanzaExcepcion() {
        RestClient.Builder builder = RestClient.builder().baseUrl("http://api.weatherapi.com/v1");
        MockRestServiceServer server = MockRestServiceServer.bindTo(builder).build();
        RestClient restClient = builder.build();
        WeatherApiClient client = new WeatherApiClient("test-key", restClient);

        server.expect(requestToUriTemplate(
                        "http://api.weatherapi.com/v1/current.json?key={key}&q={q}&aqi=no", "test-key", "CABA"))
                .andRespond(withServerError());

        assertThrows(Exception.class, () -> client.obtenerClima("CABA"));
    }
}
