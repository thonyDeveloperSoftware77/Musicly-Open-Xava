package com.yourcompany.musicly.model;
import java.util.*;
import java.util.stream.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity
@View(members = "titulo; duracion; genero; album; artistas")
@Getter @Setter // Lombok generará los getters y setters
public class Cancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 100, nullable = false)
    String titulo;

    @Column(nullable = false)
    int duracion; // Duración en segundos

    @ManyToOne(optional = false)
    Genero genero;

    @ManyToOne(optional = false)
    Album album;

    @ManyToMany
    List<Artista> artistas;
    
    @Transient
    public List<String> getRolesDeArtistas() {
        return artistas.stream()
                       .flatMap(artista -> artista.getRoles().stream())
                       .map(Role::getDescripcion)
                       .collect(Collectors.toList());
    }
}



