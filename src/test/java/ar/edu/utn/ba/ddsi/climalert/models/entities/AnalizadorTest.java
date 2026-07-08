package ar.edu.utn.ba.ddsi.climalert.models.entities;

import ar.edu.utn.ba.ddsi.climalert.client.WeatherApiClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnalizadorTest {

    @Mock
    private WeatherApiClient weatherApiClient;

    @Mock
    private Suscriptor suscriptor;

    @Test
    void reportarClimaConsultaElClimaDelLugarLoDevuelveYNotificaATodosLosSuscriptores() {
        Clima clima = new Clima("Buenos Aires", -34.6, -58.4, 36.0, 61.0);
        when(weatherApiClient.obtenerClima("Buenos Aires")).thenReturn(clima);

        Analizador analizador = new Analizador(
                "Buenos Aires",
                new ArrayList<>(List.of(suscriptor)),
                weatherApiClient
        );

        Clima climaReportado = analizador.reportarClima();

        assertThat(climaReportado).isEqualTo(clima);
        verify(suscriptor).notificarClima(clima);
    }
}
