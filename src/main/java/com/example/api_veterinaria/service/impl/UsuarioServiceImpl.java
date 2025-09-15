package com.example.api_veterinaria.service.impl;

import com.example.api_veterinaria.dto.UsuarioDTO;
import com.example.api_veterinaria.mapper.UsuarioMapper;
import com.example.api_veterinaria.model.Estado;
import com.example.api_veterinaria.model.Rol;
import com.example.api_veterinaria.model.Usuario;
import com.example.api_veterinaria.repository.EstadoRepository;
import com.example.api_veterinaria.repository.RolRepository;
import com.example.api_veterinaria.repository.UsuarioRepository;
import com.example.api_veterinaria.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepo;
    private final RolRepository rolRepo;
    private final EstadoRepository estadoRepo;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepo, RolRepository rolRepo, EstadoRepository estadoRepo) {
        this.usuarioRepo = usuarioRepo;
        this.rolRepo = rolRepo;
        this.estadoRepo = estadoRepo;
    }

    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO dto) {
        Rol rol = rolRepo.findById(dto.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        Estado estado = estadoRepo.findById(dto.getEstadoId())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        Usuario usuario = UsuarioMapper.toEntity(dto, rol, estado);
        usuarioRepo.save(usuario);
        return UsuarioMapper.toDto(usuario);
    }

    @Override
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepo.findAll().stream().map(UsuarioMapper::toDto).toList();
    }

    @Override
    public Optional<UsuarioDTO> buscarPorId(Integer id) {
        return usuarioRepo.findById(id).map(UsuarioMapper::toDto);
    }

    @Override
    public void eliminarUsuario(Integer id) {
        usuarioRepo.deleteById(id);
    }
}
