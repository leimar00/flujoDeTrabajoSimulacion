package co.edu.cesde.selection.infrastructure.rest;

import co.edu.cesde.selection.infrastructure.persistence.VacanteEntity;
import co.edu.cesde.selection.infrastructure.persistence.VacanteJpaRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vacantes")
@CrossOrigin(origins = "http://localhost:5173")
public class VacantesController {

    private final VacanteJpaRepo repo;

    public VacantesController(VacanteJpaRepo repo) {
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
        VacanteEntity e = new VacanteEntity();
        applyFields(e, body);
        if (e.getFecha() == null) e.setFecha(LocalDate.now());
        if (e.getEstado() == null) e.setEstado("activa");
        VacanteEntity saved = repo.save(e);
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
                    VacanteEntity saved = repo.save(e);
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

    private void applyFields(VacanteEntity e, Map<String, Object> body) {
        Object titulo = body.getOrDefault("titulo", body.get("title"));
        Object departamento = body.getOrDefault("departamento", body.get("department"));
        Object descripcion = body.getOrDefault("descripcion", body.get("description"));
        Object salario = body.getOrDefault("salario", body.get("salary"));
        Object estado = body.getOrDefault("estado", body.get("status"));
        Object fecha = body.getOrDefault("fecha", body.get("fecha_creacion"));

        if (titulo != null) e.setTitulo(titulo.toString());
        if (departamento != null) e.setDepartamento(departamento.toString());
        if (descripcion != null) e.setDescripcion(descripcion.toString());
        if (salario != null) {
            try { e.setSalario(Double.parseDouble(salario.toString())); } catch (NumberFormatException ignored) {}
        }
        if (estado != null) e.setEstado(estado.toString());
        if (fecha != null) {
            try { e.setFecha(LocalDate.parse(fecha.toString())); } catch (Exception ignored) {}
        }
    }

    private Map<String, Object> toMap(VacanteEntity e) {
        Map<String, Object> m = new HashMap<>();
        m.put("id", e.getId());
        m.put("titulo", e.getTitulo());
        m.put("departamento", e.getDepartamento());
        m.put("descripcion", e.getDescripcion());
        m.put("salario", e.getSalario());
        m.put("estado", e.getEstado());
        m.put("fecha", e.getFecha() != null ? e.getFecha().toString() : null);
        return m;
    }
}
