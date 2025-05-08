package com.ubedev.agendacultural.repository;

import com.ubedev.agendacultural.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByNombreContainingIgnoreCase(String nombre);

    Optional<Categoria> findByNombre(String string);
    
    @Query("SELECT c FROM Categoria c")
    List<Categoria> findAllCategorias();
}
