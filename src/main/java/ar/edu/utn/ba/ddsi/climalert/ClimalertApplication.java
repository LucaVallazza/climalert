package ar.edu.utn.ba.ddsi.climalert;

import ar.edu.utn.ba.ddsi.climalert.services.WeatherService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClimalertApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClimalertApplication.class, args);
	}

	@Bean
	CommandLineRunner printClima(WeatherService weatherService) {
		return args -> {
			try {
				System.out.println(weatherService.obtenerClimaActual());
			} catch (Exception exception) {
				System.out.println("No se pudo obtener el clima: " + exception.getMessage());
			}
		};
	}

}
