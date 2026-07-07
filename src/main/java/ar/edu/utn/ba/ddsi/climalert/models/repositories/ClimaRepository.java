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

    public Optional<Clima> obtenerUltimo() {
        if (climas.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(climas.get(climas.size() - 1));
    }

    public ArrayList<Clima> obtenerTodos() {
        return climas;
    }
}
