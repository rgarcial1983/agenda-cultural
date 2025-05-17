package com.ubedev.agendacultural.mapper;

import com.ubedev.agendacultural.dto.LocalizacionDTO;
import com.ubedev.agendacultural.model.Localizacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LocalizacionMapper {

    @Mapping(target = "id", ignore = true)
    Localizacion toEntity(LocalizacionDTO dto);

    LocalizacionDTO toDTO(Localizacion entity);
}