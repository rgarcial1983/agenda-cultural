package com.ubedev.agendacultural.service;

import com.ubedev.agendacultural.model.Localizacion;
import com.ubedev.agendacultural.repository.LocalizacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalizacionService {

    private final LocalizacionRepository localizacionRepository;

    public LocalizacionService(LocalizacionRepository localizacionRepository) {
        this.localizacionRepository = localizacionRepository;
    }

    public List<Localizacion> listar(String lugar) {
        return localizacionRepository.findByLugarContainingIgnoreCase(lugar);
    }

    public Localizacion crear(Localizacion localizacion) {
        return localizacionRepository.save(localizacion);
    }

    public Localizacion obtenerPorId(Long id) {
        return localizacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Localización no encontrada"));
    }

    public void eliminar(Long id) {
        localizacionRepository.deleteById(id);
    }
}
