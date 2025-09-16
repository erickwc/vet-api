package org.esfe.controladores;

import org.esfe.dtos.raza.RazaGuardar;
import org.esfe.dtos.raza.RazaModificar;
import org.esfe.dtos.raza.RazaSalida;
import org.esfe.servicios.interfaces.IRazaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/razas")
public class RazaController {

    @Autowired
    private IRazaService razaService;

    @GetMapping
    public ResponseEntity<Page<RazaSalida>> mostrarTodosPaginados(Pageable pageable) {
        Page<RazaSalida> razas = razaService.obtenerTodosPaginados(pageable);
        if (razas.hasContent()) {
            return ResponseEntity.ok(razas);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/lista")
    public ResponseEntity<List<RazaSalida>> mostrarTodos(){
       List<RazaSalida> raza = razaService.obtenerTodos();
        if (!raza.isEmpty()){
            return ResponseEntity.ok(raza);
        }
        return  ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RazaSalida> buscarPorId(@PathVariable Integer id){
        RazaSalida raza = razaService.obtenerPorId(id);
        if(raza != null){
            return ResponseEntity.ok(raza);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<RazaSalida> crear(@RequestBody RazaGuardar razaGuardar){
        RazaSalida raza = razaService.crear(razaGuardar);
        return ResponseEntity.ok(raza);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RazaSalida> editar(@PathVariable Integer id, @RequestBody RazaModificar razaModificar){
        RazaSalida raza = razaService.editar(razaModificar);
        return ResponseEntity.ok(raza);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Integer id){
        razaService.eliminarPorId(id);
        return ResponseEntity.ok().body("Raza eliminada correctamente");
    }


}
