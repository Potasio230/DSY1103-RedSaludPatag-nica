package com.duoc.ms_consultas.service;

import com.duoc.ms_consultas.model.consultas;
import com.duoc.ms_consultas.repository.consultasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class consultasService {

    private final consultasRepository repository;

    public consultasService(consultasRepository repository) {
        this.repository = repository;
    }

    public consultas crear(consultas consultas) {
        return repository.save(consultas);
    }

    public List<consultas> listarConsultas() {
        return repository.findAll();
    }

    public consultas buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public consultas actualizar(Long id, consultas consulta) {
        consultas c = buscarPorId(id);

        if (c == null) {
            return null;
        }

        c.setNombrePaciente(consulta.getNombrePaciente());
        c.setFichaPaciente(consulta.getFichaPaciente());
        c.setNombreProfesional(consulta.getNombreProfesional());
        c.setFichaProfesional(consulta.getFichaProfesional());
        c.setRazonConsulta(consulta.getRazonConsulta());
        c.setFechaConsulta(consulta.getFechaConsulta());
        c.setModalidad(consulta.getModalidad());

        return repository.save(c);
    }

    public boolean eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}


