package com.yourcompany.musicly.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity
@View(members = "nombre; biografia; roles")
@Getter @Setter
public class Artista {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 500)
    private String biografia;

    @OneToMany(mappedBy = "artista")
    private List<Role> roles; // Relación con los roles
}
