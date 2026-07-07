package ar.edu.utn.ba.ddsi.climalert.models.entities;

public interface CriterioAlarma {
    abstract Boolean esAlarmante(Clima clima);
}
