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
    CommandLineRunner initData(EventoRepository eventoRepo, 
                             CiudadRepository ciudadRepo, 
                             CategoriaRepository categoriaRepo, 
                             LocalizacionRepository localizacionRepo) {
        return args -> {
            // Categorías básicas
            if (categoriaRepo.count() == 0) {
                categoriaRepo.save(new Categoria(null, "Concierto"));
                categoriaRepo.save(new Categoria(null, "Taller"));
                categoriaRepo.save(new Categoria(null, "Gratuito"));
            }

            // Ciudad
            Ciudad ubeda = null;
            if (ciudadRepo.count() == 0) {
                ubeda = ciudadRepo.save(new Ciudad(null, "Úbeda"));
            } else {
                ubeda = ciudadRepo.findByNombre("Úbeda").orElse(null);
            }

            // Localizaciones básicas
            if (localizacionRepo.count() == 0) {
                localizacionRepo.save(new Localizacion(null, "Plaza Vázquez de Molina", "https://goo.gl/maps/8XJZQZQZQZQZQZQZQ"));
                localizacionRepo.save(new Localizacion(null, "Palacio de las Cadenas", "https://goo.gl/maps/9XJZQZQZQZQZQZQZQ"));
            }

            // Eventos básicos
            if (eventoRepo.count() == 0) {
                Categoria concierto = categoriaRepo.findByNombre("Concierto").orElse(null);
                Categoria gratuito = categoriaRepo.findByNombre("Gratuito").orElse(null);
                Localizacion plaza = localizacionRepo.findByLugar("Plaza Vázquez de Molina").orElse(null);
                Localizacion palacio = localizacionRepo.findByLugar("Palacio de las Cadenas").orElse(null);

                Evento evento1 = new Evento(
                    null, 
                    "Concierto de Música Renacentista",
                    "Concierto especial de música renacentista",
                    LocalDate.of(2024, 6, 15),
                    plaza,
                    ubeda,
                    Arrays.asList(concierto, gratuito)
                );

                Evento evento2 = new Evento(
                    null,
                    "Visita Guiada",
                    "Recorrido por los palacios renacentistas",
                    LocalDate.of(2024, 6, 20),
                    palacio,
                    ubeda,
                    Arrays.asList(gratuito)
                );

                eventoRepo.saveAll(Arrays.asList(evento1, evento2));
            }
        };
    }
}
