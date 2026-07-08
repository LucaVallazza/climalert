package ar.edu.utn.ba.ddsi.climalert.providers;

public interface EmailProvider {
    void enviar(String destinatario, String asunto, String cuerpo);
}
