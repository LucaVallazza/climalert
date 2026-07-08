package ar.edu.utn.ba.ddsi.climalert.models.records;

import lombok.AllArgsConstructor;
import lombok.Getter;


public record Lugar(String nombre, double latitud, double longitud) {
}
