package ar.edu.utn.ba.ddsi.climalert.models.entities;
import java.time.LocalTime;
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
            System.out.println("[" + LocalTime.now().withNano(0) + "] clima alarmante en " + clima.ubicacion() + " -> " + clima.temperatura() + "c, " + clima.humidity() + "% humedad");
            alertarATodos(clima);
        } else {
            System.out.println("[" + LocalTime.now().withNano(0) + "] clima normal en " + clima.ubicacion() + " -> " + clima.temperatura() + "c, " + clima.humidity() + "% humedad");
        }
    }

    private Boolean cumpleCriterios(Clima clima){
        return criterios.stream().allMatch(criterio -> criterio.esAlarmante(clima));
    }

    private void alertarATodos(Clima clima){
        String motivos = criterios.stream()
                .map(criterio -> criterio.crearMensajeAlerta(clima))
                .collect(Collectors.joining("\n"));
        String mensaje = motivos + "\n\n" + clima.detalle();

        alertadores.forEach(alertador -> alertador.enviarAlerta(mensaje));
    }



}
