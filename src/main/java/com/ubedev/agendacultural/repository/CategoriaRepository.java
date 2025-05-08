package com.ubedev.agendacultural.repository;

import com.ubedev.agendacultural.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByNombreContainingIgnoreCase(String nombre);

    Optional<Categoria> findByNombre(String string);
}
