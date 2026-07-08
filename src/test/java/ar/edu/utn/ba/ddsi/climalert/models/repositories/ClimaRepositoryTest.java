package ar.edu.utn.ba.ddsi.climalert.models.repositories;

import ar.edu.utn.ba.ddsi.climalert.models.entities.Clima;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ClimaRepositoryTest {

    @Test
    void obtenerUltimoDevuelveElUltimoClimaGuardadoParaEseLugar() {
        ClimaRepository climaRepository = new ClimaRepository();
        Clima climaViejoBuenosAires = new Clima("Buenos Aires", -34.6, -58.4, 20.0, 40.0);
        Clima climaNuevoBuenosAires = new Clima("Buenos Aires", -34.6, -58.4, 36.0, 61.0);
        Clima climaCordoba = new Clima("Cordoba", -31.4, -64.2, 30.0, 50.0);

        climaRepository.guardar(climaViejoBuenosAires);
        climaRepository.guardar(climaCordoba);
        climaRepository.guardar(climaNuevoBuenosAires);

        assertThat(climaRepository.obtenerUltimo("Buenos Aires")).contains(climaNuevoBuenosAires);
        assertThat(climaRepository.obtenerUltimo("Cordoba")).contains(climaCordoba);
    }

    @Test
    void obtenerUltimoDevuelveVacioSiNoHayClimaGuardadoParaEseLugar() {
        ClimaRepository climaRepository = new ClimaRepository();

        Optional<Clima> resultado = climaRepository.obtenerUltimo("Buenos Aires");

        assertThat(resultado).isEmpty();
    }
}
