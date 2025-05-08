package com.ubedev.agendacultural.service;

import com.ubedev.agendacultural.model.Ciudad;
import com.ubedev.agendacultural.repository.CiudadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiudadService {

    private final CiudadRepository ciudadRepository;

    public CiudadService(CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
    }

    public List<Ciudad> listar(String nombre) {
        return ciudadRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public Ciudad crear(Ciudad ciudad) {
        return ciudadRepository.save(ciudad);
    }

    public Ciudad obtenerPorId(Long id) {
        return ciudadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
    }

    public void eliminar(Long id) {
        ciudadRepository.deleteById(id);
    }
}
