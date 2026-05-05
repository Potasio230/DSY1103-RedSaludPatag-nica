package com.duoc.ms_recetas.service;

import com.duoc.ms_recetas.model.recetas;
import com.duoc.ms_recetas.repository.recetasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class recetasService {

    private final recetasRepository repository;

    public recetasService(recetasRepository repository) {
        this.repository = repository;
    }

    public recetas crear (recetas recetas) {
        return repository.save(recetas);
    }

    public List<recetas> listarRecetas() {
        return repository.findAll();
    }

    public recetas buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public recetas actualizar(Long id, recetas receta) {
        recetas r = buscarPorId(receta.getId());

        if (r == null) {
            return null;
        }

        r.setIdPaciente(receta.getIdPaciente());
        r.setNombrePaciente(receta.getNombrePaciente());
        r.setIdProfesional(receta.getIdProfesional());
        r.setNombreProfesional(receta.getNombreProfesional());
        r.setFechaEmision(receta.getFechaEmision());
        r.setNombreMedicamentos(receta.getNombreMedicamentos());
        r.setIndicacionesMedicas(receta.getIndicacionesMedicas());

        return repository.save(r);
    }

    public boolean eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
