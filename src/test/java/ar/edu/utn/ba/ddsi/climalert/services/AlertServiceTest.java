package ar.edu.utn.ba.ddsi.climalert.services;

import ar.edu.utn.ba.ddsi.climalert.models.entities.Analizador;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class AlertServiceTest {

    @Test
    void analizarClimasLeAvisaATodosLosAnalizadoresRegistrados() {
        AlertService alertService = new AlertService();
        Analizador analizador = mock(Analizador.class);

        alertService.agregarAnalizador(analizador);
        alertService.analizarClimas();

        verify(analizador).analizar();
    }
}
