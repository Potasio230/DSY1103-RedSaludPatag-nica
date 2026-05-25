package com.duoc.ms_programas.repository;

import com.duoc.ms_programas.model.programas;
import org.springframework.data.jpa.repository.JpaRepository;


public interface programasRepository extends JpaRepository<programas, Long> {
// Hereda métodos CRUD de JpaRepository: findAll, findById, save, deleteById
}
