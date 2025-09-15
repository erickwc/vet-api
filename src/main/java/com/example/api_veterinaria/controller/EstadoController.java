package com.example.api_veterinaria.controller;

import com.example.api_veterinaria.dto.EstadoDTO;
import com.example.api_veterinaria.service.EstadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estados")
public class EstadoController {

    private final EstadoService estadoService;

    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    // GET /api/estados → listar todos los estados
    @GetMapping
    public ResponseEntity<List<EstadoDTO>> listar() {
        return ResponseEntity.ok(estadoService.listarEstados());
    }
}
