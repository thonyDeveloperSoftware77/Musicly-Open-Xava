package com.yourcompany.musicly.model;

import javax.persistence.*;
import org.openxava.annotations.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@View(members = "nombre; canciones; duracionTotal; popularidad")
@Getter @Setter
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 100, nullable = false)
    String nombre;

    @ManyToMany
    List<Cancion> canciones;

    @ReadOnly
    @Calculation("sum(canciones.reproducciones)")
    int popularidad; // Popularidad basada en reproducciones de las canciones

    @Depends("canciones")
    public int getDuracionTotal() {
        return canciones.stream()
                .mapToInt(Cancion::getDuracion)
                .sum(); // Suma de todas las duraciones de las canciones
    }

    @PrePersist
    @PreUpdate
    private void validarDuracionTotal() {
        if (getDuracionTotal() > 10800) { // 3 horas en segundos
            throw new RuntimeException("La duración total de la playlist no puede exceder las 3 horas.");
        }
    }
}
