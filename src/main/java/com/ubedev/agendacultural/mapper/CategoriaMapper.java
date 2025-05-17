package com.ubedev.agendacultural.mapper;

import com.ubedev.agendacultural.dto.CategoriaDTO;
import com.ubedev.agendacultural.model.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    @Mapping(target = "id", ignore = true)
    Categoria toEntity(CategoriaDTO dto);
    CategoriaDTO toDTO(Categoria entity);
} 