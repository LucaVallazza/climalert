package ar.edu.utn.ba.ddsi.climalert.models.entities;
import ar.edu.utn.ba.ddsi.climalert.services.MailService;

public class AlertadorMail implements Alertador{
    private String mail;
    private MailService mailService;

    public AlertadorMail(String mail, MailService mailService){
        this.mail = mail;
        this.mailService = mailService;
    }

    public AlertadorMail(String mail) {
        this.mail = mail;
    }

    @Override
    public void enviarAlerta(String mensaje) {
        mailService.enviarMail(mail , mensaje);
    }
}
