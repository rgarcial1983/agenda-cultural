package com.ubedev.agendacultural.dto.categoria;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para respuesta de Categoría")
public class CategoriaResponseDTO {
    @Schema(description = "Identificador único de la categoría")
    private Long id;
    
    @Schema(description = "Nombre de la categoría")
    private String nombre;
} 