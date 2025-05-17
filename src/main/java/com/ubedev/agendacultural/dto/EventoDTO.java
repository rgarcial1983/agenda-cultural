package com.ubedev.agendacultural.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoDTO {
    private Long id;
    @NotBlank(message = "El título es obligatorio")
    @Size(max = 255, message = "El título no puede exceder los 255 caracteres")
    private String titulo;
    private String descripcion;
    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;
    private Long localizacionId;
    private List<Long> categoriaIds;
}
