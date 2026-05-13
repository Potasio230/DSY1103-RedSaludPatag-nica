package com.duoc.ms_programas.service;

import com.duoc.ms_programas.model.programas;
import com.duoc.ms_programas.repository.programasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class programasService {

    private final programasRepository repository;

    public programasService(programasRepository repository) {
        this.repository = repository;
    }

    public programas crear (programas programas) {
        return repository.save(programas);
    }

    public List<programas> listarTodos() {
        return repository.findAll();
    }

    public programas buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public programas actualizar(Long id, programas programas) {
        programas p = buscarPorId(id);

        if (p == null) {
            return null;
        }

        p.setNombrePrograma(programas.getNombrePrograma());
        p.setNombreEncargado(programas.getNombreEncargado());
        p.setTipoPrograma(programas.getTipoPrograma());
        p.setLugarPrograma(programas.getLugarPrograma());
        p.setFechaPrograma(programas.getFechaPrograma());

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
