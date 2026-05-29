package co.edu.cesde.selection.infrastructure.persistence;

import co.edu.cesde.selection.domain.enums.EstadoSeleccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SeleccionJpaRepo extends JpaRepository<ProcesoSeleccionJpaEntity, Long> {


    List<ProcesoSeleccionJpaEntity> findByEstado(EstadoSeleccion estado);
}

