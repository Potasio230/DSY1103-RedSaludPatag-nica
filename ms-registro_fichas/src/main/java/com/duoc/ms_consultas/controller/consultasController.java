package com.duoc.ms_consultas.controller;

import com.duoc.ms_consultas.model.consultas;
import com.duoc.ms_consultas.service.consultasService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/redsalud/consultas")
public class consultasController {

    private final consultasService service;

    public consultasController(consultasService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> listarConsultas(){
        try {
            List<consultas> consultas = service.listarConsultas();
            return ResponseEntity.ok()
                    .body("Consultas listadas correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar las consultas");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        try {
            consultas consultas= service.buscarPorId(id);

            if (consultas == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontro el ID de la consulta");
            }
            return ResponseEntity.ok().body(consultas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error del servidor");
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody consultas consulta){
        try {

            if (consulta == null ||
                    consulta.getNombrePaciente() == null ||
                    consulta.getFichaPaciente() == null ||
                    consulta.getNombreProfesional() == null ||
                    consulta.getFichaProfesional() == null ||
                    consulta.getModalidad() == null ||
                    consulta.getFechaConsulta() == null ||
                    consulta.getRazonConsulta() == null) {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Faltan rellenar campos obligatorios");
            }

            consultas nuevaConsulta = service.crear(consulta);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(nuevaConsulta);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al agendar la consulta");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody consultas consulta){
        consultas actualizado = service.actualizar(id, consulta);

        if (actualizado == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontro el ID de la consulta");
        }

        return ResponseEntity.ok().body(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        boolean eliminado = service.eliminar(id);

        if (eliminado){
            return ResponseEntity.ok("Consulta eliminada correctamente");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Consulta no encontrado");
    }
}
