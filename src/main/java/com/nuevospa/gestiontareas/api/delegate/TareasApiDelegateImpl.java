package com.nuevospa.gestiontareas.api.delegate;

import com.nuevospa.gestiontareas.api.TareasApiDelegate;
import com.nuevospa.gestiontareas.dto.CambioEstadoTarea;
import com.nuevospa.gestiontareas.dto.PageTarea;
import com.nuevospa.gestiontareas.model.Tarea;
import com.nuevospa.gestiontareas.service.TareaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class TareasApiDelegateImpl implements TareasApiDelegate {

    private final TareaService tareaService;

    @Override
    public ResponseEntity<PageTarea> obtenerTareas(Integer page, Integer size, Integer estadoId, 
                                                 UUID usuarioId, Integer prioridad, String sort) {
        log.info("Obteniendo tareas con filtros: estadoId={}, usuarioId={}, prioridad={}, page={}, size={}, sort={}",
                estadoId, usuarioId, prioridad, page, size, sort);
        
        Pageable pageable;
        if (sort != null && !sort.isEmpty()) {
            String[] sortParams = sort.split(",");
            String sortField = sortParams[0];
            Sort.Direction direction = sortParams.length > 1 && sortParams[1].equalsIgnoreCase("desc") 
                    ? Sort.Direction.DESC : Sort.Direction.ASC;
            pageable = PageRequest.of(page, size, Sort.by(direction, sortField));
        } else {
            pageable = PageRequest.of(page, size);
        }
        
        Page<Tarea> tareas = tareaService.findFiltered(estadoId, usuarioId, prioridad, pageable);
        
        // Convertir Page<Tarea> a PageTarea
        PageTarea pageResponse = new PageTarea();
        pageResponse.setContent(tareas.getContent());
        pageResponse.setPageable(tareas.getPageable());
        pageResponse.setLast(tareas.isLast());
        pageResponse.setTotalPages(tareas.getTotalPages());
        pageResponse.setTotalElements(tareas.getTotalElements());
        pageResponse.setSize(tareas.getSize());
        pageResponse.setNumber(tareas.getNumber());
        pageResponse.setSort(tareas.getSort());
        pageResponse.setFirst(tareas.isFirst());
        pageResponse.setNumberOfElements(tareas.getNumberOfElements());
        pageResponse.setEmpty(tareas.isEmpty());
        
        return ResponseEntity.ok(pageResponse);
    }

    @Override
    public ResponseEntity<Tarea> obtenerTareaPorId(UUID id) {
        log.info("Obteniendo tarea con ID: {}", id);
        
        return tareaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> crearTarea(Tarea tarea) {
        log.info("Creando nueva tarea: {}", tarea);
        
        Tarea tareaSaved = tareaService.save(tarea);
        
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tareaSaved.getId())
                .toUri();
        
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Tarea> actualizarTarea(UUID id, Tarea tarea) {
        log.info("Actualizando tarea con ID: {}", id);
        
        if (!id.equals(tarea.getId())) {
            log.error("ID de la ruta ({}) no coincide con el ID en el cuerpo ({})", id, tarea.getId());
            return ResponseEntity.badRequest().build();
        }
        
        return tareaService.findById(id)
                .map(existingTarea -> {
                    tarea.setId(id);
                    tarea.setFechaCreacion(existingTarea.getFechaCreacion());
                    tarea.setUsuarioCreador(existingTarea.getUsuarioCreador());
                    tarea.setUsuarioCreadorId(existingTarea.getUsuarioCreadorId());
                    Tarea updated = tareaService.save(tarea);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> eliminarTarea(UUID id) {
        log.info("Eliminando tarea con ID: {}", id);
        
        if (!tareaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        tareaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Tarea> actualizarEstadoTarea(UUID id, CambioEstadoTarea cambioEstado) {
        log.info("Actualizando estado de tarea con ID: {} a estado: {}", id, cambioEstado.getEstadoId());
        
        return tareaService.cambiarEstado(id, cambioEstado.getEstadoId())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
} 