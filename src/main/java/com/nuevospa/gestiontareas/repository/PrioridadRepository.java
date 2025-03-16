package com.nuevospa.gestiontareas.repository;

import com.nuevospa.gestiontareas.model.Prioridad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrioridadRepository extends JpaRepository<Prioridad, Integer> {
} 