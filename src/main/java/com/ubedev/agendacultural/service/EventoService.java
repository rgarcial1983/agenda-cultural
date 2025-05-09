package com.ubedev.agendacultural.service;

import com.ubedev.agendacultural.dto.EventoDTO;
import com.ubedev.agendacultural.model.Categoria;
import com.ubedev.agendacultural.model.Evento;
import com.ubedev.agendacultural.model.Localizacion;
import com.ubedev.agendacultural.repository.CategoriaRepository;
import com.ubedev.agendacultural.repository.EventoRepository;
import com.ubedev.agendacultural.repository.LocalizacionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final LocalizacionRepository localizacionRepository;
    private final CategoriaRepository categoriaRepository;

    public EventoService(EventoRepository eventoRepository,
                         LocalizacionRepository localizacionRepository,
                         CategoriaRepository categoriaRepository) {
        this.eventoRepository = eventoRepository;
        this.localizacionRepository = localizacionRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Evento crearEvento(EventoDTO dto) {
        Evento evento = new Evento();
        evento.setTitulo(dto.getTitulo());
        evento.setDescripcion(dto.getDescripcion());
        evento.setFecha(dto.getFecha());

        Localizacion localizacion = localizacionRepository.findById(dto.getLocalizacionId())
                .orElseThrow(() -> new RuntimeException("Localización no encontrada"));
        evento.setLocalizacion(localizacion);

        List<Categoria> categorias = categoriaRepository.findAllById(dto.getCategoriaIds());
        evento.setCategorias(categorias);

        return eventoRepository.save(evento);
    }

    public Page<Evento> listarEventos(String titulo, LocalDate fechaInicio, LocalDate fechaFin, 
                                    Long ciudadId, List<Long> categoriaIds, Pageable pageable) {
        return eventoRepository.findByFiltros(titulo, fechaInicio, fechaFin, ciudadId, categoriaIds, pageable);
    }

    public Evento obtenerEventoPorId(Long id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
    }
}

