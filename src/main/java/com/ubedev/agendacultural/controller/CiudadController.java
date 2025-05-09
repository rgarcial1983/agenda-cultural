package com.ubedev.agendacultural.controller;

import com.ubedev.agendacultural.dto.ciudad.CiudadCreateDTO;
import com.ubedev.agendacultural.dto.ciudad.CiudadResponseDTO;
import com.ubedev.agendacultural.dto.ciudad.CiudadUpdateDTO;
import com.ubedev.agendacultural.mapper.CiudadMapper;
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
    public ResponseEntity<List<CiudadResponseDTO>> listar(
            @RequestParam(required = false) String nombre) {
        List<Ciudad> ciudades = ciudadService.listar(nombre);
        List<CiudadResponseDTO> dtos = ciudades.stream()
                .map(ciudadMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener ciudad", description = "Obtiene una ciudad por su ID")
    public ResponseEntity<CiudadResponseDTO> obtener(@PathVariable Long id) {
        Ciudad ciudad = ciudadService.obtenerPorId(id);
        return ResponseEntity.ok(ciudadMapper.toResponseDTO(ciudad));
    }

    @PostMapping
    @Operation(summary = "Crear ciudad", description = "Crea una nueva ciudad")
    public ResponseEntity<CiudadResponseDTO> crear(@Valid @RequestBody CiudadCreateDTO dto) {
        Ciudad ciudad = ciudadService.crear(ciudadMapper.toEntity(dto));
        return ResponseEntity.ok(ciudadMapper.toResponseDTO(ciudad));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar ciudad", description = "Actualiza una ciudad existente")
    public ResponseEntity<CiudadResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody CiudadUpdateDTO dto) {
        Ciudad ciudad = ciudadService.obtenerPorId(id);
        ciudadMapper.updateEntityFromDTO(dto, ciudad);
        ciudad = ciudadService.actualizar(ciudad);
        return ResponseEntity.ok(ciudadMapper.toResponseDTO(ciudad));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar ciudad", description = "Elimina una ciudad por su ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ciudadService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
