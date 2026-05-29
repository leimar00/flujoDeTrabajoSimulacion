package co.edu.cesde.selection.infrastructure.persistence;

import co.edu.cesde.selection.application.port.output.SelecionPersistencePort;
import co.edu.cesde.selection.domain.Aspirante;
import co.edu.cesde.selection.domain.ProcesoSeleccion;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.List;

@Component
public class SeleccionPersistenceAdapter implements SelecionPersistencePort {

    private final SeleccionJpaRepo seleccionJpaRepo;
    private final AspiranteJpaRepo aspiranteJpaRepo;

    public SeleccionPersistenceAdapter(SeleccionJpaRepo seleccionJpaRepo, AspiranteJpaRepo aspiranteJpaRepo) {
        this.seleccionJpaRepo = seleccionJpaRepo;
        this.aspiranteJpaRepo = aspiranteJpaRepo;
    }

    @Override
    public ProcesoSeleccion guardar(ProcesoSeleccion proceso) {
        // Aquí iría tu lógica de mapeo de dominio a entity y repositorio
        return proceso;
    }

    @Override
    public Optional<ProcesoSeleccion> buscarPorId(Long id) {
        return seleccionJpaRepo.findById(id).map(entity -> new ProcesoSeleccion());
    }

    @Override
    public List<ProcesoSeleccion> buscarAprobados() {
        return List.of();
    }

    @Override
    public List<ProcesoSeleccion> buscarTodos() {
        return List.of();
    }

    @Override
    public Optional<Aspirante> findAspiranteById(Long id) {
        return aspiranteJpaRepo.findById(id).map(entity -> {
            Aspirante domain = new Aspirante();
            domain.setId(entity.getId());
            domain.setNombre(entity.getNombres());
            domain.setApellido(entity.getApellidos());
            domain.setEmail(entity.getEmail());
            domain.setCargoAplicado(entity.getCargoAplicado());
            return domain;
        });
    }
}