package com.example.ms_profesionales.controller;

import com.example.ms_profesionales.model.MsProfesional;
import com.example.ms_profesionales.service.MsProfesionalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesionales")
public class MsProfesionalController {

    private final MsProfesionalService service;

    private MsProfesionalController(MsProfesionalService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<MsProfesional>> listar(){
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MsProfesional> buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody MsProfesional profesional) {
        try {
            if (profesional.getNombre() == null ||
                    profesional.getEspecialidad() == null ||
                    profesional.getCorreo() == null) {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("TODOS LOS CAMPOS OBLIGATORIOS DEBEN ESTAR COMPLETOS");
            }

            MsProfesional nuevo = service.guardar(profesional);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("ERROR AL CREAR EL PROFESIONAL");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody MsProfesional profesional) {
        MsProfesional actualizado = service.actualizar(id, profesional);

        if (actualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("PROFESIONAL NO ENCONTRADO");
        }

        return ResponseEntity.ok(actualizado);
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
