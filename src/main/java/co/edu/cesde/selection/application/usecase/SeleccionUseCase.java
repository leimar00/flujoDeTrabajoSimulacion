package co.edu.cesde.selection.application.usecase;

import co.edu.cesde.selection.application.dto.AspiranteDTO;
import co.edu.cesde.selection.application.dto.EntrevistaCmd;
import co.edu.cesde.selection.application.dto.ProcesoDTO;
import co.edu.cesde.selection.application.port.input.SeleccionServicePort;
import co.edu.cesde.selection.application.port.output.ContratacionNotifPort;
import co.edu.cesde.selection.application.port.output.SelecionPersistencePort;
import co.edu.cesde.selection.domain.ProcesoSeleccion;
import co.edu.cesde.selection.domain.enums.EstadoSeleccion;
import co.edu.cesde.selection.infrastructure.mapper.SeleccionMapper;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class SeleccionUseCase implements SeleccionServicePort {

    private final SelecionPersistencePort persistencePort;
    private final ContratacionNotifPort notifPort;
    private final SeleccionMapper mapper;

    public SeleccionUseCase(SelecionPersistencePort persistencePort, ContratacionNotifPort notifPort, SeleccionMapper mapper) {
        this.persistencePort = persistencePort;
        this.notifPort = notifPort;
        this.mapper = mapper;
    }

    @Override
    public ProcesoDTO iniciarProceso(AspiranteDTO dto) {
        ProcesoSeleccion nuevoProceso = mapper.toDomainFromAspirante(dto);
        nuevoProceso.setEstado(EstadoSeleccion.PENDIENTE);
        nuevoProceso.setFechaInicio(LocalDate.now());

        ProcesoSeleccion guardado = persistencePort.guardar(nuevoProceso);
        return mapper.toDTO(guardado);
    }

    @Override
    public void registrarEntrevista(EntrevistaCmd cmd) {
        ProcesoSeleccion proceso = persistencePort.buscarPorId(cmd.getIdAspirante())
                .orElseThrow(() -> new RuntimeException("Proceso no encontrado para el ID: " + cmd.getIdAspirante()));

        proceso.setEstado(EstadoSeleccion.ENTREVISTADO);
        proceso.setObservaciones(cmd.getNotasEntrevistador());

        persistencePort.guardar(proceso);
    }

    @Override
    public void aprobar(Long procesoId) {
        ProcesoSeleccion proceso = persistencePort.buscarPorId(procesoId)
                .orElseThrow(() -> new RuntimeException("Proceso no encontrado."));

        proceso.setEstado(EstadoSeleccion.APROBADO);
        proceso.setFechaDecision(LocalDate.now());

        ProcesoSeleccion actualizado = persistencePort.guardar(proceso);
        notifPort.notificarCandidatoAprobado(actualizado);
    }

    @Override
    public void rechazar(Long procesoId, String motivo) {
        ProcesoSeleccion proceso = persistencePort.buscarPorId(procesoId)
                .orElseThrow(() -> new RuntimeException("Proceso no encontrado."));

        proceso.setEstado(EstadoSeleccion.RECHAZADO);
        proceso.setFechaDecision(LocalDate.now());
        proceso.setObservaciones("Motivo de rechazo: " + motivo);

        persistencePort.guardar(proceso);
    }

    @Override
    public ProcesoDTO buscarPorId(Long id) {
        return persistencePort.buscarPorId(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Proceso no encontrado con ID: " + id));
    }

    @Override
    public List<ProcesoDTO> listarAprobados() {
        List<ProcesoSeleccion> aprobados = persistencePort.buscarAprobados();
        return mapper.toDTOList(aprobados);
    }

    @Override
    public byte[] exportarCSV() {
        List<ProcesoSeleccion> todos = persistencePort.buscarTodos();
        StringBuilder csvBuilder = new StringBuilder();

        csvBuilder.append("ID Proceso,ID Aspirante,Nombre,Cargo,Estado,Observaciones\n");

        for (ProcesoSeleccion p : todos) {
            String estadoStr = (p.getEstado() != null) ? p.getEstado().name() : "SIN_ESTADO";
            String obsStr = (p.getObservaciones() != null) ? p.getObservaciones().replace(",", " ") : "";

            csvBuilder.append(p.getId()).append(",")
                    .append(p.getAspiranteId()).append(",")
                    .append(p.getNombreAspirante()).append(",")
                    .append(p.getCargoAplicado()).append(",")
                    .append(estadoStr).append(",")
                    .append(obsStr).append("\n");
        }

        return csvBuilder.toString().getBytes();
    }
}