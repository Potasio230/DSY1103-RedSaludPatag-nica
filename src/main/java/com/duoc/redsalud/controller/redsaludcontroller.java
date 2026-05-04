package com.duoc.redsalud.controller;

import com.duoc.redsalud.model.redsalud;
import com.duoc.redsalud.service.redsaludservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/redsalud/pacientes")
public class redsaludcontroller {

    private final redsaludservice service;

    public redsaludcontroller(redsaludservice service) {
        this.service = service;
    }

    // LISTA A TODOS LOS PACIENTES
    @GetMapping
    public ResponseEntity<?> listarTodos() {
        try {
            List<redsalud> redsalud = service.listarTodos();
            return ResponseEntity.ok().body(redsalud);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar los pacientes");
        }
    }

    // BUSCA LA FICHA DEL PACIENTE POR SU ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            redsalud redsalud = service.buscarPorId(id);

            if  (redsalud == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontro el ID del paciente");
            }
            return ResponseEntity.ok(redsalud);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error del servidor");
        }
    }

    // CREA LA FICHA DEL PACIENTE CON LOS CAMPOS QUE SI SON OBLIGATORIOS
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody  redsalud paciente) {
        try {
            if (paciente.getNombre() == null ||
                    paciente.getDireccion() == null ||
                    paciente.getFechaNacimiento() == null) {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Nombre, Direccion, Fecha nacimiento son campos obligatorios");
            }

            redsalud nuevoPaciente = service.crear(paciente);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPaciente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la ficha del paciente");
        }

    }

    // ACTUALIZA LOS DATOS DEL PACIENTE
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody  redsalud paciente) {
        redsalud actualizado = service.actualizar(id, paciente);

        if (actualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontro el ID del paciente");
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    // EN CASO DE NO ENCONTRAR EL ID DEL PACIENTE, DARA UN ERROR DE NOT FOUND EN LA TERMINAL
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No se encontro el ID del paciente");
    }
}
