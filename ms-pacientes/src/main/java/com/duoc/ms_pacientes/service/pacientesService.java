package com.duoc.ms_pacientes.service;

import com.duoc.ms_pacientes.model.pacientes;
import com.duoc.ms_pacientes.repository.pacientesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// UTILIZAMOS SERVICE PARA LA INYECCIÓN DE DEPENDENCIAS
@Service
public class pacientesService {

    // LO QUE NOS AYUDARA A CONECTAR CON LA BASE DE DATOS
    private final pacientesRepository repository;

    // CONSTRUCTOR CON INYECCION DE DEPENDENCIAS
    public pacientesService(pacientesRepository repository) {
        this.repository = repository;
    }

    // CREA LA FICHA DEL PACIENTE
    public pacientes crear (pacientes pacientes) {
        return repository.save(pacientes);
    }

    // LISTA A TODOS LOS PACIENTES REGISTRADOS EN LA BASE DE DATOS
    public List<pacientes> listarTodos() {
        return repository.findAll();
    }

    // BUSCA LOS PACIENTES POR SU NÚMERO DE ID
    public pacientes buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    // ACTUALIZA LA FICHA DE LOS PACIENTES EN CASO DE QUE SEA NECESARIO
    public pacientes actualizar(Long id, pacientes paciente) {
        pacientes p = buscarPorId(id);

        if (p == null) {
            return null;
        }

        p.setNombre(paciente.getNombre());
        p.setDireccion(paciente.getDireccion());
        p.setResidencia(paciente.getResidencia());
        p.setFechaNacimiento(paciente.getFechaNacimiento());
        p.setEmail(paciente.getEmail());
        p.setTelefono(paciente.getTelefono());
        p.setRut(paciente.getRut());

        return repository.save(p);
    }

    public boolean eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
