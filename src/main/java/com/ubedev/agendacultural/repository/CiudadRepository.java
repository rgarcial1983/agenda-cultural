package com.ubedev.agendacultural.repository;

import com.ubedev.agendacultural.model.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CiudadRepository extends JpaRepository<Ciudad, Long> {
    List<Ciudad> findByNombreContainingIgnoreCase(String nombre);
    Optional<Ciudad> findByNombre(String nombre);
    
    @Query("SELECT c FROM Ciudad c")
    List<Ciudad> findAllCiudades();
}
