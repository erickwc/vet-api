package com.example.api_veterinaria.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UsuarioDTO {
    private Integer usuarioId;
    private String nickName;
    private String correo;
    private String nombreCompleto;
    private String telefono;
    private String direccion;
    private LocalDate fechaNacimiento;
    private Short rolId;
    private Short estadoId;
}
