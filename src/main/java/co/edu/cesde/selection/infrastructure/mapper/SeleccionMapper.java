package co.edu.cesde.selection.infrastructure.mapper;

import co.edu.cesde.selection.application.dto.AspiranteDTO;
import co.edu.cesde.selection.application.dto.ProcesoDTO;
import co.edu.cesde.selection.domain.ProcesoSeleccion;
import co.edu.cesde.selection.infrastructure.persistence.ProcesoSeleccionJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SeleccionMapper {

    ProcesoSeleccionJpaEntity toEntity(ProcesoSeleccion domain);
    ProcesoSeleccion toDomain(ProcesoSeleccionJpaEntity entity);




    @Mapping(target = "aspiranteId", source = "id")
    @Mapping(target = "nombreAspirante", expression = "java(dto.getNombre() + \" \" + dto.getApellido())")
    @Mapping(target = "cargoAplicado", source = "cargo")
    ProcesoSeleccion toDomainFromAspirante(AspiranteDTO dto);

    @Mapping(target = "idAspirante", source = "aspiranteId")
    @Mapping(target = "nombreCompleto", source = "nombreAspirante")
    @Mapping(target = "cargo", source = "cargoAplicado")
    @Mapping(target = "estadoActual", expression = "java(domain.getEstado() != null ? domain.getEstado().name() : null)")
    ProcesoDTO toDTO(ProcesoSeleccion domain);

    List<ProcesoDTO> toDTOList(List<ProcesoSeleccion> domainList);
}