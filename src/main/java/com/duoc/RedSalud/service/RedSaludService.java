package com.duoc.Redsalud.service;

import com.duoc.Redsalud.model.RedSalud;
import com.duoc.Redsalud.repository.RedSaludRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RedSaludService {

    private final RedSaludRepository redSaludRepository;

    public RedSaludService(RedSaludRepository redSaludRepository) {
        this.redSaludRepository = redSaludRepository;
    }

    // CREAR PACIENTE - POST
    public RedSalud crear(RedSalud redSalud) {
        return redSaludRepository.save(redSalud);
    }

    // LISTAR PACIENTES - GET
    public List<RedSalud> listarTodos() {
        return redSaludRepository.findAll();
    }

    // BUSCAR POR ID
    public RedSalud buscarPorId(Long id) {
        Optional<RedSalud> redSalud = redSaludRepository.findById(id);
        return redSalud.orElse(null);
    }

    // EXISTE POR ID
    public boolean existePorId (Long id) {
        return redSaludRepository.existsById(id);
    }

    // ACTUALIZAR
    public RedSalud actualizar(Long id, RedSalud paciente){
        RedSalud p = redSaludRepository.findById(id).orElse(null);

        if (p == null) {
            return null;
        }

        p.setNombre(paciente.getNombre());
        p.setDireccion(paciente.getDireccion());
        p.setFechaNacimiento(paciente.getFechaNacimiento());
        p.setRut(paciente.getRut());

        return redSaludRepository.save(p);
    }

    // BORRAR PACIENTE - DELETE
    public void eliminarPaciente(Long id) {
        redSaludRepository.deleteById(id);
    }

}
