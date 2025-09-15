package com.example.api_veterinaria.controller;

import com.example.api_veterinaria.security.JwtUtils;
import com.example.api_veterinaria.model.Usuario;
import com.example.api_veterinaria.repository.UsuarioRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        try {
            // Autenticar con Spring Security
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            // Obtener usuario de la BD
            Usuario usuario = usuarioRepository.findByNickName(request.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

            // Generar JWT
            String token = jwtUtils.generateJwtToken(usuario.getNickName());

            return new LoginResponse(token, "Login exitoso");

        } catch (BadCredentialsException e) {
            throw new RuntimeException("Usuario o clave incorrectos");
        }
    }

    @Data
    public static class LoginRequest {
        private String username;
        private String password;
    }

    @Data
    public static class LoginResponse {
        private String token;
        private String message;

        public LoginResponse(String token, String message) {
            this.token = token;
            this.message = message;
        }
    }
}
