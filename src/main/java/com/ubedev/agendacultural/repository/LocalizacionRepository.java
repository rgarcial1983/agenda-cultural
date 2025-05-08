package com.ubedev.agendacultural.repository;

import com.ubedev.agendacultural.model.Localizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LocalizacionRepository extends JpaRepository<Localizacion, Long> {
    List<Localizacion> findByLugarContainingIgnoreCase(String lugar);

    Optional<Localizacion> findByLugar(String lugar);

    @Query("SELECT l FROM Localizacion l")
    List<Localizacion> findAllLocalizaciones();
}
