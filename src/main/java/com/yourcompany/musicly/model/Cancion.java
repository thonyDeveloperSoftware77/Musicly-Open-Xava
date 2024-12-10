package com.yourcompany.musicly.model;

import javax.persistence.*;

import com.yourcompany.musicly.calculadores.CalculadorReproduccionesIniciales;
import org.openxava.annotations.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@View(members = "titulo; duracion; genero; album; artistas; reproducciones; popularidad")
@Getter @Setter
public class Cancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String titulo;

    @Column(nullable = false)
    private int duracion; // Duración en segundos

    @ManyToOne(optional = false)
    private Genero genero;

    @ManyToOne(optional = false)
    private Album album;

    @ManyToMany
    private List<Artista> artistas;

    @DefaultValueCalculator(CalculadorReproduccionesIniciales.class)
    @Column(nullable = false)
    private int reproducciones; // Número de reproducciones

    @ReadOnly
    @Calculation("reproducciones")
    private int popularidad; // Popularidad basada en reproducciones

    @Transient
    public List<String> getRolesDeArtistas() {
        return artistas.stream()
                .flatMap(artista -> artista.getRoles().stream())
                .map(Role::getDescripcion)
                .collect(Collectors.toList());
    }
}
