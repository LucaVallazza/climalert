package ar.edu.utn.ba.ddsi.climalert.providers;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class SmtpEmailProvider implements EmailProvider {

    private final JavaMailSender javaMailSender;

    public SmtpEmailProvider(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void enviar(String destinatario, String asunto, String cuerpo) {
        System.out.println("[" + LocalTime.now().withNano(0) + "] mandando mail a " + destinatario + "...");

        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(destinatario);
        mensaje.setSubject(asunto);
        mensaje.setText(cuerpo);
        javaMailSender.send(mensaje);

        System.out.println("[" + LocalTime.now().withNano(0) + "] mail enviado a " + destinatario);
    }
}
