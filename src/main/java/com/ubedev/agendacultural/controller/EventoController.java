package com.ubedev.agendacultural.controller;

import com.ubedev.agendacultural.dto.EventoDTO;
import com.ubedev.agendacultural.model.Evento;
import com.ubedev.agendacultural.service.EventoService;
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

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @PostMapping
    public ResponseEntity<Evento> crearEvento(@RequestBody EventoDTO dto) {
        Evento evento = eventoService.crearEvento(dto);
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
        return ResponseEntity.ok(eventoService.listarEventos(titulo, fechaInicio, fechaFin, ciudadId, categoriaIds, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> obtenerEvento(@PathVariable Long id) {
        Evento evento = eventoService.obtenerEventoPorId(id);
        return ResponseEntity.ok(evento);
    }
}

