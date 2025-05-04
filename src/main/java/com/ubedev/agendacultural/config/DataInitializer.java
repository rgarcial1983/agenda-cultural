package com.ubedev.agendacultural.config;

import com.ubedev.agendacultural.model.Categoria;
import com.ubedev.agendacultural.model.Localizacion;
import com.ubedev.agendacultural.repository.CategoriaRepository;
import com.ubedev.agendacultural.repository.LocalizacionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(CategoriaRepository categoriaRepo, LocalizacionRepository localizacionRepo) {
        return args -> {
            // Categorías de ejemplo
            if (categoriaRepo.count() == 0) {
                categoriaRepo.save(new Categoria(null, "Concierto"));
                categoriaRepo.save(new Categoria(null, "Taller"));
                categoriaRepo.save(new Categoria(null, "Infantil"));
                categoriaRepo.save(new Categoria(null, "Gratuito"));
                categoriaRepo.save(new Categoria(null, "Al aire libre"));
            }

            // Localizaciones de ejemplo
            if (localizacionRepo.count() == 0) {
                localizacionRepo.save(new Localizacion(null, "Auditorio Municipal", "https://maps.google.com/?q=-34.6037,-58.3816"));
                localizacionRepo.save(new Localizacion(null, "Plaza Central", "https://maps.google.com/?q=-34.6047,-58.3825"));
                localizacionRepo.save(new Localizacion(null, "Centro Cultural Sur", "https://maps.google.com/?q=-34.6080,-58.3790"));
            }
        };
    }
}
