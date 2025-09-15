package com.example.api_veterinaria.model;

import jakarta.persistence.*;   // ✅ Bien, se usa en Spring Boot 3+
import java.time.LocalDate;    // ✅ Correcto para fechas modernas

import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usuarioId;

    @ManyToOne
    @JoinColumn(name = "RolId", nullable = false)
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "EstadoId", nullable = false)
    private Estado estado;

    private String nickName;
    private String correo;
    private String clave;
    private String nombreCompleto;
    private String dui;
    private String telefono;
    private String direccion;
    private LocalDate fechaNacimiento;
}
