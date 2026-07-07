package ar.edu.utn.ba.ddsi.climalert.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class MailServiceTest {

    private final PrintStream stdOutOriginal = System.out;
    private final ByteArrayOutputStream capturado = new ByteArrayOutputStream();

    @BeforeEach
    void redirigirSalida() {
        System.setOut(new PrintStream(capturado));
    }

    @AfterEach
    void restaurarSalida() {
        System.setOut(stdOutOriginal);
    }

    @Test
    void enviarMailNoTieneEnvioRealTodaviaYSoloLoguea() {
        // Este test documenta el estado actual (stub): no hay integración real de correo.
        // Ver TODO de la conversación: falta reemplazar esto por un envío real (JavaMailSender).
        new MailService().enviarMail("admin@clima.com", "temperatura y humedad altas");

        assertThat(capturado.toString())
                .contains("admin@clima.com")
                .contains("temperatura y humedad altas");
    }
}
