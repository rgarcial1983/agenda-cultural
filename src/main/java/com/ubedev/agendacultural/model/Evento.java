package com.ubedev.agendacultural.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data // Incluye getters, setters, toString, equals y hashCode
@NoArgsConstructor // Constructor vacío necesario para JPA
@AllArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    private String lugar;
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "localizacion_id")
    private Localizacion localizacion;

    @ManyToOne
    @JoinColumn(name = "ciudad_id")
    private Ciudad ciudad;

    @ManyToMany
    @JoinTable(
            name = "evento_categoria",
            joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )

    private List<Categoria> categorias = new ArrayList<>();

}
