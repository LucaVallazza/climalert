package ar.edu.utn.ba.ddsi.climalert.models.entities;

import ar.edu.utn.ba.ddsi.climalert.client.WeatherApiClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnalizadorTest {

    @Mock
    private WeatherApiClient weatherApiClient;

    @Mock
    private Suscriptor suscriptor;

    @Test
    void reportarClimaConsultaElClimaDelLugarYNotificaATodosLosSuscriptores() {
        Clima clima = new Clima("CABA", -34.6, -58.4, 36.0, 61.0);
        when(weatherApiClient.obtenerClima("CABA")).thenReturn(clima);

        Analizador analizador = new Analizador(
                "CABA",
                new ArrayList<>(List.of(suscriptor)),
                weatherApiClient
        );

        analizador.reportarClima();

        verify(suscriptor).notificarClima(clima);
    }
}
