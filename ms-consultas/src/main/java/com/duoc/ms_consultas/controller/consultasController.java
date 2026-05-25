package com.duoc.ms_consultas.controller;

import com.duoc.ms_consultas.dto.ConsultasDTO;
import com.duoc.ms_consultas.model.consultas;
import com.duoc.ms_consultas.service.consultasService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


import java.util.List;

//MAPEO DE ENDPOINTS:
//GET    http://localhost:8084/redsalud/v1/consultas              -> listar todas
//GET    http://localhost:8084/redsalud/v1/consultas/{id}         -> obtener una (404 si no existe)
//POST    http://localhost:8084/redsalud/v1/consultas              -> crear (201 Created)
//PUT     http://localhost:8084/redsalud/v1/consultas/{id}         -> actualizar
//DELETE  http://localhost:8084/redsalud/v1/consultas/{id}         -> eliminar (204 No Content)

@RestController
@RequestMapping("/redsalud/v1/consultas")// Se define la ruta base para todos los endpoints de este controlador
public class consultasController {

    private final consultasService service;  // Inyección del servicio que maneja la lógica de negocio
    public consultasController(consultasService service){
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<?> listarConsultas(){
        try {
            List<consultas> consultas = service.listarConsultas(); // Llama al servicio para obtener las consultas
            return ResponseEntity.ok()
                    .body("Consultas listadas correctamente"); // Devuelve mensaje de éxito
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar las consultas");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        try {
            consultas consultas= service.buscarPorId(id); // Busca una consulta por ID

            if (consultas == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontro el ID de la consulta");
            }
            return ResponseEntity.ok().body(consultas); // Devuelve la entidad encontrada
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error del servidor");
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody ConsultasDTO consultaDTO){
        try {
            consultas nuevaConsulta = service.crear(consultaDTO);  // Convierte DTO en entidad y guarda
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaConsulta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al agendar la consulta");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody consultas consulta){
        consultas actualizado = service.actualizar(id, consulta); // Actualiza campos de la entidad

        if (actualizado == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontro el ID de la consulta");
        }

        return ResponseEntity.ok().body(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        boolean eliminado = service.eliminar(id); // Elimina por ID

        if (eliminado){
            return ResponseEntity.ok("Consulta eliminada correctamente");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Consulta no encontrado");
    }
}
