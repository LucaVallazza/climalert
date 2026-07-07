package ar.edu.utn.ba.ddsi.climalert.models.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SuscriptorTest {

    @Mock
    private CriterioAlarma criterioAlarma;

    @Mock
    private Alertador alertador;

    private final Clima clima = new Clima("CABA", -34.6, -58.4, 36.0, 61.0);

    @Test
    void alertaATodosLosAlertadoresCuandoSeCumplenTodosLosCriterios() {
        when(criterioAlarma.esAlarmante(clima)).thenReturn(true);
        when(criterioAlarma.crearMensajeAlerta(clima)).thenReturn("temperatura y humedad altas");

        Suscriptor suscriptor = new Suscriptor(
                new ArrayList<>(java.util.List.of(criterioAlarma)),
                new ArrayList<>(java.util.List.of(alertador))
        );

        suscriptor.notificarClima(clima);

        verify(alertador, times(1)).enviarAlerta("temperatura y humedad altas");
    }

    @Test
    void noAlertaCuandoNoSeCumpleAlgunCriterio() {
        when(criterioAlarma.esAlarmante(clima)).thenReturn(false);

        Suscriptor suscriptor = new Suscriptor(
                new ArrayList<>(java.util.List.of(criterioAlarma)),
                new ArrayList<>(java.util.List.of(alertador))
        );

        suscriptor.notificarClima(clima);

        verify(alertador, never()).enviarAlerta(any());
    }

    @Test
    void noAlertaCuandoNoHayCriteriosQueSeCumplanEntreVarios() {
        CriterioAlarma otroCriterio = mock(CriterioAlarma.class);
        when(criterioAlarma.esAlarmante(clima)).thenReturn(true);
        when(otroCriterio.esAlarmante(clima)).thenReturn(false);

        Suscriptor suscriptor = new Suscriptor(
                new ArrayList<>(java.util.List.of(criterioAlarma, otroCriterio)),
                new ArrayList<>(java.util.List.of(alertador))
        );

        suscriptor.notificarClima(clima);

        verify(alertador, never()).enviarAlerta(any());
    }
}
