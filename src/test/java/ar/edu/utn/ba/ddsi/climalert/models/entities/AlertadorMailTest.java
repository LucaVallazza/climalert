package ar.edu.utn.ba.ddsi.climalert.models.entities;

import ar.edu.utn.ba.ddsi.climalert.services.MailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AlertadorMailTest {

    @Mock
    private MailService mailService;

    @Test
    void enviarAlertaDelegaEnMailServiceConElMailYElMensaje() {
        AlertadorMail alertador = new AlertadorMail("admin@clima.com", mailService);

        alertador.enviarAlerta("temperatura y humedad altas");

        verify(mailService).enviarMail("admin@clima.com", "temperatura y humedad altas");
    }
}
