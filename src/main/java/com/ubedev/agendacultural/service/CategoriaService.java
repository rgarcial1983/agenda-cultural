package com.ubedev.agendacultural.service;

import com.ubedev.agendacultural.model.Categoria;
import com.ubedev.agendacultural.repository.CategoriaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoriaService {

    private static final Logger logger = LoggerFactory.getLogger(CategoriaService.class);
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listar(String nombre) {
        logger.info("Buscando categorías con nombre: {}", nombre);
        List<Categoria> categorias;
        if (nombre == null || nombre.trim().isEmpty()) {
            logger.info("No se proporcionó nombre, buscando todas las categorías");
            categorias = categoriaRepository.findAllCategorias();
        } else {
            categorias = categoriaRepository.findByNombreContainingIgnoreCase(nombre);
        }
        logger.info("Categorías encontradas: {}", categorias.size());
        return categorias;
    }

    public Categoria crear(Categoria categoria) {
        logger.info("Creando nueva categoría: {}", categoria.getNombre());
        return categoriaRepository.save(categoria);
    }

    public Categoria actualizar(Categoria categoria) {
        logger.info("Actualizando categoría: {}", categoria.getNombre());
        return categoriaRepository.save(categoria);
    }

    public Categoria obtenerPorId(Long id) {
        logger.info("Buscando categoría por ID: {}", id);
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }

    public void eliminar(Long id) {
        logger.info("Eliminando categoría con ID: {}", id);
        categoriaRepository.deleteById(id);
    }
}
