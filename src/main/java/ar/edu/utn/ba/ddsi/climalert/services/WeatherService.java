package ar.edu.utn.ba.ddsi.climalert.services;

import ar.edu.utn.ba.ddsi.climalert.client.WeatherApiClient;
import ar.edu.utn.ba.ddsi.climalert.models.entities.Clima;
import ar.edu.utn.ba.ddsi.climalert.models.records.Lugar;
import ar.edu.utn.ba.ddsi.climalert.dtos.WeatherApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class WeatherService {


	private WeatherApiClient weatherApiClient;

	public WeatherService(WeatherApiClient weatherApiClient) {
		this.weatherApiClient = weatherApiClient;
	}

	public void analizarClimas() {
		Clima climaActual = weatherApiClient.obtenerClima();
		if(climaActual.esAlarmante()){
			//alarmar!
    	}
	}
}
