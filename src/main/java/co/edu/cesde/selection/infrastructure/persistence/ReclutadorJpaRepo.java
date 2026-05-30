package co.edu.cesde.selection.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReclutadorJpaRepo extends JpaRepository<ReclutadorJpaEntity, Long> {
    Optional<ReclutadorJpaEntity> findByEmail(String email);
}