// UsuarioRepository.java
package com.example.api_veterinaria.repository;

import com.example.api_veterinaria.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByNickName(String nickName);
}
