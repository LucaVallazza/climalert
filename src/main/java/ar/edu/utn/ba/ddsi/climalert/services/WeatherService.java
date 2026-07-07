package ar.edu.utn.ba.ddsi.climalert.services;

import ar.edu.utn.ba.ddsi.climalert.client.WeatherApiClient;
import ar.edu.utn.ba.ddsi.climalert.models.entities.Analizador;
import ar.edu.utn.ba.ddsi.climalert.models.entities.Clima;
import ar.edu.utn.ba.ddsi.climalert.models.records.Lugar;
import ar.edu.utn.ba.ddsi.climalert.dtos.WeatherApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;

@Service
public class WeatherService {

	private ArrayList<Analizador> analizadores;



	public void analizarClimas() {
		analizadores.stream().forEach(analizador -> analizador.reportarClima());

	}

}
