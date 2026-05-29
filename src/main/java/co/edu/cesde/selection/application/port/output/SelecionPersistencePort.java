package co.edu.cesde.selection.application.port.output;

import co.edu.cesde.selection.domain.Aspirante;
import co.edu.cesde.selection.domain.ProcesoSeleccion; // O la ruta de tu aggregate root
import java.util.Optional;
import java.util.List;

public interface SelecionPersistencePort {
    ProcesoSeleccion guardar(ProcesoSeleccion proceso); // Cambiado de 'save' a 'guardar'
    Optional<ProcesoSeleccion> buscarPorId(Long id);    // Cambiado de 'findById' a 'buscarPorId'
    List<ProcesoSeleccion> buscarAprobados();           // Cambiado
    List<ProcesoSeleccion> buscarTodos();               // Cambiado
    Optional<Aspirante> findAspiranteById(Long id);
}
