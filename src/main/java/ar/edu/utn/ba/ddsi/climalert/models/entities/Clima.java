package ar.edu.utn.ba.ddsi.climalert.models.entities;

public record Clima(String ubicacion, Double latitud, Double longitud, Double temperatura, Double humidity) {

    public String detalle() {
        return "Ubicación: %s (lat %.2f, lon %.2f)\nTemperatura: %.1f°C\nHumedad: %.1f%%"
                .formatted(ubicacion, latitud, longitud, temperatura, humidity);
    }
}