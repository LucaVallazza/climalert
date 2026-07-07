package ar.edu.utn.ba.ddsi.climalert.models.entities;

public class CriterioMayorTemperaturaHumedad implements CriterioAlarma{
    private Double temperaturaAlarmante;
    private Double humedadAlarmante;

    @Override
    public Boolean esAlarmante(Clima clima) {
        return clima.humidity() > humedadAlarmante && clima.temperatura() > temperaturaAlarmante;
    }

    @Override
    public String crearMensajeAlerta(Clima clima) {
        return "La temperatura superó los " + temperaturaAlarmante + " grados y la humedad es mayor a " + humedadAlarmante;
    }
}
