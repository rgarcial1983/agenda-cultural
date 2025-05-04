package com.ubedev.agendacultural.service;

import com.ubedev.agendacultural.dto.EventoDTO;
import com.ubedev.agendacultural.model.Categoria;
import com.ubedev.agendacultural.model.Evento;
import com.ubedev.agendacultural.model.Localizacion;
import com.ubedev.agendacultural.repository.CategoriaRepository;
import com.ubedev.agendacultural.repository.EventoRepository;
import com.ubedev.agendacultural.repository.LocalizacionRepository;
import org.springframework.stereotype.Service;

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

    public List<Evento> listarEventos() {
        return eventoRepository.findAll();
    }

    public Evento obtenerEventoPorId(Long id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
    }
}

