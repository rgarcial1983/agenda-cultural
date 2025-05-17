package com.ubedev.agendacultural.controller;

import com.ubedev.agendacultural.dto.LocalizacionDTO;
import com.ubedev.agendacultural.mapper.LocalizacionMapper;
import com.ubedev.agendacultural.model.Localizacion;
import com.ubedev.agendacultural.service.LocalizacionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/localizaciones")
public class LocalizacionController {

    private final LocalizacionService localizacionService;
    private final LocalizacionMapper localizacionMapper;

    public LocalizacionController(LocalizacionService localizacionService, LocalizacionMapper localizacionMapper) {
        this.localizacionService = localizacionService;
        this.localizacionMapper = localizacionMapper;
    }

    @GetMapping
    public ResponseEntity<List<LocalizacionDTO>> listar(
            @RequestParam(required = false) String lugar) {
        List<Localizacion> localizaciones = localizacionService.listar(lugar);
        return ResponseEntity.ok(localizaciones.stream()
                .map(localizacionMapper::toDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalizacionDTO> obtener(@PathVariable Long id) {
        Localizacion localizacion = localizacionService.obtenerPorId(id);
        return ResponseEntity.ok(localizacionMapper.toDTO(localizacion));
    }

    @PostMapping
    public ResponseEntity<LocalizacionDTO> crear(@Valid @RequestBody LocalizacionDTO dto) {
        Localizacion localizacion = localizacionService.crear(localizacionMapper.toEntity(dto));
        return ResponseEntity.ok(localizacionMapper.toDTO(localizacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocalizacionDTO> actualizar(@PathVariable Long id, @Valid @RequestBody LocalizacionDTO dto) {
        Localizacion localizacion = localizacionService.obtenerPorId(id); // Asegura que la localización existe
        Localizacion updatedLocalizacion = localizacionService.actualizar(localizacionMapper.toEntity(dto));
        return ResponseEntity.ok(localizacionMapper.toDTO(updatedLocalizacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        localizacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
