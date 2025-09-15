package com.example.api_veterinaria.service.impl;

import com.example.api_veterinaria.dto.EstadoDTO;
import com.example.api_veterinaria.model.Estado;
import com.example.api_veterinaria.repository.EstadoRepository;
import com.example.api_veterinaria.service.EstadoService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EstadoServiceImpl implements EstadoService {

    private final EstadoRepository estadoRepo;

    public EstadoServiceImpl(EstadoRepository estadoRepo) {
        this.estadoRepo = estadoRepo;
    }

    @Override
    public List<EstadoDTO> listarEstados() {
        return estadoRepo.findAll().stream()
                .map(estado -> {
                    EstadoDTO dto = new EstadoDTO();
                    dto.setIdEstado(estado.getIdEstado());
                    dto.setNombre(estado.getNombre());
                    return dto;
                })
                .toList();
    }
}
