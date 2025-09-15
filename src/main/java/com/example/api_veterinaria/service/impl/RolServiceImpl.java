package com.example.api_veterinaria.service.impl;

import com.example.api_veterinaria.dto.RolDTO;
import com.example.api_veterinaria.model.Rol;
import com.example.api_veterinaria.repository.RolRepository;
import com.example.api_veterinaria.service.RolService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepo;

    public RolServiceImpl(RolRepository rolRepo) {
        this.rolRepo = rolRepo;
    }

    @Override
    public List<RolDTO> listarRoles() {
        return rolRepo.findAll().stream()
                .map(rol -> {
                    RolDTO dto = new RolDTO();
                    dto.setIdRol(rol.getIdRol());
                    dto.setNombre(rol.getNombre());
                    return dto;
                })
                .toList();
    }
}
