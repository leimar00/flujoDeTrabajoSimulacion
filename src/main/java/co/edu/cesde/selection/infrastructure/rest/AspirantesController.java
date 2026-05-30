package co.edu.cesde.selection.infrastructure.rest;

import co.edu.cesde.selection.infrastructure.persistence.AspiranteEntity;
import co.edu.cesde.selection.infrastructure.persistence.AspiranteJpaRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/aspirantes")
@CrossOrigin(origins = "http://localhost:5173")
public class AspirantesController {

    private final AspiranteJpaRepo repo;

    public AspirantesController(AspiranteJpaRepo repo) {
        this.repo = repo;
    }

    // Listar todos
    @GetMapping
    public Map<String,Object> listAll() {
        List<Map<String,Object>> items = repo.findAll().stream().map(e -> {
            Map<String,Object> m = new HashMap<>();
            m.put("id", e.getId());
            m.put("nombre", e.getNombres());
            m.put("apellido", e.getApellidos());
            m.put("email", e.getEmail());
            m.put("estado", e.getEstado());
            m.put("cargoAplicado", e.getCargoAplicado());
            return m;
        }).collect(Collectors.toList());
        Map<String,Object> res = new HashMap<>();
        res.put("success", true);
        res.put("data", items);
        return res;
    }

    // Crear
    @PostMapping
    public Map<String,Object> create(@RequestBody Map<String,Object> body) {
        AspiranteEntity e = new AspiranteEntity();
        // aceptar varios nombres de campo para compatibilidad
        Object nombre = body.getOrDefault("nombre", body.get("nombres"));
        Object apellido = body.getOrDefault("apellido", body.get("apellidos"));
        Object email = body.get("email");
        Object estado = body.getOrDefault("estado", "pendiente");
        Object cargo = body.getOrDefault("cargoAplicado", body.get("cargo") );

        e.setNombres(nombre != null ? nombre.toString() : null);
        e.setApellidos(apellido != null ? apellido.toString() : null);
        e.setEmail(email != null ? email.toString() : null);
        e.setEstado(estado != null ? estado.toString() : null);
        e.setCargoAplicado(cargo != null ? cargo.toString() : null);

        AspiranteEntity saved = repo.save(e);

        Map<String,Object> data = new HashMap<>();
        Map<String,Object> m = new HashMap<>();
        m.put("id", saved.getId());
        m.put("nombre", saved.getNombres());
        m.put("apellido", saved.getApellidos());
        m.put("email", saved.getEmail());
        m.put("estado", saved.getEstado());
        m.put("cargoAplicado", saved.getCargoAplicado());
        data.put("data", m);

        Map<String,Object> res = new HashMap<>();
        res.put("success", true);
        res.putAll(data);
        return res;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String,Object>> update(@PathVariable Long id, @RequestBody Map<String,Object> body) {
        return repo.findById(id).map(e -> {
            Object nombre = body.getOrDefault("nombre", body.get("nombres"));
            Object apellido = body.getOrDefault("apellido", body.get("apellidos"));
            Object email = body.get("email");
            Object estado = body.getOrDefault("estado", e.getEstado());
            Object cargo = body.getOrDefault("cargoAplicado", body.get("cargo") );

            if (nombre != null) e.setNombres(nombre.toString());
            if (apellido != null) e.setApellidos(apellido.toString());
            if (email != null) e.setEmail(email.toString());
            e.setEstado(estado != null ? estado.toString() : e.getEstado());
            if (cargo != null) e.setCargoAplicado(cargo.toString());

            AspiranteEntity saved = repo.save(e);

            Map<String,Object> m = new HashMap<>();
            m.put("id", saved.getId());
            m.put("nombre", saved.getNombres());
            m.put("apellido", saved.getApellidos());
            m.put("email", saved.getEmail());
            m.put("estado", saved.getEstado());
            m.put("cargoAplicado", saved.getCargoAplicado());

            Map<String,Object> res = new HashMap<>();
            res.put("success", true);
            res.put("data", m);
            return ResponseEntity.ok(res);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
