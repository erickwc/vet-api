package org.esfe.servicios.interfaces;

import org.esfe.dtos.raza.RazaGuardar;
import org.esfe.dtos.raza.RazaModificar;
import org.esfe.dtos.raza.RazaSalida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRazaService {

    List<RazaSalida> obtenerTodos();

    Page<RazaSalida> obtenerTodosPaginados(Pageable pageable);

    RazaSalida obtenerPorId(Integer id);

    RazaSalida crear(RazaGuardar razaGuardar);

    RazaSalida editar(RazaModificar razaModificar);

    void eliminarPorId(Integer id);
}

