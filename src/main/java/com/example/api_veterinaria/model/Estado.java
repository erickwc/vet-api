package com.example.api_veterinaria.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "estado")
public class Estado {

    @Id
    @Column(name = "IdEstado")
    private Short  idEstado;

    @Column(name = "Nombre", length = 45, nullable = false, unique = true)
    private String nombre;
}
