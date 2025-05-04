package com.ubedev.agendacultural.repository;

import com.ubedev.agendacultural.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {

}
