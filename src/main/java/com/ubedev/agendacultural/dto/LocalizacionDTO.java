package com.ubedev.agendacultural.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalizacionDTO {
    private Long id;
    private String lugar;
    private String enlaceGoogleMaps;
} 