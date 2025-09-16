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
import org.springframework.security.crypto.password.PasswordEncoder; // <-- importar esta

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepo;
    private final RolRepository rolRepo;
    private final EstadoRepository estadoRepo;
    private final PasswordEncoder passwordEncoder; // <-- declarar aquí

    // Inyectamos PasswordEncoder en el constructor
    public UsuarioServiceImpl(UsuarioRepository usuarioRepo,
                              RolRepository rolRepo,
                              EstadoRepository estadoRepo,
                              PasswordEncoder passwordEncoder) { // <-- Spring lo inyecta
        this.usuarioRepo = usuarioRepo;
        this.rolRepo = rolRepo;
        this.estadoRepo = estadoRepo;
        this.passwordEncoder = passwordEncoder; // <-- asignar
    }

    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO dto) {
        Rol rol = rolRepo.findById(dto.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        Estado estado = estadoRepo.findById(dto.getEstadoId())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        // Hashear contraseña
        String hashedPassword = passwordEncoder.encode(dto.getClave());

        Usuario usuario = UsuarioMapper.toEntity(dto, rol, estado);
        usuario.setClave(hashedPassword);
        usuarioRepo.save(usuario);
        return UsuarioMapper.toDto(usuario);
    }

    @Override
    public UsuarioDTO actualizarUsuario(Integer id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Rol rol = rolRepo.findById(dto.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        Estado estado = estadoRepo.findById(dto.getEstadoId())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        // Actualizar campos
        usuario.setNickName(dto.getNickName());
        usuario.setCorreo(dto.getCorreo());
        usuario.setNombreCompleto(dto.getNombreCompleto());
        usuario.setTelefono(dto.getTelefono());
        usuario.setDireccion(dto.getDireccion());
        usuario.setFechaNacimiento(dto.getFechaNacimiento());
        usuario.setRol(rol);
        usuario.setEstado(estado);

        // Si envías una nueva clave, la hasheamos
        if (dto.getClave() != null && !dto.getClave().isEmpty()) {
            usuario.setClave(passwordEncoder.encode(dto.getClave()));
        }

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
