package ar.edu.utn.ba.ddsi.climalert.models.entities;

import ar.edu.utn.ba.ddsi.climalert.models.repositories.ClimaRepository;

import java.util.ArrayList;

public class Analizador {
    private String nombreLugar;
    private ArrayList<Suscriptor> suscriptores;
    private ClimaRepository climaRepository;

    public Analizador(String nombreLugar, ArrayList<Suscriptor> suscriptores, ClimaRepository climaRepository) {
        this.nombreLugar = nombreLugar;
        this.suscriptores = suscriptores;
        this.climaRepository = climaRepository;
    }

    public void analizar(){
        climaRepository.obtenerUltimo(nombreLugar)
                .ifPresent(clima -> suscriptores.forEach(suscriptor -> suscriptor.notificarClima(clima)));
    }
}
