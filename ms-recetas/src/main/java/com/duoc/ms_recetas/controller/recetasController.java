package com.duoc.ms_recetas.controller;

import com.duoc.ms_recetas.model.recetas;
import com.duoc.ms_recetas.service.recetasService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/redsalud/recetas")
public class recetasController {

    private recetasService service;

    public recetasController(recetasService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> listarRecetas() {
        try {
            List<recetas> pacientes = service.listarRecetas();
            return ResponseEntity.ok()
                    .body("Recetas listadas correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar las recetas");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        try {
            recetas recetas = service.buscarPorId(id);

            if (recetas == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontro el ID de la receta medica");
            }
            return ResponseEntity.ok().body(recetas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error del servidor");
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody recetas recetas){
        try {
            if (recetas.getFechaEmision() == null ||
                    recetas.getIdPaciente() == null ||
                    recetas.getNombrePaciente() == null ||
                    recetas.getIdProfesional() == null ||
                    recetas.getNombreProfesional() == null ||
                    recetas.getIndicacionesMedicas() == null ||
                    recetas.getNombreMedicamentos() == null) {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Faltan rellenar campos obligatorios");
            }

            recetas nuevaReceta = service.crear(recetas);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Receta creada exitosamente");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la receta");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody recetas recetas){
        recetas actualizado = service.actualizar(id, recetas);

        if (actualizado == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontro el ID de la receta medica");
        }

        return ResponseEntity.ok().body(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        boolean eliminado = service.eliminar(id);

        if (eliminado){
            return ResponseEntity.ok("Receta eliminada correctamente");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Receta no encontrada");
    }




}
