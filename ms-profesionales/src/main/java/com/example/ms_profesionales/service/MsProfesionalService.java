package com.example.ms_profesionales.service;

import com.example.ms_profesionales.model.MsProfesional;
import com.example.ms_profesionales.repository.MsProfesionalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Marca la clase como servicio ya que contiene la lógica de negocio
public class MsProfesionalService {

    private final MsProfesionalRepository repository;

    public MsProfesionalService(MsProfesionalRepository repository) {
        this.repository = repository;
    }

    public List<MsProfesional> listarTodos(){
        return repository.findAll();
    } // Devuelve todos los profesionales

    public Optional<MsProfesional> buscarPorId(Long id){
        return repository.findById(id);
    }

    public MsProfesional guardar(MsProfesional msProfesional){
        return repository.save(msProfesional);
    }

    public MsProfesional actualizar(Long id, MsProfesional profesional) {
        return repository.findById(id)
                .map(p -> {
                    // Actualiza campos de la entidad existente
                    p.setNombre(profesional.getNombre());
                    p.setEspecialidad(profesional.getEspecialidad());
                    p.setCorreo(profesional.getCorreo());
                    p.setTelefono(profesional.getTelefono());
                    return repository.save(p); // Guarda los cambios realizados
                })
                .orElse(null);
    }

    public void eliminar(Long id){
        repository.deleteById(id);
    }
}
