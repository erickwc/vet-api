package com.example.api_veterinaria.mapper;

import com.example.api_veterinaria.dto.UsuarioDTO;
import com.example.api_veterinaria.model.Estado;
import com.example.api_veterinaria.model.Rol;
import com.example.api_veterinaria.model.Usuario;

public class UsuarioMapper {
    public static UsuarioDTO toDto(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setUsuarioId(usuario.getUsuarioId());
        dto.setNickName(usuario.getNickName());
        dto.setCorreo(usuario.getCorreo());
        dto.setNombreCompleto(usuario.getNombreCompleto());
        dto.setTelefono(usuario.getTelefono());
        dto.setDireccion(usuario.getDireccion());
        dto.setFechaNacimiento(usuario.getFechaNacimiento());
        dto.setRolId(usuario.getRol().getIdRol());
        dto.setEstadoId(usuario.getEstado().getIdEstado());
        return dto;
    }

    public static Usuario toEntity(UsuarioDTO dto, Rol rol, Estado estado) {
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(dto.getUsuarioId());
        usuario.setNickName(dto.getNickName());
        usuario.setCorreo(dto.getCorreo());
        usuario.setClave("hashed"); // luego aplicas hash real
        usuario.setNombreCompleto(dto.getNombreCompleto());
        usuario.setTelefono(dto.getTelefono());
        usuario.setDireccion(dto.getDireccion());
        usuario.setFechaNacimiento(dto.getFechaNacimiento());
        usuario.setRol(rol);
        usuario.setEstado(estado);
        return usuario;
    }
}
