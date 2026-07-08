package ar.edu.utn.ba.ddsi.climalert;

import ar.edu.utn.ba.ddsi.climalert.client.WeatherApiClient;
import ar.edu.utn.ba.ddsi.climalert.models.entities.Alertador;
import ar.edu.utn.ba.ddsi.climalert.models.entities.AlertadorMail;
import ar.edu.utn.ba.ddsi.climalert.models.entities.Analizador;
import ar.edu.utn.ba.ddsi.climalert.models.entities.CriterioAlarma;
import ar.edu.utn.ba.ddsi.climalert.models.entities.CriterioMayorTemperaturaHumedad;
import ar.edu.utn.ba.ddsi.climalert.models.entities.Suscriptor;
import ar.edu.utn.ba.ddsi.climalert.services.MailService;
import ar.edu.utn.ba.ddsi.climalert.services.WeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class ClimalertApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClimalertApplication.class, args);
	}

	@Bean
	public WeatherApiClient weatherApiClient(@Value("${WEATHER_API_KEY:}") String apiKey) {
		return new WeatherApiClient(apiKey);
	}

	@Bean
	public Analizador analizadorBuenosAires(WeatherApiClient weatherApiClient, WeatherService weatherService, MailService mailService) {
		ArrayList<Alertador> alertadores = new ArrayList<>(List.of(
				new AlertadorMail("admin@clima.com", mailService),
				new AlertadorMail("emergencias@clima.com", mailService),
				new AlertadorMail("meteorologia@clima.com", mailService)
		));
		ArrayList<CriterioAlarma> criterios = new ArrayList<>(List.of(
				new CriterioMayorTemperaturaHumedad(35.0, 60.0)
		));
		Suscriptor suscriptor = new Suscriptor(criterios, alertadores);

		Analizador analizador = new Analizador("Buenos Aires", new ArrayList<>(List.of(suscriptor)), weatherApiClient);
		weatherService.agregarAnalizador(analizador);
		return analizador;
	}

}
