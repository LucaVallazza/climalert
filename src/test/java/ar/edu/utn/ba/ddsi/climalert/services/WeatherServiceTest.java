package ar.edu.utn.ba.ddsi.climalert.services;

import ar.edu.utn.ba.ddsi.climalert.client.WeatherApiClient;
import ar.edu.utn.ba.ddsi.climalert.models.entities.Clima;
import ar.edu.utn.ba.ddsi.climalert.models.repositories.ClimaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    @Mock
    private WeatherApiClient weatherApiClient;

    @Mock
    private ClimaRepository climaRepository;

    @Test
    void guardarClimaDelegaEnElRepositorio() {
        WeatherService weatherService = new WeatherService(weatherApiClient, climaRepository);
        Clima clima = new Clima("Buenos Aires", -34.6, -58.4, 36.0, 61.0);

        weatherService.guardarClima(clima);

        verify(climaRepository).guardar(clima);
    }

    @Test
    void obtenerYGuardarClimasConsultaYGuardaElClimaDeCadaLugarRegistrado() {
        WeatherService weatherService = new WeatherService(weatherApiClient, climaRepository);
        Clima clima = new Clima("Buenos Aires", -34.6, -58.4, 36.0, 61.0);
        when(weatherApiClient.obtenerClima("Buenos Aires")).thenReturn(clima);

        weatherService.agregarLugar("Buenos Aires");
        weatherService.obtenerYGuardarClimas();

        verify(climaRepository).guardar(clima);
    }
}
