package ar.edu.utn.ba.ddsi.climalert.models.entities;

import ar.edu.utn.ba.ddsi.climalert.models.repositories.ClimaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnalizadorTest {

    @Mock
    private ClimaRepository climaRepository;

    @Mock
    private Suscriptor suscriptor;

    @Test
    void analizarNotificaATodosLosSuscriptoresConElUltimoClimaGuardadoParaSuLugar() {
        Clima clima = new Clima("Buenos Aires", -34.6, -58.4, 36.0, 61.0);
        when(climaRepository.obtenerUltimo("Buenos Aires")).thenReturn(Optional.of(clima));

        Analizador analizador = new Analizador(
                "Buenos Aires",
                new ArrayList<>(List.of(suscriptor)),
                climaRepository
        );

        analizador.analizar();

        verify(suscriptor).notificarClima(clima);
    }

    @Test
    void siNoHayClimaGuardadoParaSuLugarNoNotificaANadie() {
        when(climaRepository.obtenerUltimo("Buenos Aires")).thenReturn(Optional.empty());

        Analizador analizador = new Analizador(
                "Buenos Aires",
                new ArrayList<>(List.of(suscriptor)),
                climaRepository
        );

        analizador.analizar();

        verify(suscriptor, never()).notificarClima(org.mockito.ArgumentMatchers.any());
    }
}
