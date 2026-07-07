package ar.edu.utn.ba.ddsi.climalert.services;

import ar.edu.utn.ba.ddsi.climalert.models.entities.Clima;
import ar.edu.utn.ba.ddsi.climalert.models.repositories.ClimaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    @Mock
    private ClimaRepository climaRepository;

    @Test
    void guardarClimaDelegaEnElRepositorio() {
        WeatherService weatherService = new WeatherService(climaRepository);
        Clima clima = new Clima("CABA", -34.6, -58.4, 36.0, 61.0);

        weatherService.guardarClima(clima);

        verify(climaRepository).guardar(clima);
    }

    @Test
    void analizarClimasSinAnalizadoresRegistradosNoFalla() {
        WeatherService weatherService = new WeatherService(climaRepository);

        weatherService.analizarClimas();
    }
}
