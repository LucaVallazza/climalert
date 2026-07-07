package ar.edu.utn.ba.ddsi.climalert.models.entities;

import ar.edu.utn.ba.ddsi.climalert.client.WeatherApiClient;
import ar.edu.utn.ba.ddsi.climalert.models.records.Lugar;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Analizador {
    private String nombreLugar;
    private ArrayList<Suscriptor> suscriptores;
    private WeatherApiClient weatherApiClient;

    public Analizador(WeatherApiClient weatherApiClient) {
        this.weatherApiClient = weatherApiClient;
    }

    public void reportarClima(){
        weatherApiClient.obtenerClima(nombreLugar);
        suscriptores.stream().forEach(suscriptor -> suscriptor.notificarClima(clima));

    }
}
