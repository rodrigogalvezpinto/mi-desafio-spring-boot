package com.nuevospa.gestiontareas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nuevospa.gestiontareas.model.EstadoTarea;

@Repository
public interface EstadoTareaRepository extends JpaRepository<EstadoTarea, Integer> {
    
    Optional<EstadoTarea> findByNombre(String nombre);
} 