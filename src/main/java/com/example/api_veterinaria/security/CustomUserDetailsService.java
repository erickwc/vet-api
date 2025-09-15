package com.example.api_veterinaria.security;

import com.example.api_veterinaria.model.Usuario;
import com.example.api_veterinaria.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Intentando login con: " + username);

        Usuario usuario = usuarioRepository.findByNickName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        System.out.println("Usuario encontrado en BD: " + usuario.getNickName() + " / clave: " + usuario.getClave());

        return User.builder()
                .username(usuario.getNickName())
                .password(usuario.getClave())
                .roles(usuario.getRol().getNombre())
                .build();
    }

}
