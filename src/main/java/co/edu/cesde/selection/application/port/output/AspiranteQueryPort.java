package co.edu.cesde.selection.application.port.output;

import co.edu.cesde.selection.application.dto.AspiranteDTO;
import java.util.Optional;

public interface AspiranteQueryPort {
    Optional<AspiranteDTO> buscarPorId(Long id);
}

