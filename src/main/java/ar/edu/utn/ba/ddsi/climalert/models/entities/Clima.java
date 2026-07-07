package ar.edu.utn.ba.ddsi.climalert.models.entities;

public record Clima(String ubicacion, Double latitud, Double longitud, Double temperatura, Double humidity) {

  public boolean esAlarmante() {
    return temperatura != null && temperatura > 35 && humidity != null && humidity > 60;
  }
}