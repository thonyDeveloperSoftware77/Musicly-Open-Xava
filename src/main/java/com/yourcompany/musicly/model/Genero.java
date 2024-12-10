package com.yourcompany.musicly.model;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity
@View(members = "descripcion")
@Getter @Setter
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 50, nullable = false, unique = true)
    String descripcion;
}


