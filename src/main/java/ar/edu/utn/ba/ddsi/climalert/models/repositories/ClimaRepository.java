package ar.edu.utn.ba.ddsi.climalert.models.repositories;

import ar.edu.utn.ba.ddsi.climalert.models.entities.Clima;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public class ClimaRepository {
    // por ahora el repository es una lista en memoria
    private final ArrayList<Clima> climas = new ArrayList<>();

    public void guardar(Clima clima) {
        climas.add(clima);
    }

    public Optional<Clima> obtenerUltimo(String lugar) {
        for (int i = climas.size() - 1; i >= 0; i--) {
            Clima clima = climas.get(i);
            if (clima.ubicacion().equals(lugar)) {
                return Optional.of(clima);
            }
        }
        return Optional.empty();
    }

    public ArrayList<Clima> obtenerTodos() {
        return climas;
    }
}
