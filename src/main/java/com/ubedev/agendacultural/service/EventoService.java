package com.ubedev.agendacultural.service;

import com.ubedev.agendacultural.dto.EventoDTO;
import com.ubedev.agendacultural.model.Categoria;
import com.ubedev.agendacultural.mapper.EventoMapper;
import com.ubedev.agendacultural.model.Evento;
import com.ubedev.agendacultural.model.Localizacion;
import com.ubedev.agendacultural.repository.CategoriaRepository;
import com.ubedev.agendacultural.repository.EventoRepository;
import com.ubedev.agendacultural.repository.LocalizacionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        Evento evento = eventoMapper.toEntity(dto);

        Localizacion localizacion = localizacionRepository.findById(dto.getLocalizacionId())
                .orElseThrow(() -> new RuntimeException("Localización no encontrada"));
        evento.setLocalizacion(localizacion);

        if (dto.getCategoriaIds() != null && !dto.getCategoriaIds().isEmpty()) {
            List<Categoria> categorias = categoriaRepository.findAllById(dto.getCategoriaIds());
            evento.setCategorias(categorias);
        }

        Evento savedEvento = eventoRepository.save(evento);
        return eventoMapper.toDTO(savedEvento);
    }

    public Page<EventoDTO> listarEventos(String titulo, LocalDate fechaInicio, LocalDate fechaFin,
                                         Long ciudadId, List<Long> categoriaIds, Pageable pageable) {
        Page<Evento> eventosPage = eventoRepository.findByFiltros(titulo, fechaInicio, fechaFin, ciudadId, categoriaIds, pageable);
        List<EventoDTO> eventoDTOs = eventosPage.getContent().stream()
                .map(eventoMapper::toDTO)
                .toList();
        return new PageImpl<>(eventoDTOs, pageable, eventosPage.getTotalElements());
    }

    public EventoDTO obtenerPorId(Long id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        return eventoMapper.toDTO(evento);
    }

    public EventoDTO actualizar(Long id, EventoDTO dto) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        // MapStruct can handle updating the existing entity from the DTO
        eventoMapper.updateEntityFromDTO(dto, evento);
        Evento updatedEvento = eventoRepository.save(evento);
        return eventoMapper.toDTO(updatedEvento);
    }

    public void eliminar(Long id) {
        eventoRepository.deleteById(id);
    }
}