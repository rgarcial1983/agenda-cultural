package com.ubedev.agendacultural.repository;

import com.ubedev.agendacultural.model.Evento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long>, JpaSpecificationExecutor<Evento> {

    @Query("SELECT e FROM Evento e WHERE " +
           "(:titulo IS NULL OR LOWER(e.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))) AND " +
           "(:fechaInicio IS NULL OR e.fecha >= :fechaInicio) AND " +
           "(:fechaFin IS NULL OR e.fecha <= :fechaFin) AND " +
           "(:ciudadId IS NULL OR e.ciudad.id = :ciudadId) AND " +
           "(:categoriaIds IS NULL OR EXISTS (SELECT c FROM e.categorias c WHERE c.id IN :categoriaIds))")
    Page<Evento> findByFiltros(
            @Param("titulo") String titulo,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin,
            @Param("ciudadId") Long ciudadId,
            @Param("categoriaIds") List<Long> categoriaIds,
            Pageable pageable);
}
