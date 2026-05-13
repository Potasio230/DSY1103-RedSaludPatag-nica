package com.duoc.ms_farmacia.service;

import com.duoc.ms_farmacia.dto.FarmaciaDTO;
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

    public farmacia crear(FarmaciaDTO dto) {
        farmacia nueva = new farmacia();
        nueva.setId(dto.getId());
        nueva.setMedicamentos(dto.getMedicamentos());
        nueva.setStockMedicamentos(dto.getStockMedicamentos());
        nueva.setEncargadoNombre(dto.getEncargadoNombre());
        nueva.setTelefonoFarmacia(dto.getTelefonoFarmacia());
        nueva.setProveedor(dto.getProveedor());
        nueva.setTelefonoProveedor(dto.getTelefonoProveedor());
        nueva.setHorarioFarmacia(dto.getHorarioFarmacia());

        return repository.save(nueva);
    }


    public List<farmacia> listarMedicamentos() {
        return repository.findAll();
    }

    public farmacia buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public farmacia actualizar(Long id, FarmaciaDTO dto) {
        farmacia f = buscarPorId(id);

        if (f == null) {
            return null;
        }

        f.setMedicamentos(dto.getMedicamentos());
        f.setStockMedicamentos(dto.getStockMedicamentos());
        f.setEncargadoNombre(dto.getEncargadoNombre());
        f.setTelefonoFarmacia(dto.getTelefonoFarmacia());
        f.setProveedor(dto.getProveedor());
        f.setTelefonoProveedor(dto.getTelefonoProveedor());
        f.setHorarioFarmacia(dto.getHorarioFarmacia());

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
