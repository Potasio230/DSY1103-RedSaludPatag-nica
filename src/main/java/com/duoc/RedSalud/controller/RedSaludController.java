package com.duoc.RedSalud.controller;

import com.duoc.RedSalud.model.RedSalud;
import com.duoc.RedSalud.repository.RedSaludRepository;
import com.duoc.RedSalud.service.RedSaludService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/redsalud/pacientes")
public class RedSaludController {
    private final RedSaludService redSaludService;

    public RedSaludController(RedSaludService redSaludService) {
        this.redSaludService = redSaludService;
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        try {
            List<RedSalud> redSalud = redSaludService.listarTodos();
            return ResponseEntity.ok().body(redSalud);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR AL LISTAR LOS PACIENTES");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            RedSalud redSalud = redSaludService.buscarPorId(id);

            if (redSalud == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR PACIENTE NO ENCONTRADO");
            }
            return ResponseEntity.ok(redSalud);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("EL PACIENTE NO FUE ENCONTRADO");
        }
    }

    // CREAR FICHA DE PACIENTE
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody RedSalud redSalud) {
        try {
            if (redSalud.getNombre() == null ||
                redSalud.getDireccion() == null ||
                redSalud.getFechaNacimiento() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TODOS LOS CAMPOS SON OBLIGATORIOS");
            }

            if (redSaludService.existePorId(redSalud.getId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("YA EXISTE ESE PACIENTE CON ESE ID");
            }

            RedSalud nuevoPaciente = redSaludService.crear(redSalud);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPaciente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR AL CREAR LA FICHA DEL PACIENTE");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Long id) {
        try {
            if (!redSaludService.existePorId(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PACIENTE NO ENCONTRADO");
            }

            redSaludService.eliminarPaciente(id);
            return ResponseEntity.ok("PACIENTE ELIMINADO CORRECTAMENTE");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR AL ELIMINAR EL PACIENTE");
        }
    }
}
