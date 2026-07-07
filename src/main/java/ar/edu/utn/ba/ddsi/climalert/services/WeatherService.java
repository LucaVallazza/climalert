package ar.edu.utn.ba.ddsi.climalert.services;

import ar.edu.utn.ba.ddsi.climalert.models.entities.Clima;
import ar.edu.utn.ba.ddsi.climalert.models.records.Lugar;
import ar.edu.utn.ba.ddsi.climalert.dtos.WeatherApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class WeatherService {

	private final String apiKey;
	private final RestClient restClient;


	public WeatherService(@Value("${OPENWEATHER_API_KEY}") String apiKey) {
		this.apiKey = apiKey == null ? "" : apiKey.trim();
		this.restClient = RestClient.builder()
				.baseUrl("http://api.weatherapi.com/v1")
				.build();
	}

	public Clima obtenerClimaActual() {
		WeatherApiResponse weatherApiResponse = restClient.get()
				.uri("/current.json?key={key}&q=Buenos Aires&aqi=no", apiKey)
				.retrieve()
				.body(WeatherApiResponse.class);

		if (weatherApiResponse == null) {
			throw new IllegalStateException("No se pudo obtener el clima actual");
		}


		Lugar lugar = new Lugar(
				weatherApiResponse.location().name(),
				weatherApiResponse.location().lat(),
				weatherApiResponse.location().lon()
		);

		return new Clima(
				lugar.nombre(),
				lugar.latitud(),
				lugar.longitud(),
				weatherApiResponse.current().temp_c(),
				weatherApiResponse.current().humidity() == null ? null : weatherApiResponse.current().humidity().doubleValue()
		);
  }

	public boolean analizarClimaActual() {
		Clima climaActual = obtenerClimaActual();
		return climaActual.esAlarmante();
	}
}
