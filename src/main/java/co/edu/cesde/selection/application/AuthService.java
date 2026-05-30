package co.edu.cesde.selection.application;

import co.edu.cesde.selection.domain.Reclutador;
import co.edu.cesde.selection.domain.ReclutadorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final ReclutadorRepository repo;

    public AuthService(ReclutadorRepository repo) {
        this.repo = repo;
    }

    public Reclutador login(String email, String passwordPlain) {
        Reclutador r = repo.findByEmail(email);
        if (r == null) return null;
        if (r.getActive() != null && !r.getActive()) return null;
        // comparación en texto plano (solo pruebas)
        if (r.getPassword() != null && r.getPassword().equals(passwordPlain)) {
            repo.updateLastLogin(r.getId());
            r.setPassword(null);
            return r;
        }
        return null;
    }

    public Reclutador getProfile(Long id) {
        return repo.findById(id);
    }
}
