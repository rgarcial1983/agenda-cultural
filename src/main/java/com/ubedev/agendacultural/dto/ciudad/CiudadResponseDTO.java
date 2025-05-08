package com.ubedev.agendacultural.dto.ciudad;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para respuesta de Ciudad")
public class CiudadResponseDTO {
    @Schema(description = "Identificador único de la ciudad")
    private Long id;
    
    @Schema(description = "Nombre de la ciudad")
    private String nombre;
} 