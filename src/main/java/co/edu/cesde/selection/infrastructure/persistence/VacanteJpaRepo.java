package co.edu.cesde.selection.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacanteJpaRepo extends JpaRepository<VacanteEntity, Long> {
}
