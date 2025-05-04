package com.ubedev.agendacultural.controller;

import com.ubedev.agendacultural.dto.EventoDTO;
import com.ubedev.agendacultural.model.Evento;
import com.ubedev.agendacultural.service.EventoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Evento>> listarEventos() {
        return ResponseEntity.ok(eventoService.listarEventos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> obtenerEvento(@PathVariable Long id) {
        Evento evento = eventoService.obtenerEventoPorId(id);
        return ResponseEntity.ok(evento);
    }
}

