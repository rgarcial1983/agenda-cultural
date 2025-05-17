package com.ubedev.agendacultural.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalizacionDTO {
    private Long id;
    private String lugar;
    private String enlaceGoogleMaps;
} 