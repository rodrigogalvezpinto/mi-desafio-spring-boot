package com.nuevospa.gestiontareas.service;

import com.nuevospa.gestiontareas.dto.TareaActualizarDTO;
import com.nuevospa.gestiontareas.dto.TareaCrearDTO;
import com.nuevospa.gestiontareas.dto.TareaDTO;
import com.nuevospa.gestiontareas.model.EstadoTarea;
import com.nuevospa.gestiontareas.model.Prioridad;
import com.nuevospa.gestiontareas.model.Tarea;
import com.nuevospa.gestiontareas.model.Usuario;
import com.nuevospa.gestiontareas.repository.EstadoTareaRepository;
import com.nuevospa.gestiontareas.repository.PrioridadRepository;
import com.nuevospa.gestiontareas.repository.TareaRepository;
import com.nuevospa.gestiontareas.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TareaService {

    private final TareaRepository tareaRepository;
    private final UsuarioRepository usuarioRepository;
    private final EstadoTareaRepository estadoTareaRepository;
    private final PrioridadRepository prioridadRepository;

    @Transactional(readOnly = true)
    public Page<Tarea> findFiltered(Integer estadoId, UUID usuarioId, Integer prioridadId, Pageable pageable) {
        log.info("Obteniendo tareas con filtros: estadoId={}, usuarioId={}, prioridadId={}", estadoId, usuarioId, prioridadId);
        
        if (estadoId != null && usuarioId != null && prioridadId != null) {
            return tareaRepository.findByEstadoIdAndUsuarioAsignadoIdAndPrioridadId(estadoId, usuarioId, prioridadId, pageable);
        } else if (estadoId != null && usuarioId != null) {
            return tareaRepository.findByEstadoIdAndUsuarioAsignadoId(estadoId, usuarioId, pageable);
        } else if (estadoId != null && prioridadId != null) {
            return tareaRepository.findByEstadoIdAndPrioridadId(estadoId, prioridadId, pageable);
        } else if (usuarioId != null && prioridadId != null) {
            return tareaRepository.findByUsuarioAsignadoIdAndPrioridadId(usuarioId, prioridadId, pageable);
        } else if (estadoId != null) {
            return tareaRepository.findByEstadoId(estadoId, pageable);
        } else if (usuarioId != null) {
            return tareaRepository.findByUsuarioAsignadoId(usuarioId, pageable);
        } else if (prioridadId != null) {
            return tareaRepository.findByPrioridadId(prioridadId, pageable);
        } else {
            return tareaRepository.findAll(pageable);
        }
    }

    @Transactional(readOnly = true)
    public Optional<Tarea> findById(UUID id) {
        log.info("Buscando tarea con ID: {}", id);
        return tareaRepository.findById(id);
    }

    @Transactional
    public Tarea save(Tarea tarea) {
        log.info("Guardando tarea: {}", tarea);
        return tareaRepository.save(tarea);
    }

    @Transactional
    public void deleteById(UUID id) {
        log.info("Eliminando tarea con ID: {}", id);
        tareaRepository.deleteById(id);
    }
    
    @Transactional
    public Optional<Tarea> cambiarEstado(UUID id, Integer estadoId) {
        log.info("Cambiando estado de tarea {} a estadoId={}", id, estadoId);
        
        return tareaRepository.findById(id)
                .flatMap(tarea -> estadoTareaRepository.findById(estadoId)
                        .map(nuevoEstado -> {
                            tarea.setEstado(nuevoEstado);
                            tarea.setEstadoId(estadoId);
                            return tareaRepository.save(tarea);
                        }));
    }

    @Transactional
    public TareaDTO create(TareaCrearDTO dto) {
        log.info("Creando nueva tarea: {}", dto);
        
        EstadoTarea estado = estadoTareaRepository.findById(dto.getEstadoId())
                .orElseThrow(() -> new NoSuchElementException("Estado no encontrado con ID: " + dto.getEstadoId()));
        
        Prioridad prioridad = prioridadRepository.findById(dto.getPrioridadId())
                .orElseThrow(() -> new NoSuchElementException("Prioridad no encontrada con ID: " + dto.getPrioridadId()));
        
        Usuario usuarioAsignado = null;
        if (dto.getUsuarioId() != null) {
            usuarioAsignado = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con ID: " + dto.getUsuarioId()));
        }
        
        Tarea tarea = Tarea.builder()
                .titulo(dto.getTitulo())
                .descripcion(dto.getDescripcion())
                .fechaCreacion(LocalDateTime.now())
                .fechaVencimiento(dto.getFechaVencimiento())
                .usuarioAsignado(usuarioAsignado)
                .estado(estado)
                .prioridad(prioridad)
                .build();
        
        Tarea tareaGuardada = tareaRepository.save(tarea);
        log.info("Tarea creada con ID: {}", tareaGuardada.getId());
        
        return convertToDTO(tareaGuardada);
    }

    @Transactional
    public TareaDTO update(TareaActualizarDTO dto) {
        log.info("Actualizando tarea: {}", dto);
        
        Tarea tarea = tareaRepository.findById(dto.getId())
                .orElseThrow(() -> new NoSuchElementException("Tarea no encontrada con ID: " + dto.getId()));
        
        EstadoTarea estado = estadoTareaRepository.findById(dto.getEstadoId())
                .orElseThrow(() -> new NoSuchElementException("Estado no encontrado con ID: " + dto.getEstadoId()));
        
        Prioridad prioridad = prioridadRepository.findById(dto.getPrioridadId())
                .orElseThrow(() -> new NoSuchElementException("Prioridad no encontrada con ID: " + dto.getPrioridadId()));
        
        Usuario usuarioAsignado = null;
        if (dto.getUsuarioId() != null) {
            usuarioAsignado = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con ID: " + dto.getUsuarioId()));
        }
        
        tarea.setTitulo(dto.getTitulo());
        tarea.setDescripcion(dto.getDescripcion());
        tarea.setFechaVencimiento(dto.getFechaVencimiento());
        tarea.setUsuarioAsignado(usuarioAsignado);
        tarea.setEstado(estado);
        tarea.setPrioridad(prioridad);
        
        Tarea tareaActualizada = tareaRepository.save(tarea);
        log.info("Tarea actualizada: ID={}", tareaActualizada.getId());
        
        return convertToDTO(tareaActualizada);
    }

    private TareaDTO convertToDTO(Tarea tarea) {
        return TareaDTO.builder()
                .id(tarea.getId())
                .titulo(tarea.getTitulo())
                .descripcion(tarea.getDescripcion())
                .fechaCreacion(tarea.getFechaCreacion())
                .fechaVencimiento(tarea.getFechaVencimiento())
                .usuarioId(tarea.getUsuarioAsignado() != null ? tarea.getUsuarioAsignado().getId() : null)
                .usuarioNombre(tarea.getUsuarioAsignado() != null ? tarea.getUsuarioAsignado().getNombre() : null)
                .estadoId(tarea.getEstado().getId())
                .estadoNombre(tarea.getEstado().getNombre())
                .prioridadId(tarea.getPrioridad().getId())
                .prioridadNombre(tarea.getPrioridad().getNombre())
                .build();
    }
} 