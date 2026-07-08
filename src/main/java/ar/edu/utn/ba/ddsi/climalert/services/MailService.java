package ar.edu.utn.ba.ddsi.climalert.services;

import ar.edu.utn.ba.ddsi.climalert.providers.EmailProvider;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final EmailProvider emailProvider;

    public MailService(EmailProvider emailProvider) {
        this.emailProvider = emailProvider;
    }

    public void enviarMail(String direccion, String mensaje){
        emailProvider.enviar(direccion, "Alerta climatica - Climalert", mensaje);
    }
}
