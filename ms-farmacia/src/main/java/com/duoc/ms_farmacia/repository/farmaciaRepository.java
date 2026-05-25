package com.duoc.ms_farmacia.repository;

import com.duoc.ms_farmacia.model.farmacia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface farmaciaRepository extends JpaRepository<farmacia, Long> {
// Hereda métodos CRUD de JpaRepository: findAll, findById, save, deleteById

}
