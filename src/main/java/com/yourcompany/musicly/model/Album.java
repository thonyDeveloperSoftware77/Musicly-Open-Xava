package com.yourcompany.musicly.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity
@View(members = "nombre; fechaLanzamiento; artistaPrincipal; canciones")
@Getter @Setter
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 100, nullable = false)
    String nombre;

    @Temporal(TemporalType.DATE)
    Date fechaLanzamiento;

    @ManyToOne(optional = false)
    Artista artistaPrincipal;

    @OneToMany(mappedBy = "album")
    List<Cancion> canciones;
}


