package com.example.ms_profesionales.controller;

import com.example.ms_profesionales.model.MsProfesional;
import com.example.ms_profesionales.service.MsProfesionalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//MAPEO DE ENDPOINTS:
//GET    http://localhost:8081/redsalud/v1/profesinales               -> listar todas
//GET    http://localhost:8081/redsalud/v1/profesionales/{id}         -> obtener una (404 si no existe)
//POST    http://localhost:8081/redsalud/v1/profesionales             -> crear (201 Created)
//PUT     http://localhost:8081/redsalud/v1/profesionales/{id}        -> actualizar
//DELETE  http://localhost:8081redsalud/v1/profesionales/{id}         -> eliminar (204 No Content)
@RestController
@RequestMapping("/redsalud/v1/profesionales") // Se define la ruta base para todos los endpoints de este controlador
public class MsProfesionalController {

    private final MsProfesionalService service;  // Inyección del servicio que maneja la lógica de negocio

    private MsProfesionalController(MsProfesionalService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<MsProfesional>> listar(){return ResponseEntity.ok(service.listarTodos()); // Llama al servicio para obtener a los profesionales
    }

    @GetMapping("/{id}")
    public ResponseEntity<MsProfesional> buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)// Si existe, devuelve 200 OK con el objeto
                .orElse(ResponseEntity.notFound().build()); // Si no existe, devuelve 404 Not Found
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody MsProfesional profesional) {
        try {
            // Validación manual de campos obligatorios
            if (profesional.getNombre() == null ||
                    profesional.getEspecialidad() == null ||
                    profesional.getCorreo() == null) {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("TODOS LOS CAMPOS OBLIGATORIOS DEBEN ESTAR COMPLETOS");
            }

            MsProfesional nuevo = service.guardar(profesional);  // Guarda en BD
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("ERROR AL CREAR EL PROFESIONAL");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody MsProfesional profesional) {
        MsProfesional actualizado = service.actualizar(id, profesional); // Actualiza campos de la entidad

        if (actualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("PROFESIONAL NO ENCONTRADO");
        }

        return ResponseEntity.ok(actualizado); // Devuelve objeto actualizado
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            if (service.buscarPorId(id).isPresent()) {
                service.eliminar(id);
                return ResponseEntity.ok("PROFESIONAL ELIMINADO CORRECTAMENTE");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("PROFESIONAL NO ENCONTRADO");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("ERROR AL ELIMINAR EL PROFESIONAL");
        }
    }
}
