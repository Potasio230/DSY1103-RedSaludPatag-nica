package com.duoc.ms_pacientes.service;

import com.duoc.ms_pacientes.model.pacientes;
import com.duoc.ms_pacientes.repository.pacientesRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service // Marca la clase como servicio ya que contiene la lógica de negocio
public class pacientesService {

    private final pacientesRepository repository;

    public pacientesService(pacientesRepository repository) {
        this.repository = repository;
    }

    public pacientes crear (pacientes pacientes) {
        return repository.save(pacientes);
    }

    public List<pacientes> listarTodos() {
        return repository.findAll();
    } // Devuelve todos los pacientes

    public pacientes buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public pacientes actualizar(Long id, pacientes paciente) {
        pacientes p = buscarPorId(id);

        if (p == null) {
            return null; // Si no existe, retorna un null
        }

        // Mapeo manual del DTO a la entidad que asegura que solo campos válidos sean persistentes
        p.setNombre(paciente.getNombre());
        p.setDireccion(paciente.getDireccion());
        p.setResidencia(paciente.getResidencia());
        p.setFechaNacimiento(paciente.getFechaNacimiento());
        p.setEmail(paciente.getEmail());
        p.setTelefono(paciente.getTelefono());
        p.setRut(paciente.getRut());

        return repository.save(p); // Guarda los cambios realizados
    }

    public boolean eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
