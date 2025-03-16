package com.nuevospa.gestiontareas.api;

import com.nuevospa.gestiontareas.dto.CambioEstadoTarea;
import com.nuevospa.gestiontareas.dto.PageTarea;
import com.nuevospa.gestiontareas.model.Tarea;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface TareasApiDelegate {
    ResponseEntity<PageTarea> obtenerTareas(Integer page, Integer size, Integer estadoId, 
                                           UUID usuarioId, Integer prioridad, String sort);
    
    ResponseEntity<Tarea> obtenerTareaPorId(UUID id);
    
    ResponseEntity<Void> crearTarea(Tarea tarea);
    
    ResponseEntity<Tarea> actualizarTarea(UUID id, Tarea tarea);
    
    ResponseEntity<Void> eliminarTarea(UUID id);
    
    ResponseEntity<Tarea> actualizarEstadoTarea(UUID id, CambioEstadoTarea cambioEstado);
} 