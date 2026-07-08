package ar.edu.utn.ba.ddsi.climalert.services;

import ar.edu.utn.ba.ddsi.climalert.client.WeatherApiClient;
import ar.edu.utn.ba.ddsi.climalert.models.entities.Clima;
import ar.edu.utn.ba.ddsi.climalert.models.repositories.ClimaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;

@Service
public class WeatherService {

	private final ArrayList<String> lugares;
	private final WeatherApiClient weatherApiClient;
	private final ClimaRepository climaRepository;

	public WeatherService(WeatherApiClient weatherApiClient, ClimaRepository climaRepository) {
		this.lugares = new ArrayList<>();
		this.weatherApiClient = weatherApiClient;
		this.climaRepository = climaRepository;
	}

	public void agregarLugar(String lugar) {
		lugares.add(lugar);
	}

	@Scheduled(fixedRate = 300000)
	public void obtenerYGuardarClimas() {
		System.out.println("[" + LocalTime.now().withNano(0) + "] arranca el fetch de los " + lugares.size() + " lugares");
		lugares.stream().forEach(lugar -> guardarClima(weatherApiClient.obtenerClima(lugar)));
		System.out.println("[" + LocalTime.now().withNano(0) + "] termino el fetch, ya deberian estar guardados");
	}

	public void guardarClima(Clima clima){
		climaRepository.guardar(clima);
	}

}
