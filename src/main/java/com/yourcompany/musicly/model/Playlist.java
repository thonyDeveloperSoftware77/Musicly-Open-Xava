package com.yourcompany.musicly.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity
@View(members = "nombre; canciones; duracionTotal")
@Getter @Setter
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 100, nullable = false)
    String nombre;

    @ManyToMany
    List<Cancion> canciones;

    @Depends("canciones")
    public int getDuracionTotal() {
        return canciones.stream()
                        .mapToInt(Cancion::getDuracion)
                        .sum(); // Suma de todas las duraciones de las canciones
    }
}


