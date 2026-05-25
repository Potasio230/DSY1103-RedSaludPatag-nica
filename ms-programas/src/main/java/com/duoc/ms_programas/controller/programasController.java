package com.duoc.ms_programas.controller;

import com.duoc.ms_programas.model.programas;
import com.duoc.ms_programas.service.programasService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//MAPEO DE ENDPOINTS:
//GET    http://localhost:8085/redsalud/v1/programas              -> listar todas
//GET    http://localhost:8085/redsalud/v1/programas/{id}         -> obtener una (404 si no existe)
//POST    http://localhost:8085/redsalud/v1/programas             -> crear (201 Created)
//PUT     http://localhost:8085/redsalud/v1/programas/{id}        -> actualizar
//DELETE  http://localhost:8085redsalud/v1/programas/{id}         -> eliminar (204 No Content)
@RestController
@RequestMapping("/redsalud/v1/programas") // Se define la ruta base para todos los endpoints de este controlador
public class programasController {

    private final programasService service; // Inyección del servicio que maneja la lógica de negocio

    public programasController(programasService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> listarTodos(){
        try {
            List<programas> programas = service.listarTodos(); // Llama al servicio para obtener los programas
            return ResponseEntity.ok()
                    .body("Programas listados correctamente"); // Devuelve mensaje de exito
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar los programas"); // Devuelve mensaje de error
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        try {
            programas programas = service.buscarPorId(id); // Busca programa por ID

            if (programas == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontro el ID del programa"); // 404 si no existe
            }
            return ResponseEntity.ok().body(programas); // Devuelve el objeto encontrado
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error del servidor");
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody programas programas){
        try {
            // Validación manual de campos obligatorios
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

        return ResponseEntity.ok().body(actualizado); // Devuelve el objeto actualizado
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        boolean eliminado = service.eliminar(id); // Elimina el objeto mediante el ID

        if (eliminado){
            return ResponseEntity.ok("Programa eliminado correctamente");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Programa no encontrado");
    }

}
