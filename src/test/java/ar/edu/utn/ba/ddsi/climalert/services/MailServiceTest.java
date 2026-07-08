package ar.edu.utn.ba.ddsi.climalert.services;

import ar.edu.utn.ba.ddsi.climalert.providers.EmailProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MailServiceTest {

    @Mock
    private EmailProvider emailProvider;

    @Test
    void enviarMailDelegaEnElEmailProviderConAsuntoFijo() {
        MailService mailService = new MailService(emailProvider);

        mailService.enviarMail("admin@clima.com", "temperatura y humedad altas");

        verify(emailProvider).enviar("admin@clima.com", "Alerta climatica - Climalert", "temperatura y humedad altas");
    }
}
