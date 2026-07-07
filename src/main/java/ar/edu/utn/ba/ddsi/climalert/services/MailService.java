package ar.edu.utn.ba.ddsi.climalert.services;

import org.springframework.stereotype.Service;

// Se comunica con los mails
@Service
public class MailService {

    public void enviarMail(String direccion, String mensaje){
        System.out.println("Enviar mail a :" + direccion + " con mensaje: " + mensaje);

    }
}
