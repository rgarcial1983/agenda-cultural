package com.ubedev.agendacultural.repository;

import com.ubedev.agendacultural.model.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CiudadRepository extends JpaRepository<Ciudad, Long> {
    List<Ciudad> findByNombreContainingIgnoreCase(String nombre);
}
