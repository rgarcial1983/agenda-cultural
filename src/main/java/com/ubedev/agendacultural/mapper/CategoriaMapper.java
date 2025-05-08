package com.ubedev.agendacultural.mapper;

import com.ubedev.agendacultural.dto.categoria.CategoriaCreateDTO;
import com.ubedev.agendacultural.dto.categoria.CategoriaResponseDTO;
import com.ubedev.agendacultural.dto.categoria.CategoriaUpdateDTO;
import com.ubedev.agendacultural.model.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    
    @Mapping(target = "id", ignore = true)
    Categoria toEntity(CategoriaCreateDTO dto);
    
    CategoriaResponseDTO toResponseDTO(Categoria entity);
    
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(CategoriaUpdateDTO dto, @MappingTarget Categoria entity);
} 