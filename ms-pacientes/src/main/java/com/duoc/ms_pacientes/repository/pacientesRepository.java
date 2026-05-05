package com.duoc.ms_pacientes.repository;

import com.duoc.ms_pacientes.model.pacientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface pacientesRepository extends JpaRepository<pacientes,Long> {
}
