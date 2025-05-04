package com.ubedev.agendacultural.controller;

import com.ubedev.agendacultural.model.Localizacion;
import com.ubedev.agendacultural.service.LocalizacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/localizaciones")
public class LocalizacionController {

    private final LocalizacionService localizacionService;

    public LocalizacionController(LocalizacionService localizacionService) {
        this.localizacionService = localizacionService;
    }

    @GetMapping
    public ResponseEntity<List<Localizacion>> listar() {
        return ResponseEntity.ok(localizacionService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Localizacion> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(localizacionService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Localizacion> crear(@RequestBody Localizacion localizacion) {
        return ResponseEntity.ok(localizacionService.crear(localizacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        localizacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
