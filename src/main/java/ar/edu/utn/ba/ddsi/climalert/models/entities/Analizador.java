package ar.edu.utn.ba.ddsi.climalert.models.entities;

import ar.edu.utn.ba.ddsi.climalert.client.WeatherApiClient;

import java.util.ArrayList;

public class Analizador {
    private String nombreLugar;
    private ArrayList<Suscriptor> suscriptores;
    private WeatherApiClient weatherApiClient;

    public Analizador(String nombreLugar, ArrayList<Suscriptor> suscriptores, WeatherApiClient weatherApiClient) {
        this.nombreLugar = nombreLugar;
        this.suscriptores = suscriptores;
        this.weatherApiClient = weatherApiClient;
    }

    public Clima reportarClima(){
        Clima clima = weatherApiClient.obtenerClima(nombreLugar);
        suscriptores.stream().forEach(suscriptor -> suscriptor.notificarClima(clima));
        return clima;
    }
}
