package com.ubedev.agendacultural.controller;

import com.ubedev.agendacultural.dto.LocalizacionDTO;
import com.ubedev.agendacultural.model.Localizacion;
import com.ubedev.agendacultural.service.LocalizacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/localizaciones")
public class LocalizacionController {

    private final LocalizacionService localizacionService;

    public LocalizacionController(LocalizacionService localizacionService) {
        this.localizacionService = localizacionService;
    }

    @GetMapping
    public ResponseEntity<List<LocalizacionDTO>> listar(
            @RequestParam(required = false) String lugar) {
        List<Localizacion> localizaciones = localizacionService.listar(lugar);
        List<LocalizacionDTO> dtos = localizaciones.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalizacionDTO> obtener(@PathVariable Long id) {
        Localizacion localizacion = localizacionService.obtenerPorId(id);
        return ResponseEntity.ok(convertirADTO(localizacion));
    }

    @PostMapping
    public ResponseEntity<LocalizacionDTO> crear(@RequestBody LocalizacionDTO dto) {
        Localizacion localizacion = localizacionService.crear(convertirAEntidad(dto));
        return ResponseEntity.ok(convertirADTO(localizacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        localizacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private LocalizacionDTO convertirADTO(Localizacion localizacion) {
        return new LocalizacionDTO(
            localizacion.getId(),
            localizacion.getLugar(),
            localizacion.getEnlaceGoogleMaps()
        );
    }

    private Localizacion convertirAEntidad(LocalizacionDTO dto) {
        return new Localizacion(
            dto.getId(),
            dto.getLugar(),
            dto.getEnlaceGoogleMaps()
        );
    }
}
