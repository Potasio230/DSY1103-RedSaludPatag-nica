package com.duoc.ms_programas.service;

import com.duoc.ms_programas.model.programas;
import com.duoc.ms_programas.repository.programasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Marca la clase como servicio debido a que contiene la lógica de negocio
public class programasService {

    private final programasRepository repository;

    public programasService(programasRepository repository) {
        this.repository = repository;
    }

    public programas crear (programas programas) {
        return repository.save(programas);
    }

    public List<programas> listarTodos() {
        return repository.findAll(); // Devuelve todos los programas
    }

    public programas buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public programas actualizar(Long id, programas programas) {
        programas p = buscarPorId(id);

        if (p == null) {
            return null; // Si no existe, retorna un null
        }

        // Actualiza campos de la entidad existente
        p.setNombrePrograma(programas.getNombrePrograma());
        p.setNombreEncargado(programas.getNombreEncargado());
        p.setTipoPrograma(programas.getTipoPrograma());
        p.setLugarPrograma(programas.getLugarPrograma());
        p.setFechaPrograma(programas.getFechaPrograma());

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
