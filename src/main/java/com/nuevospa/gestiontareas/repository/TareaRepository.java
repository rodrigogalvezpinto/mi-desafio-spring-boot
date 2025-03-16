package com.nuevospa.gestiontareas.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nuevospa.gestiontareas.model.EstadoTarea;
import com.nuevospa.gestiontareas.model.Prioridad;
import com.nuevospa.gestiontareas.model.Tarea;
import com.nuevospa.gestiontareas.model.Usuario;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, UUID> {
    
    Page<Tarea> findByUsuarioAsignado(Usuario usuario, Pageable pageable);
    
    Page<Tarea> findByEstado(EstadoTarea estado, Pageable pageable);
    
    Page<Tarea> findByPrioridad(Prioridad prioridad, Pageable pageable);
    
    Page<Tarea> findByUsuarioAsignadoAndEstado(Usuario usuario, EstadoTarea estado, Pageable pageable);
    
    Page<Tarea> findByUsuarioAsignadoAndPrioridad(Usuario usuario, Prioridad prioridad, Pageable pageable);
    
    Page<Tarea> findByEstadoAndPrioridad(EstadoTarea estado, Prioridad prioridad, Pageable pageable);
    
    Page<Tarea> findByUsuarioAsignadoAndEstadoAndPrioridad(Usuario usuario, EstadoTarea estado, Prioridad prioridad, Pageable pageable);

    Page<Tarea> findByEstadoId(Integer estadoId, Pageable pageable);
    
    Page<Tarea> findByUsuarioAsignadoId(UUID usuarioId, Pageable pageable);
    
    Page<Tarea> findByPrioridadId(Integer prioridadId, Pageable pageable);
    
    Page<Tarea> findByEstadoIdAndUsuarioAsignadoId(Integer estadoId, UUID usuarioId, Pageable pageable);
    
    Page<Tarea> findByEstadoIdAndPrioridadId(Integer estadoId, Integer prioridadId, Pageable pageable);
    
    Page<Tarea> findByUsuarioAsignadoIdAndPrioridadId(UUID usuarioId, Integer prioridadId, Pageable pageable);
    
    Page<Tarea> findByEstadoIdAndUsuarioAsignadoIdAndPrioridadId(Integer estadoId, UUID usuarioId, Integer prioridadId, Pageable pageable);
} 