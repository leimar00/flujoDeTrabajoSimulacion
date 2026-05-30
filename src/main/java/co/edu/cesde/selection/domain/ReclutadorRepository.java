package co.edu.cesde.selection.domain;

public interface ReclutadorRepository {
    Reclutador findByEmail(String email);
    Reclutador findById(Long id);
    void updateLastLogin(Long id);
}
