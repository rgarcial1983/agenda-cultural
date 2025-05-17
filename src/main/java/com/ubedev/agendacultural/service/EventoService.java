package com.ubedev.agendacultural.service;

import com.ubedev.agendacultural.dto.CategoriaDTO;
import com.ubedev.agendacultural.dto.EventoDTO;
import com.ubedev.agendacultural.mapper.EventoMapper;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final LocalizacionRepository localizacionRepository;
    private final CategoriaRepository categoriaRepository;

    private final EventoMapper eventoMapper;

    public EventoService(EventoRepository eventoRepository,
                         LocalizacionRepository localizacionRepository,
                         CategoriaRepository categoriaRepository,
                         EventoMapper eventoMapper) {
        this.eventoRepository = eventoRepository;
        this.localizacionRepository = localizacionRepository;
        this.categoriaRepository = categoriaRepository;
        this.eventoMapper = eventoMapper;
    }

    public EventoDTO crear(EventoDTO dto) {
        Evento evento = new Evento();
        evento.setTitulo(dto.getTitulo());
        evento.setDescripcion(dto.getDescripcion());
        evento.setFecha(dto.getFecha());

        Localizacion localizacion = localizacionRepository.findById(dto.getLocalizacion().getId())
                .orElseThrow(() -> new RuntimeException("Localización no encontrada"));
        evento.setLocalizacion(localizacion);

        List<Categoria> categorias = new ArrayList<>();
        for (CategoriaDTO c : dto.getCategorias()) {
            categoriaRepository.findById(c.getId()).ifPresent(categorias::add);
        }
        evento.setCategorias(categorias);

        return eventoMapper.toDTO(eventoRepository.save(evento));
    }

    public Page<Evento> listarEventos(String titulo, LocalDate fechaInicio, LocalDate fechaFin,
                                      Long ciudadId, List<Long> categoriaIds, Pageable pageable) {
        return eventoRepository.findByFiltros(titulo, fechaInicio, fechaFin, ciudadId, categoriaIds, pageable);
    }

    public EventoDTO obtenerPorId(Long id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        return eventoMapper.toDTO(evento);
    }

    public EventoDTO actualizar(Long id, EventoDTO dto) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        Evento updatedEvento = eventoRepository.save(evento);
        return eventoMapper.toDTO(updatedEvento);
    }

    public void eliminar(Long id) {
        eventoRepository.deleteById(id);
    }
}
