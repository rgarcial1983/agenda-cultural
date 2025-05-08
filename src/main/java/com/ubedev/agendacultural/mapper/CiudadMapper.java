package com.ubedev.agendacultural.mapper;

import com.ubedev.agendacultural.dto.ciudad.CiudadCreateDTO;
import com.ubedev.agendacultural.dto.ciudad.CiudadResponseDTO;
import com.ubedev.agendacultural.dto.ciudad.CiudadUpdateDTO;
import com.ubedev.agendacultural.model.Ciudad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CiudadMapper {
    
    @Mapping(target = "id", ignore = true)
    Ciudad toEntity(CiudadCreateDTO dto);
    
    CiudadResponseDTO toResponseDTO(Ciudad entity);
    
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(CiudadUpdateDTO dto, @MappingTarget Ciudad entity);
} 