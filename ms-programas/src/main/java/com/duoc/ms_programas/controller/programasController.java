package com.duoc.ms_programas.controller;

import com.duoc.ms_programas.model.programas;
import com.duoc.ms_programas.service.programasService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/redsalud/v1/programas")
public class programasController {

    private final programasService service;

    public programasController(programasService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> listarTodos(){
        try {
            List<programas> programas = service.listarTodos();
            return ResponseEntity.ok()
                    .body("Programas listados correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar los programas");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        try {
            programas programas = service.buscarPorId(id);

            if (programas == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontro el ID del programa");
            }
            return ResponseEntity.ok().body(programas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error del servidor");
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody programas programas){
        try {
            if (programas.getNombrePrograma() == null ||
                    programas.getNombreEncargado() == null ||
                    programas.getTipoPrograma() == null ||
                    programas.getLugarPrograma() == null ||
                    programas.getFechaPrograma() == null) {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Faltan rellenar campos obligatorios");
            }

            programas nuevoPrograma = service.crear(programas);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Programa creado exitosamente");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el programa");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody programas programas){
        programas actualizado = service.actualizar(id, programas);

        if (actualizado == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontro el ID del programa");
        }

        return ResponseEntity.ok().body(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        boolean eliminado = service.eliminar(id);

        if (eliminado){
            return ResponseEntity.ok("Paciente eliminado correctamente");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Paciente no encontrado");
    }

}
