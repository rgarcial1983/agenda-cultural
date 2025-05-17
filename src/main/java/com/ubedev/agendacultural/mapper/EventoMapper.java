package com.ubedev.agendacultural.mapper;

import com.ubedev.agendacultural.dto.EventoDTO;
import com.ubedev.agendacultural.model.Evento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "localizacion", ignore = true) // Will be set in service
    @Mapping(target = "categorias", ignore = true) // Will be set in service
    Evento toEntity(EventoDTO dto);

    @Mapping(target = "localizacionId", source = "localizacion.id")
    @Mapping(target = "categoriaIds", expression = "java(entity.getCategorias() != null ? entity.getCategorias().stream().map(c -> c.getId()).collect(java.util.stream.Collectors.toList()) : null)")
    EventoDTO toDTO(Evento entity);
}