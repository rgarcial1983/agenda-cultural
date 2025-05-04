package com.ubedev.agendacultural.config;

import com.ubedev.agendacultural.model.Categoria;
import com.ubedev.agendacultural.model.Ciudad;
import com.ubedev.agendacultural.model.Evento;
import com.ubedev.agendacultural.model.Localizacion;
import com.ubedev.agendacultural.repository.CategoriaRepository;
import com.ubedev.agendacultural.repository.CiudadRepository;
import com.ubedev.agendacultural.repository.EventoRepository;
import com.ubedev.agendacultural.repository.LocalizacionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(EventoRepository eventoRepo, CiudadRepository ciudadRepo, CategoriaRepository categoriaRepo, LocalizacionRepository localizacionRepo) {
        return args -> {
            // Categorías de ejemplo
            if (categoriaRepo.count() == 0) {
                categoriaRepo.save(new Categoria(null, "Concierto"));
                categoriaRepo.save(new Categoria(null, "Taller"));
                categoriaRepo.save(new Categoria(null, "Infantil"));
                categoriaRepo.save(new Categoria(null, "Gratuito"));
                categoriaRepo.save(new Categoria(null, "Al aire libre"));
            }

            // Ciudades de ejemplo
            if (ciudadRepo.count() == 0) {
                ciudadRepo.save(new Ciudad(null, "Úbeda"));
                ciudadRepo.save(new Ciudad(null, "Baeza"));
                ciudadRepo.save(new Ciudad(null, "Linares"));
            }

            // Localizaciones de ejemplo
            if (localizacionRepo.count() == 0) {
                localizacionRepo.save(new Localizacion(null, "Auditorio Municipal", "https://maps.google.com/?q=-34.6037,-58.3816"));
                localizacionRepo.save(new Localizacion(null, "Plaza Central", "https://maps.google.com/?q=-34.6047,-58.3825"));
                localizacionRepo.save(new Localizacion(null, "Centro Cultural Sur", "https://maps.google.com/?q=-34.6080,-58.3790"));
            }

            // Crear algunos eventos de ejemplo
            if (eventoRepo.count() == 0) {
                Evento evento1 = new Evento(
                        null, "Concierto de Música Clásica", "Un evento cultural de música clásica",
                        LocalDate.of(2023, 6, 15),
                        localizacionRepo.findById(1L).orElse(null), ciudadRepo.findById(1L).orElse(null),
                        Arrays.asList(categoriaRepo.findById(1L).orElse(null))
                );

                Evento evento2 = new Evento(
                        null, "Taller de Pintura", "Un taller de pintura para todas las edades",
                        LocalDate.of(2023, 7, 10),
                        localizacionRepo.findById(3L).orElse(null), ciudadRepo.findById(1L).orElse(null),
                        Arrays.asList(categoriaRepo.findById(2L).orElse(null),categoriaRepo.findById(1L).orElse(null))
                );

                eventoRepo.saveAll(Arrays.asList(evento1, evento2));
            }
        };
    }
}
