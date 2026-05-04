package com.duoc.redsalud.service;

import com.duoc.redsalud.model.redsalud;
import com.duoc.redsalud.repository.redsaludrepository;
import org.springframework.stereotype.Service;

import java.util.List;

// UTILIZAMOS SERVICE PARA LA INYECCION DE DEPENDENCIAS
@Service
public class redsaludservice {

    // LO QUE NOS AYUDARA A CONECTAR CON LA BASE DE DATOS
    private final redsaludrepository repository;

    // CONSTRUCTOR CON INYECCION DE DEPENDENCIAS
    public redsaludservice(redsaludrepository repository) {
        this.repository = repository;
    }

    // CREA LA FICHA DEL PACIENTE
    public redsalud crear(redsalud paciente) {
        return repository.save(paciente);
    }

    // LISTA A TODOS LOS PACIENTES REGISTRADOS EN LA BASE DE DATOS
    public List<redsalud> listarTodos() {
        return repository.findAll();
    }

    // BUSCA LOS PACIENTES POR SU NÚMERO DE ID
    public redsalud buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    // ACTUALIZA LA FICHA DE LOS PACIENTES EN CASO DE QUE SEA NECESARIO
    public redsalud actualizar(Long id, redsalud paciente) {
        redsalud p = buscarPorId(id);

        p.setNombre(paciente.getNombre());
        p.setDireccion(paciente.getDireccion());
        p.setResidencia(paciente.getResidencia());
        p.setFechaNacimiento(paciente.getFechaNacimiento());
        p.setEmail(paciente.getEmail());
        p.setTelefono(paciente.getTelefono());
        p.setRut(paciente.getRut());

        return repository.save(p);
    }

    // ELIMINA LA FICHA DEL PACIENTE
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

}
