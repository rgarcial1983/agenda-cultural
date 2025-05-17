package com.ubedev.agendacultural.controller;

import com.ubedev.agendacultural.dto.EventoDTO;
import com.ubedev.agendacultural.mapper.EventoMapper;
import com.ubedev.agendacultural.model.Evento;
import com.ubedev.agendacultural.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    private final EventoService eventoService;
    private final EventoMapper eventoMapper;

    public EventoController(EventoService eventoService, EventoMapper eventoMapper) {
        this.eventoService = eventoService;
        this.eventoMapper = eventoMapper;
    }

    @PostMapping
    public ResponseEntity<EventoDTO> crearEvento(@Valid @RequestBody EventoDTO dto) {
        EventoDTO evento = eventoService.crear(dto);
        return ResponseEntity.ok(evento);
    }

    @GetMapping
    public ResponseEntity<Page<Evento>> listarEventos(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            @RequestParam(required = false) Long ciudadId,
            @RequestParam(required = false) List<Long> categoriaIds,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<Evento> eventos = eventoService.listarEventos(titulo, fechaInicio, fechaFin, ciudadId, categoriaIds, pageable);
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoDTO> obtenerEvento(@PathVariable Long id) {
        EventoDTO evento = eventoService.obtenerPorId(id);
        return ResponseEntity.ok(evento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoDTO> actualizarEvento(@PathVariable Long id, @Valid @RequestBody EventoDTO dto) {
        EventoDTO evento = eventoService.actualizar(id, dto);
        return ResponseEntity.ok(evento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEvento(@PathVariable Long id) {
        eventoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}


