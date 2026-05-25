package com.duoc.ms_farmacia.controller;

import com.duoc.ms_farmacia.dto.FarmaciaDTO;
import com.duoc.ms_farmacia.model.farmacia;
import com.duoc.ms_farmacia.service.farmaciaService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//MAPEO DE ENDPOINTS:
//GET    http://localhost:8086/redsalud/v1/farmacia               -> listar todas
//GET    http://localhost:8086/redsalud/v1/farmacia/{id}          -> obtener una (404 si no existe)
//POST    http://localhost:8086/redsalud/v1/farmacia              -> crear (201 Created)
//PUT     http://localhost:8086/redsalud/v1/farmacia/{id}         -> actualizar
//DELETE  http://localhost:8086/redsalud/v1/farmacia/{id}         -> eliminar (204 No Content)

@RestController
@RequestMapping("/redsalud/v1/farmacia") // Se define la ruta base para todos los endpoints de este controlador
public class farmaciaController {

    private final farmaciaService service; // Inyección del servicio que maneja la lógica de negocio


    public farmaciaController(farmaciaService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> listarMedicamentos(){
        try {
            List<farmacia> pacientes = service.listarMedicamentos(); // Llama al servicio para obtener los medicamentos
            return ResponseEntity.ok(pacientes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar los medicamentos"); // Devuelve mensaje de error
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        try {
            farmacia farmacia = service.buscarPorId(id); // Busca medicamento por ID

            if (farmacia == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontro el ID del medicamento"); // 404 si no existe
            }
            return ResponseEntity.ok().body(farmacia); // Devuelve el objeto encontrado
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error del servidor");
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody FarmaciaDTO farmaciaDTO) {
        try {
            farmacia nuevoMedicamento = service.crear(farmaciaDTO); // Convierte el DTO en entidad y la guarda
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMedicamento);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al ingresar el medicamento");
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody FarmaciaDTO farmaciaDTO){
        try {
            farmacia actualizado = service.actualizar(id, farmaciaDTO); // Actualiza los campos modificados de la entidad

            if (actualizado == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontro el ID de la farmacia para actualizar los datos");
            }

            return ResponseEntity.ok().body(actualizado); // Devuelve el objeto actualizado

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el medicamento");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        boolean eliminado = service.eliminar(id); // Elimina el objeto mediante el ID

        if (eliminado){
            return ResponseEntity.ok("Medicamento eliminado correctamente");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Medicamento no encontrado");
    }

}
