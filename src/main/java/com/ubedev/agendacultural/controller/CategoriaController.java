package com.ubedev.agendacultural.controller;

import com.ubedev.agendacultural.dto.categoria.CategoriaCreateDTO;
import com.ubedev.agendacultural.dto.categoria.CategoriaResponseDTO;
import com.ubedev.agendacultural.dto.categoria.CategoriaUpdateDTO;
import com.ubedev.agendacultural.mapper.CategoriaMapper;
import com.ubedev.agendacultural.model.Categoria;
import com.ubedev.agendacultural.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categorias")
@Tag(name = "Categorías", description = "API para gestionar categorías de eventos")
public class CategoriaController {

    private final CategoriaService categoriaService;
    private final CategoriaMapper categoriaMapper;

    public CategoriaController(CategoriaService categoriaService, CategoriaMapper categoriaMapper) {
        this.categoriaService = categoriaService;
        this.categoriaMapper = categoriaMapper;
    }

    @GetMapping
    @Operation(summary = "Listar categorías", description = "Obtiene una lista de categorías, opcionalmente filtrada por nombre")
    public ResponseEntity<List<CategoriaResponseDTO>> listar(
            @RequestParam(required = false) String nombre) {
        List<Categoria> categorias = categoriaService.listar(nombre);
        List<CategoriaResponseDTO> dtos = categorias.stream()
                .map(categoriaMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener categoría", description = "Obtiene una categoría por su ID")
    public ResponseEntity<CategoriaResponseDTO> obtener(@PathVariable Long id) {
        Categoria categoria = categoriaService.obtenerPorId(id);
        return ResponseEntity.ok(categoriaMapper.toResponseDTO(categoria));
    }

    @PostMapping
    @Operation(summary = "Crear categoría", description = "Crea una nueva categoría")
    public ResponseEntity<CategoriaResponseDTO> crear(@Valid @RequestBody CategoriaCreateDTO dto) {
        Categoria categoria = categoriaService.crear(categoriaMapper.toEntity(dto));
        return ResponseEntity.ok(categoriaMapper.toResponseDTO(categoria));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar categoría", description = "Actualiza una categoría existente")
    public ResponseEntity<CategoriaResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaUpdateDTO dto) {
        Categoria categoria = categoriaService.obtenerPorId(id);
        categoriaMapper.updateEntityFromDTO(dto, categoria);
        categoria = categoriaService.actualizar(categoria);
        return ResponseEntity.ok(categoriaMapper.toResponseDTO(categoria));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar categoría", description = "Elimina una categoría por su ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        categoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
