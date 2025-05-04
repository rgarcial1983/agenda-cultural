package com.ubedev.agendacultural.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EventoDTO {
    private String titulo;
    private String descripcion;
    private LocalDate fecha;
    private Long localizacionId;
    private List<Long> categoriaIds;
}
