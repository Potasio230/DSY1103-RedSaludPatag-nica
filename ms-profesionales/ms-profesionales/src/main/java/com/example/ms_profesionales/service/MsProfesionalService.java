package com.example.ms_profesionales.service;

import com.example.ms_profesionales.model.MsProfesional;
import com.example.ms_profesionales.repository.MsProfesionalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MsProfesionalService {

    private final MsProfesionalRepository repository;

    public MsProfesionalService(MsProfesionalRepository repository) {
        this.repository = repository;
    }

    public List<MsProfesional> listarTodos(){
        return repository.findAll();
    }

    public Optional<MsProfesional> buscarPorId(Long id){
        return repository.findById(id);
    }

    public MsProfesional guardar(MsProfesional msProfesional){
        return repository.save(msProfesional);
    }

    public MsProfesional actualizar(Long id, MsProfesional profesional) {
        return repository.findById(id)
                .map(p -> {
                    p.setNombre(profesional.getNombre());
                    p.setEspecialidad(profesional.getEspecialidad());
                    p.setCorreo(profesional.getCorreo());
                    p.setTelefono(profesional.getTelefono());
                    return repository.save(p);
                })
                .orElse(null);
    }

    public void eliminar(Long id){
        repository.deleteById(id)
    }
}
