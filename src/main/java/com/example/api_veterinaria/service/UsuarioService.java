package com.example.api_veterinaria.service;

import com.example.api_veterinaria.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    UsuarioDTO crearUsuario(UsuarioDTO dto);
    List<UsuarioDTO> listarUsuarios();
    Optional<UsuarioDTO> buscarPorId(Integer id);
    void eliminarUsuario(Integer id);
    UsuarioDTO actualizarUsuario(Integer id, UsuarioDTO dto);

}
