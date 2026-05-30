package co.edu.cesde.selection.infrastructure.rest;

import co.edu.cesde.selection.infrastructure.persistence.ProcesoSeleccionJpaEntity;
import co.edu.cesde.selection.infrastructure.persistence.SeleccionJpaRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/procesos")
@CrossOrigin(origins = "http://localhost:5173")
public class ProcesosController {

    private final SeleccionJpaRepo repo;

    public ProcesosController(SeleccionJpaRepo repo) {
        this.repo = repo;
    }

    @GetMapping
    public Map<String, Object> listAll() {
        List<Map<String, Object>> items = repo.findAll().stream()
                .map(this::toMap)
                .collect(Collectors.toList());
        Map<String, Object> res = new HashMap<>();
        res.put("success", true);
        res.put("data", items);
        return res;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable Long id) {
        return repo.findById(id)
                .map(e -> {
                    Map<String, Object> res = new HashMap<>();
                    res.put("success", true);
                    res.put("data", toMap(e));
                    return ResponseEntity.ok(res);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Map<String, Object> create(@RequestBody Map<String, Object> body) {
        ProcesoSeleccionJpaEntity e = new ProcesoSeleccionJpaEntity();
        applyFields(e, body);
        if (e.getFechaInicio() == null) e.setFechaInicio(LocalDate.now());
        if (e.getEtapa() == null) e.setEtapa("convocatoria");
        ProcesoSeleccionJpaEntity saved = repo.save(e);
        Map<String, Object> res = new HashMap<>();
        res.put("success", true);
        res.put("data", toMap(saved));
        return res;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        return repo.findById(id)
                .map(e -> {
                    applyFields(e, body);
                    ProcesoSeleccionJpaEntity saved = repo.save(e);
                    Map<String, Object> res = new HashMap<>();
                    res.put("success", true);
                    res.put("data", toMap(saved));
                    return ResponseEntity.ok(res);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private void applyFields(ProcesoSeleccionJpaEntity e, Map<String, Object> body) {
        Object vacanteId = body.getOrDefault("id_vacante", body.get("vacanteId"));
        Object aspiranteId = body.getOrDefault("id_aspirante", body.get("aspiranteId"));
        Object etapa = body.getOrDefault("etapa", body.get("stage"));
        Object observaciones = body.get("observaciones");
        Object nombreAspirante = body.getOrDefault("nombreAspirante", body.get("nombre_aspirante"));
        Object fechaCreacion = body.getOrDefault("fecha_creacion", body.get("fecha"));

        if (vacanteId != null) {
            try { e.setVacanteId(Long.parseLong(vacanteId.toString())); } catch (NumberFormatException ignored) {}
        }
        if (aspiranteId != null) {
            try { e.setAspiranteId(Long.parseLong(aspiranteId.toString())); } catch (NumberFormatException ignored) {}
        }
        if (etapa != null) e.setEtapa(etapa.toString());
        if (observaciones != null) e.setObservaciones(observaciones.toString());
        if (nombreAspirante != null) e.setNombreAspirante(nombreAspirante.toString());
        if (fechaCreacion != null) {
            try { e.setFechaInicio(LocalDate.parse(fechaCreacion.toString())); } catch (Exception ignored) {}
        }
    }

    private Map<String, Object> toMap(ProcesoSeleccionJpaEntity e) {
        Map<String, Object> m = new HashMap<>();
        m.put("id", e.getId());
        m.put("id_vacante", e.getVacanteId());
        m.put("id_aspirante", e.getAspiranteId());
        m.put("nombreAspirante", e.getNombreAspirante());
        m.put("etapa", e.getEtapa() != null ? e.getEtapa() : (e.getEstado() != null ? e.getEstado().toString() : "convocatoria"));
        m.put("observaciones", e.getObservaciones());
        m.put("fecha_creacion", e.getFechaInicio() != null ? e.getFechaInicio().toString() : null);
        return m;
    }
}
