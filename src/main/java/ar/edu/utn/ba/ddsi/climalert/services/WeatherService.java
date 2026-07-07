package ar.edu.utn.ba.ddsi.climalert.services;

import ar.edu.utn.ba.ddsi.climalert.models.entities.Analizador;
import ar.edu.utn.ba.ddsi.climalert.models.entities.Clima;
import ar.edu.utn.ba.ddsi.climalert.models.repositories.ClimaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WeatherService {

	private final ArrayList<Analizador> analizadores;
	private final ClimaRepository climaRepository;

	public WeatherService(ClimaRepository climaRepository) {
		this.analizadores = new ArrayList<>();
		this.climaRepository = climaRepository;
	}

	public void analizarClimas() {
		analizadores.stream().forEach(analizador -> analizador.reportarClima());

	}

	public void guardarClima(Clima clima){
		climaRepository.guardar(clima);
	}

}
