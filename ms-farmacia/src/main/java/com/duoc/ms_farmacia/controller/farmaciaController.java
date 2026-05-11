package com.duoc.ms_farmacia.controller;

import com.duoc.ms_farmacia.model.farmacia;
import com.duoc.ms_farmacia.service.farmaciaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/redsalud/farmacia")
public class farmaciaController {

    private final farmaciaService service;


    public farmaciaController(farmaciaService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> listarMedicamentos(){
        try {
            List<farmacia> pacientes = service.listarMedicamentos();
            return ResponseEntity.ok()
                    .body("Medicamentos listados correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar los medicamentos");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        try {
            farmacia farmacia = service.buscarPorId(id);

            if (farmacia == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontro el ID del medicamento");
            }
            return ResponseEntity.ok().body(farmacia);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error del servidor");
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody farmacia farmacia){
        try {
            if (farmacia.getMedicamentos() == null ||
                    farmacia.getStockMedicamentos() == null) {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Faltan rellenar campos obligatorios");
            }

            farmacia nuevoMedicamento = service.crear(farmacia);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Medicamento ingresado exitosamente");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al ingresar el medicamento");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody farmacia farmacia){
        farmacia actualizado = service.actualizar(id, farmacia);

        if (actualizado == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontro el ID de la farmacia para actualizar los datos");
        }

        return ResponseEntity.ok().body(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        boolean eliminado = service.eliminar(id);

        if (eliminado){
            return ResponseEntity.ok("Medicamento eliminado correctamente");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Medicamento no encontrado");
    }

}
