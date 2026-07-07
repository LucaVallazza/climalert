package ar.edu.utn.ba.ddsi.climalert.models.entities;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CriterioMayorTemperaturaHumedadTest {

    private final CriterioMayorTemperaturaHumedad criterio =
            new CriterioMayorTemperaturaHumedad(35.0, 60.0);

    @Test
    void esAlarmanteCuandoTemperaturaYHumedadSuperanElUmbral() {
        Clima clima = new Clima("CABA", -34.6, -58.4, 36.0, 61.0);

        assertThat(criterio.esAlarmante(clima)).isTrue();
    }

    @Test
    void noEsAlarmanteCuandoSoloLaTemperaturaSuperaElUmbral() {
        Clima clima = new Clima("CABA", -34.6, -58.4, 40.0, 50.0);

        assertThat(criterio.esAlarmante(clima)).isFalse();
    }

    @Test
    void noEsAlarmanteCuandoSoloLaHumedadSuperaElUmbral() {
        Clima clima = new Clima("CABA", -34.6, -58.4, 20.0, 90.0);

        assertThat(criterio.esAlarmante(clima)).isFalse();
    }

    @Test
    void noEsAlarmanteCuandoNingunValorSuperaElUmbral() {
        Clima clima = new Clima("CABA", -34.6, -58.4, 20.0, 40.0);

        assertThat(criterio.esAlarmante(clima)).isFalse();
    }

    @Test
    void elMensajeDeAlertaIncluyeLosUmbralesConfigurados() {
        Clima clima = new Clima("CABA", -34.6, -58.4, 36.0, 61.0);

        String mensaje = criterio.crearMensajeAlerta(clima);

        assertThat(mensaje).contains("35.0").contains("60.0");
    }
}
