package ar.edu.utn.ba.ddsi.climalert.models.entities;

import ar.edu.utn.ba.ddsi.climalert.models.records.Lugar;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Analizador {
    private Lugar lugar;
    private ArrayList<Alertador> alertadores;
    private ArrayList<CriterioAlarma> criterios;

    public void analizarClima(Clima clima){
        if(criterios.stream().
                allMatch(criterio -> criterio.esAlarmante(clima))
        ){
            alertadores.stream().forEach(alertador -> alertador.enviarAlerta());
        }
    }

}
