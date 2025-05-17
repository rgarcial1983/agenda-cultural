package com.ubedev.agendacultural.controller;

import com.ubedev.agendacultural.mapper.CiudadMapper;
import com.ubedev.agendacultural.dto.CiudadDTO;
import com.ubedev.agendacultural.model.Ciudad;
import com.ubedev.agendacultural.service.CiudadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ciudades")
@Tag(name = "Ciudades", description = "API para gestionar ciudades")
public class CiudadController {

    private final CiudadService ciudadService;
    private final CiudadMapper ciudadMapper;

    public CiudadController(CiudadService ciudadService, CiudadMapper ciudadMapper) {
        this.ciudadService = ciudadService;
        this.ciudadMapper = ciudadMapper;
    }

    @GetMapping
    @Operation(summary = "Listar ciudades", description = "Obtiene una lista de ciudades, opcionalmente filtrada por nombre")
    public ResponseEntity<List<CiudadDTO>> listar(
            @RequestParam(required = false) String nombre) {
        List<CiudadDTO> ciudades = ciudadService.listar(nombre);
        return ResponseEntity.ok(ciudades);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener ciudad", description = "Obtiene una ciudad por su ID")
    public ResponseEntity<CiudadDTO> obtener(@PathVariable Long id) {
        Ciudad ciudad = ciudadService.obtenerPorId(id);
        return ResponseEntity.ok(ciudadMapper.toDTO(ciudad));
    }

    @PostMapping
    @Operation(summary = "Crear ciudad", description = "Crea una nueva ciudad")
    public ResponseEntity<CiudadDTO> crear(@Valid @RequestBody CiudadDTO dto) {
        CiudadDTO ciudad = ciudadService.crear(dto);
        return ResponseEntity.ok(ciudad);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar ciudad", description = "Actualiza una ciudad existente")
    public ResponseEntity<CiudadDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody CiudadDTO dto) {
        Ciudad ciudad = ciudadService.obtenerPorId(id);
        CiudadDTO ciudadDTO = ciudadMapper.toDTO(ciudad);
        ciudadDTO = ciudadService.actualizar(id, ciudadDTO);
        return ResponseEntity.ok(ciudadDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar ciudad", description = "Elimina una ciudad por su ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ciudadService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
