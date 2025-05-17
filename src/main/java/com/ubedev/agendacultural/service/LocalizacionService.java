package com.ubedev.agendacultural.service;

import com.ubedev.agendacultural.dto.LocalizacionDTO;
import com.ubedev.agendacultural.model.Localizacion;
import com.ubedev.agendacultural.mapper.LocalizacionMapper;
import com.ubedev.agendacultural.repository.LocalizacionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LocalizacionService {

    private static final Logger logger = LoggerFactory.getLogger(LocalizacionService.class);
    private final LocalizacionRepository localizacionRepository;

    public LocalizacionService(LocalizacionRepository localizacionRepository) {
        this.localizacionRepository = localizacionRepository;
    }

    public List<Localizacion> listar(String lugar) {
        logger.info("Buscando localizaciones con lugar: {}", lugar);
        List<Localizacion> localizaciones;
        if (lugar == null || lugar.trim().isEmpty()) {
            logger.info("No se proporcionó lugar, buscando todas las localizaciones");
            localizaciones = localizacionRepository.findAllLocalizaciones();
        } else {
            localizaciones = localizacionRepository.findByLugarContainingIgnoreCase(lugar);
        }
        logger.info("Localizaciones encontradas: {}", localizaciones.size());
        return localizaciones;
    }

    public Localizacion crear(Localizacion localizacion) {
        logger.info("Creando nueva localización: {}", localizacion.getLugar());
        return localizacionRepository.save(localizacion);
    }

    public Localizacion actualizar(Localizacion localizacion) {
        logger.info("Actualizando localización: {}", localizacion.getLugar());
        return localizacionRepository.save(localizacion);
    }

    public Localizacion obtenerPorId(Long id) {
        logger.info("Buscando localización por ID: {}", id);
        return localizacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Localización no encontrada"));
    }

    public void eliminar(Long id) {
        logger.info("Eliminando localización con ID: {}", id);
        localizacionRepository.deleteById(id);
    }
}
