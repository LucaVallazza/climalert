package ar.edu.utn.ba.ddsi.climalert.services;

import ar.edu.utn.ba.ddsi.climalert.models.entities.Analizador;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AlertService {

	private final ArrayList<Analizador> analizadores;

	public AlertService() {
		this.analizadores = new ArrayList<>();
	}

	public void agregarAnalizador(Analizador analizador) {
		analizadores.add(analizador);
	}

	@Scheduled(fixedRate = 60000)
	public void analizarClimas() {
		analizadores.stream().forEach(Analizador::analizar);
	}

}
