package com.duoc.ms_farmacia.service;

import com.duoc.ms_farmacia.model.farmacia;
import com.duoc.ms_farmacia.repository.farmaciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class farmaciaService {

    private final farmaciaRepository repository;

    public farmaciaService(farmaciaRepository repository) {
        this.repository = repository;
    }

    public farmacia crear(farmacia farmacia) {
        return repository.save(farmacia);
    }

    public List<farmacia> listarMedicamentos() {
        return repository.findAll();
    }

    public farmacia buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public farmacia actualizar(Long id, farmacia farmacia) {
        farmacia f = buscarPorId(id);

        if (f == null) {
            return null;
        }

        f.setHorarioFarmacia(farmacia.getHorarioFarmacia());
        f.setEncargadoNombre(farmacia.getEncargadoNombre());
        f.setTelefonoFarmacia(farmacia.getTelefonoFarmacia());
        f.setProveedor(farmacia.getProveedor());
        f.setTelefonoProveedor(farmacia.getTelefonoProveedor());
        f.setMedicamentos(farmacia.getMedicamentos());
        f.setStockMedicamentos(farmacia.getStockMedicamentos());

        return repository.save(f);
    }

    public boolean eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
