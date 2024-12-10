package com.yourcompany.musicly.model;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter @Setter
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String descripcion; // Ejemplo: "Vocalista", "Productor"

    @ManyToOne
    private Artista artista;
}
