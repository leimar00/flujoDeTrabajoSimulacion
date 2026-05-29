package co.edu.cesde.selection.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AspiranteJpaRepo extends JpaRepository<AspiranteEntity, Long> {
    // Interfaz mapeada correctamente con su tipo y entidad física
}