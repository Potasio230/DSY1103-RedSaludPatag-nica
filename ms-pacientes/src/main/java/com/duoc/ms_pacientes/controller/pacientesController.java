package com.duoc.ms_pacientes.controller;

import com.duoc.ms_pacientes.model.pacientes;
import com.duoc.ms_pacientes.service.pacientesService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/redsalud/pacientes")
public class pacientesController {

    private final pacientesService service;

    public pacientesController(pacientesService service){
        this.service = service;
    }

    // LISTA A TODOS LOS PACIENTES
    @GetMapping
    public ResponseEntity<?> listarTodos(){
        try {
            List<pacientes> pacientes = service.listarTodos();
            return ResponseEntity.ok()
                    .body("Pacientes listados correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar los pacientes");
        }
    }

    // BUSCA LA FICHA DEL PACIENTE POR SU ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        try {
            pacientes pacientes = service.buscarPorId(id);

            if (pacientes == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontro el ID del paciente");
            }
            return ResponseEntity.ok().body(pacientes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error del servidor");
        }
    }

    // CREA LA FICHA DEL PACIENTE CON LOS CAMPOS QUE SI SON OBLIGATORIOS
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody pacientes paciente){
        try {
            if (paciente.getNombre() == null ||
                    paciente.getDireccion() == null ||
                    paciente.getTelefono() == null ||
                    paciente.getEmail() == null ||
                    paciente.getFechaNacimiento() == null ||
                    paciente.getResidencia() == null) {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Faltan rellenar campos obligatorios");
            }

            pacientes nuevoPaciente = service.crear(paciente);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Paciente creado exitosamente");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear paciente");
        }
    }

    // ACTUALIZA LOS DATOS DEL PACIENTE
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody pacientes paciente){
        pacientes actualizado = service.actualizar(id, paciente);

        if (actualizado == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontro el ID del paciente");
        }

        return ResponseEntity.ok().body(actualizado);
    }

    // EN CASO DE NO ENCONTRAR EL ID DEL PACIENTE, DARA UN ERROR DE NOT FOUND EN LA TERMINAL
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
