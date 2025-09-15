package com.example.api_veterinaria.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rol")
public class Rol {

    @Id
    @Column(name = "IdRol")
    private Short idRol;

    @Column(name = "Nombre", length = 45, nullable = false, unique = true)
    private String nombre;
}
