package com.ubedev.agendacultural.controller;

import com.ubedev.agendacultural.model.Ciudad;
import com.ubedev.agendacultural.service.CiudadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ciudades")
public class CiudadController {

    private final CiudadService ciudadService;

    public CiudadController(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    @GetMapping
    public ResponseEntity<List<Ciudad>> listar(
            @RequestParam(required = false) String nombre) {
        return ResponseEntity.ok(ciudadService.listar(nombre));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ciudad> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(ciudadService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Ciudad> crear(@RequestBody Ciudad ciudad) {
        return ResponseEntity.ok(ciudadService.crear(ciudad));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ciudadService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
