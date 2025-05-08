package com.ubedev.agendacultural.repository;

import com.ubedev.agendacultural.model.Localizacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LocalizacionRepository extends JpaRepository<Localizacion, Long> {
    List<Localizacion> findByLugarContainingIgnoreCase(String lugar);

    Optional<Localizacion> findByLugar(String lugar);
}
