package com.ubedev.agendacultural.service;

import com.ubedev.agendacultural.dto.CiudadDTO;
import com.ubedev.agendacultural.model.Ciudad;
import com.ubedev.agendacultural.repository.CiudadRepository;
import com.ubedev.agendacultural.mapper.CiudadMapper;
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
    private final CiudadMapper ciudadMapper;

    public CiudadService(CiudadRepository ciudadRepository, CiudadMapper ciudadMapper) {
        this.ciudadRepository = ciudadRepository;
        this.ciudadMapper = ciudadMapper;
    }

    public List<CiudadDTO> listar(String nombre) {
        logger.info("Buscando ciudades con nombre: {}", nombre);
        List<Ciudad> ciudades;
        if (nombre == null || nombre.trim().isEmpty()) {
            logger.info("No se proporcionó nombre, buscando todas las ciudades");
            ciudades = ciudadRepository.findAllCiudades();
        } else {
 ciudades = ciudadRepository.findByNombreContainingIgnoreCase(nombre);
        }
        logger.info("Ciudades encontradas: {}", ciudades.size());
 return ciudades.stream()
 .map(ciudadMapper::toDTO)
 .toList();
    }

    public CiudadDTO crear(CiudadDTO ciudadDTO) {
        logger.info("Creando nueva ciudad: {}", ciudadDTO.getNombre());
        Ciudad ciudad = ciudadMapper.toEntity(ciudadDTO);
        Ciudad savedCiudad = ciudadRepository.save(ciudad);
        return ciudadMapper.toDTO(savedCiudad);
    }

    public CiudadDTO actualizar(Long id, CiudadDTO ciudadDTO) {
        logger.info("Actualizando ciudad con ID: {}", id);
        Ciudad existingCiudad = obtenerCiudadPorId(id); // Use internal method to get entity
        Ciudad updatedCiudad = ciudadRepository.save(existingCiudad);
        return ciudadMapper.toDTO(updatedCiudad);
    }

    public Ciudad obtenerPorId(Long id) {
        logger.info("Buscando ciudad por ID: {}", id);
        return ciudadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
    }

    private Ciudad obtenerCiudadPorId(Long id) {
        logger.info("Buscando ciudad por ID: {}", id);
        return ciudadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
    }

    public void eliminar(Long id) {
        logger.info("Eliminando ciudad con ID: {}", id);
        ciudadRepository.deleteById(id);
    }
}
