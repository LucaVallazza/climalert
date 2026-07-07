package ar.edu.utn.ba.ddsi.climalert.models.entities;

public class CriterioMayorTemperaturaHumedad implements CriterioAlarma{
    private Double temperaturaAlarmante;
    private Double humedadAlarmante;

    @Override
    public Boolean esAlarmante(Clima clima) {
        return clima.humidity() > humedadAlarmante && clima.temperatura() > temperaturaAlarmante;
    }
}
