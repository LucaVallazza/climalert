package ar.edu.utn.ba.ddsi.climalert.models.entities;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ClimaTest {

    @Test
    void elDetalleIncluyeUbicacionTemperaturaYHumedad() {
        Clima clima = new Clima("CABA", -34.6, -58.4, 36.5, 61.2);

        String detalle = clima.detalle();

        assertThat(detalle)
                .contains("CABA")
                .contains("36.5")
                .contains("61.2");
    }
}
