package com.ubedev.agendacultural.service;

import com.ubedev.agendacultural.model.Ciudad;
import com.ubedev.agendacultural.repository.CiudadRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CiudadService {

    private static final Logger logger = LoggerFactory.getLogger(CiudadService.class);
    private final CiudadRepository ciudadRepository;

    public CiudadService(CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
    }

    public List<Ciudad> listar(String nombre) {
        logger.info("Buscando ciudades con nombre: {}", nombre);
        List<Ciudad> ciudades;
        if (nombre == null || nombre.trim().isEmpty()) {
            logger.info("No se proporcionó nombre, buscando todas las ciudades");
            ciudades = ciudadRepository.findAllCiudades();
        } else {
            ciudades = ciudadRepository.findByNombreContainingIgnoreCase(nombre);
        }
        logger.info("Ciudades encontradas: {}", ciudades.size());
        return ciudades;
    }

    public Ciudad crear(Ciudad ciudad) {
        logger.info("Creando nueva ciudad: {}", ciudad.getNombre());
        return ciudadRepository.save(ciudad);
    }

    public Ciudad actualizar(Ciudad ciudad) {
        logger.info("Actualizando ciudad: {}", ciudad.getNombre());
        return ciudadRepository.save(ciudad);
    }

    public Ciudad obtenerPorId(Long id) {
        logger.info("Buscando ciudad por ID: {}", id);
        return ciudadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
    }

    public void eliminar(Long id) {
        logger.info("Eliminando ciudad con ID: {}", id);
        ciudadRepository.deleteById(id);
    }
}
