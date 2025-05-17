package com.ubedev.agendacultural.mapper;

import com.ubedev.agendacultural.dto.CiudadDTO;
import com.ubedev.agendacultural.model.Ciudad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CiudadMapper {
    @Mapping(target = "id", ignore = true)
    Ciudad toEntity(CiudadDTO dto);
    CiudadDTO toDTO(Ciudad entity);
} 