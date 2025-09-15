// EstadoRepository.java
package com.example.api_veterinaria.repository;

import com.example.api_veterinaria.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Short> { }
