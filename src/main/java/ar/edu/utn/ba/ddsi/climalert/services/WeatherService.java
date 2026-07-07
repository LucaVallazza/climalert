package ar.edu.utn.ba.ddsi.climalert.services;

import ar.edu.utn.ba.ddsi.climalert.models.entities.Clima;
import ar.edu.utn.ba.ddsi.climalert.models.records.Lugar;
import ar.edu.utn.ba.ddsi.climalert.dtos.WeatherApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class WeatherService {

	public void analizarClimaActual() {
		Clima climaActual = obtenerClimaActual();
		if(climaActual.esAlarmante()){
			//alarmar!
    	}
	}
}
