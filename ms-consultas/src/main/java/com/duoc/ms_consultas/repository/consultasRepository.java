package com.duoc.ms_consultas.repository;

import com.duoc.ms_consultas.model.consultas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface consultasRepository extends JpaRepository<consultas, Long>{
// Hereda métodos CRUD de JpaRepository: findAll, findById, save, deleteById

}
