package com.example.api_veterinaria.controller;

import com.example.api_veterinaria.dto.RolDTO;
import com.example.api_veterinaria.service.RolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    // GET /api/roles → listar todos los roles
    @GetMapping
    public ResponseEntity<List<RolDTO>> listar() {
        return ResponseEntity.ok(rolService.listarRoles());
    }
}
