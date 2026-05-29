package co.edu.cesde.selection.infrastructure.rest;

import co.edu.cesde.selection.application.dto.AspiranteDTO;
import co.edu.cesde.selection.application.dto.ProcesoDTO;
import co.edu.cesde.selection.application.port.input.SeleccionServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/seleccion")
@CrossOrigin(origins = "*")
public class SeleccionController {

    private final SeleccionServicePort servicePort;

    public SeleccionController(SeleccionServicePort servicePort) {
        this.servicePort = servicePort;
    }

    @PostMapping
    public ResponseEntity<ProcesoDTO> iniciarProceso(@RequestBody AspiranteDTO dto) {
        return ResponseEntity.ok(servicePort.iniciarProceso(dto));
    }

    @PutMapping("/{id}/aprobar")
    public ResponseEntity<Void> aprobar(@PathVariable Long id) {
        servicePort.aprobar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ProcesoDTO>> listarAprobados() {
        return ResponseEntity.ok(servicePort.listarAprobados());
    }
}