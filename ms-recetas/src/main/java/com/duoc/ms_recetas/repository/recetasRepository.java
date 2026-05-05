package com.duoc.ms_recetas.repository;

import com.duoc.ms_recetas.model.recetas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface recetasRepository extends JpaRepository<recetas, Long> {
}
