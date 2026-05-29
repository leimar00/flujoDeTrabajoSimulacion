package co.edu.cesde.selection.infrastructure.adapter;

import co.edu.cesde.selection.application.port.output.ContratacionNotifPort;
import co.edu.cesde.selection.domain.ProcesoSeleccion;
import org.springframework.stereotype.Component;

@Component
public class ContratacionNotifAdapter implements ContratacionNotifPort {

    @Override
    public void notificarCandidatoAprobado(ProcesoSeleccion proceso) {
        System.out.println("Notificando a E4: Candidato " + proceso.getNombreAspirante() + " aprobado.");
    }
}