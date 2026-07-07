package ar.edu.utn.ba.ddsi.climalert.models.entities;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Suscriptor {
    private ArrayList<CriterioAlarma> criterios;
    private ArrayList<Alertador> alertadores;

    public Suscriptor(ArrayList<CriterioAlarma> criterios, ArrayList<Alertador> alertadores) {
        this.criterios = criterios;
        this.alertadores = alertadores;
    }

    public void notificarClima(Clima clima){
        analizarClima(clima);
    }

    private void analizarClima(Clima clima){
        if (this.cumpleCriterios(clima)){
            alertarATodos(clima);
        }
    }

    private Boolean cumpleCriterios(Clima clima){
        return criterios.stream().allMatch(criterio -> criterio.esAlarmante(clima));
    }

    private void alertarATodos(Clima clima){
        alertadores.stream()
                .forEach(alertador -> {
                    String mensaje = criterios.stream()
                            .map(criterio -> criterio.crearMensajeAlerta(clima))
                            .collect(Collectors.joining("/n"));
                    alertador.enviarAlerta(mensaje);
                });
    }



}
