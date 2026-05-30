package co.edu.cesde.selection.infrastructure.persistence;

import co.edu.cesde.selection.domain.Reclutador;
import co.edu.cesde.selection.domain.ReclutadorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReclutadorPersistenceAdapter implements ReclutadorRepository {

    private final ReclutadorJpaRepo repo;

    public ReclutadorPersistenceAdapter(ReclutadorJpaRepo repo) {
        this.repo = repo;
    }

    @Override
    public Reclutador findByEmail(String email) {
        Optional<ReclutadorJpaEntity> opt = repo.findByEmail(email);
        if (opt.isEmpty()) return null;
        ReclutadorJpaEntity e = opt.get();
        Reclutador r = new Reclutador();
        r.setId(e.getId());
        r.setNombre(e.getNombre());
        r.setApellido(e.getApellido());
        r.setEmail(e.getEmail());
        r.setPassword(e.getPassword());
        r.setActive(e.getActive());
        r.setCreatedAt(e.getCreatedAt());
        r.setLastLogin(e.getLastLogin());
        return r;
    }

    @Override
    public Reclutador findById(Long id) {
        Optional<ReclutadorJpaEntity> opt = repo.findById(id);
        if (opt.isEmpty()) return null;
        ReclutadorJpaEntity e = opt.get();
        Reclutador r = new Reclutador();
        r.setId(e.getId());
        r.setNombre(e.getNombre());
        r.setApellido(e.getApellido());
        r.setEmail(e.getEmail());
        r.setPassword(e.getPassword());
        r.setActive(e.getActive());
        r.setCreatedAt(e.getCreatedAt());
        r.setLastLogin(e.getLastLogin());
        return r;
    }

    @Override
    public void updateLastLogin(Long id) {
        repo.findById(id).ifPresent(e -> {
            e.setLastLogin(java.time.LocalDateTime.now());
            repo.save(e);
        });
    }
}
