package com.nuevospa.gestiontareas.mapper;

import com.nuevospa.gestiontareas.dto.PageTarea;
import com.nuevospa.gestiontareas.dto.CambioEstadoTarea;
import com.nuevospa.gestiontareas.dto.LoginRequest;
import com.nuevospa.gestiontareas.dto.LoginResponse;
import com.nuevospa.gestiontareas.dto.generated.Usuario.RolEnum;
import com.nuevospa.gestiontareas.model.EstadoTarea;
import com.nuevospa.gestiontareas.model.Prioridad;
import com.nuevospa.gestiontareas.model.Rol;
import com.nuevospa.gestiontareas.model.Tarea;
import com.nuevospa.gestiontareas.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Clase utilitaria para mapear entre los DTOs generados por OpenAPI y los modelos/DTOs existentes
 */
@Component
public class DtoMapper {

    // Conversiones de fecha/hora
    private OffsetDateTime toOffsetDateTime(LocalDateTime localDateTime) {
        if (localDateTime == null) return null;
        return localDateTime.atOffset(ZoneOffset.UTC);
    }
    
    private LocalDateTime toLocalDateTime(OffsetDateTime offsetDateTime) {
        if (offsetDateTime == null) return null;
        return offsetDateTime.toLocalDateTime();
    }

    // Mappers para CambioEstadoTarea
    public com.nuevospa.gestiontareas.dto.CambioEstadoTarea toModel(com.nuevospa.gestiontareas.dto.generated.CambioEstadoTarea dto) {
        if (dto == null) return null;
        
        com.nuevospa.gestiontareas.dto.CambioEstadoTarea model = new com.nuevospa.gestiontareas.dto.CambioEstadoTarea();
        model.setEstadoId(dto.getEstadoId());
        return model;
    }

    public com.nuevospa.gestiontareas.dto.generated.CambioEstadoTarea toDto(com.nuevospa.gestiontareas.dto.CambioEstadoTarea model) {
        if (model == null) return null;
        
        com.nuevospa.gestiontareas.dto.generated.CambioEstadoTarea dto = 
            new com.nuevospa.gestiontareas.dto.generated.CambioEstadoTarea(model.getEstadoId());
        return dto;
    }

    // Mappers para Tarea
    public Tarea toModel(com.nuevospa.gestiontareas.dto.generated.Tarea dto) {
        if (dto == null) return null;
        
        Tarea model = new Tarea();
        if (dto.getId() != null) {
            model.setId(dto.getId());
        }
        model.setTitulo(dto.getTitulo());
        model.setDescripcion(dto.getDescripcion());
        model.setFechaVencimiento(toLocalDateTime(dto.getFechaVencimiento()));
        model.setEstadoId(dto.getEstadoId());
        model.setPrioridadId(dto.getPrioridadId());
        if (dto.getUsuarioAsignadoId() != null) {
            model.setUsuarioAsignadoId(dto.getUsuarioAsignadoId());
        }
        if (dto.getUsuarioCreadorId() != null) {
            model.setUsuarioCreadorId(dto.getUsuarioCreadorId());
        }
        return model;
    }

    public com.nuevospa.gestiontareas.dto.generated.Tarea toDto(Tarea model) {
        if (model == null) return null;
        
        com.nuevospa.gestiontareas.dto.generated.Tarea dto = 
            new com.nuevospa.gestiontareas.dto.generated.Tarea(model.getTitulo(), model.getEstadoId(), model.getPrioridadId());
        
        if (model.getId() != null) {
            dto.setId(model.getId());
        }
        dto.setDescripcion(model.getDescripcion());
        dto.setFechaCreacion(toOffsetDateTime(model.getFechaCreacion()));
        dto.setFechaVencimiento(toOffsetDateTime(model.getFechaVencimiento()));
        
        if (model.getUsuarioAsignadoId() != null) {
            dto.setUsuarioAsignadoId(model.getUsuarioAsignadoId());
        }
        if (model.getUsuarioCreadorId() != null) {
            dto.setUsuarioCreadorId(model.getUsuarioCreadorId());
        }
        return dto;
    }

    // Mappers para LoginRequest
    public com.nuevospa.gestiontareas.dto.LoginRequest toModel(com.nuevospa.gestiontareas.dto.generated.LoginRequest dto) {
        if (dto == null) return null;
        
        com.nuevospa.gestiontareas.dto.LoginRequest model = new com.nuevospa.gestiontareas.dto.LoginRequest();
        model.setEmail(dto.getEmail());
        model.setPassword(dto.getPassword());
        return model;
    }

    public com.nuevospa.gestiontareas.dto.generated.LoginRequest toDto(com.nuevospa.gestiontareas.dto.LoginRequest model) {
        if (model == null) return null;
        
        com.nuevospa.gestiontareas.dto.generated.LoginRequest dto = 
            new com.nuevospa.gestiontareas.dto.generated.LoginRequest(model.getEmail(), model.getPassword());
        return dto;
    }

    // Mappers para LoginResponse
    public com.nuevospa.gestiontareas.dto.generated.LoginResponse toDto(com.nuevospa.gestiontareas.dto.LoginResponse model) {
        if (model == null) return null;
        
        com.nuevospa.gestiontareas.dto.generated.LoginResponse dto = 
            new com.nuevospa.gestiontareas.dto.generated.LoginResponse(
                model.getToken(), 
                model.getTokenType(), 
                model.getUsuarioId(), 
                model.getNombre(), 
                model.getEmail(), 
                model.getRol().name()
            );
        return dto;
    }

    // Mappers para EstadoTarea
    public com.nuevospa.gestiontareas.dto.generated.EstadoTarea toDto(EstadoTarea model) {
        if (model == null) return null;
        
        com.nuevospa.gestiontareas.dto.generated.EstadoTarea dto = 
            new com.nuevospa.gestiontareas.dto.generated.EstadoTarea(model.getNombre());
        
        dto.setId(model.getId());
        dto.setDescripcion(model.getDescripcion());
        return dto;
    }

    public List<com.nuevospa.gestiontareas.dto.generated.EstadoTarea> toEstadoDtoList(List<EstadoTarea> models) {
        if (models == null) return null;
        return models.stream().map(this::toDto).collect(Collectors.toList());
    }

    // Mappers para Prioridad
    public com.nuevospa.gestiontareas.dto.generated.Prioridad toDto(Prioridad model) {
        if (model == null) return null;
        
        com.nuevospa.gestiontareas.dto.generated.Prioridad dto = 
            new com.nuevospa.gestiontareas.dto.generated.Prioridad(model.getNombre());
        
        dto.setId(model.getId());
        dto.setDescripcion(model.getDescripcion());
        return dto;
    }

    public List<com.nuevospa.gestiontareas.dto.generated.Prioridad> toPrioridadDtoList(List<Prioridad> models) {
        if (models == null) return null;
        return models.stream().map(this::toDto).collect(Collectors.toList());
    }

    // Mappers para Usuario
    public com.nuevospa.gestiontareas.dto.generated.Usuario toDto(Usuario model) {
        if (model == null) return null;
        
        RolEnum rolEnum = RolEnum.fromValue(model.getRol().name());
        
        com.nuevospa.gestiontareas.dto.generated.Usuario dto = 
            new com.nuevospa.gestiontareas.dto.generated.Usuario(
                model.getEmail(), 
                null,
                model.getNombre(), 
                model.getApellido(), 
                rolEnum
            );
        
        dto.setId(model.getId());
        dto.setFechaCreacion(toOffsetDateTime(model.getFechaCreacion()));
        return dto;
    }

    public List<com.nuevospa.gestiontareas.dto.generated.Usuario> toUsuarioDtoList(List<Usuario> models) {
        if (models == null) return null;
        return models.stream().map(this::toDto).collect(Collectors.toList());
    }

    // Mappers para PageTarea
    public com.nuevospa.gestiontareas.dto.generated.PageTarea toDto(PageTarea model) {
        if (model == null) return null;
        
        com.nuevospa.gestiontareas.dto.generated.PageTarea dto = new com.nuevospa.gestiontareas.dto.generated.PageTarea();
        
        // Mapear el contenido (lista de tareas)
        if (model.getContent() != null) {
            dto.setContent(model.getContent().stream()
                    .map(this::toDto)
                    .collect(Collectors.toList()));
        }
        
        // Mapear propiedades de paginación
        dto.setTotalPages(model.getTotalPages());
        dto.setTotalElements(model.getTotalElements());
        dto.setSize(model.getSize());
        dto.setNumber(model.getNumber());
        dto.setNumberOfElements(model.getNumberOfElements());
        dto.setFirst(model.isFirst());
        dto.setLast(model.isLast());
        dto.setEmpty(model.isEmpty());
        
        return dto;
    }

    // Métodos para convertir ResponseEntity
    public ResponseEntity<com.nuevospa.gestiontareas.dto.generated.Tarea> toTareaResponse(ResponseEntity<Tarea> response) {
        if (response == null) return null;
        
        if (response.getBody() == null) {
            return new ResponseEntity<>(response.getStatusCode());
        }
        
        return new ResponseEntity<>(toDto(response.getBody()), response.getHeaders(), response.getStatusCode());
    }

    public ResponseEntity<List<com.nuevospa.gestiontareas.dto.generated.EstadoTarea>> toEstadoListResponse(ResponseEntity<List<EstadoTarea>> response) {
        if (response == null) return null;
        
        if (response.getBody() == null) {
            return new ResponseEntity<>(response.getStatusCode());
        }
        
        return new ResponseEntity<>(toEstadoDtoList(response.getBody()), response.getHeaders(), response.getStatusCode());
    }

    public ResponseEntity<List<com.nuevospa.gestiontareas.dto.generated.Prioridad>> toPrioridadListResponse(ResponseEntity<List<Prioridad>> response) {
        if (response == null) return null;
        
        if (response.getBody() == null) {
            return new ResponseEntity<>(response.getStatusCode());
        }
        
        return new ResponseEntity<>(toPrioridadDtoList(response.getBody()), response.getHeaders(), response.getStatusCode());
    }

    public ResponseEntity<com.nuevospa.gestiontareas.dto.generated.Usuario> toUsuarioResponse(ResponseEntity<Usuario> response) {
        if (response == null) return null;
        
        if (response.getBody() == null) {
            return new ResponseEntity<>(response.getStatusCode());
        }
        
        return new ResponseEntity<>(toDto(response.getBody()), response.getHeaders(), response.getStatusCode());
    }

    public ResponseEntity<List<com.nuevospa.gestiontareas.dto.generated.Usuario>> toUsuarioListResponse(ResponseEntity<List<Usuario>> response) {
        if (response == null) return null;
        
        if (response.getBody() == null) {
            return new ResponseEntity<>(response.getStatusCode());
        }
        
        return new ResponseEntity<>(toUsuarioDtoList(response.getBody()), response.getHeaders(), response.getStatusCode());
    }

    public ResponseEntity<com.nuevospa.gestiontareas.dto.generated.PageTarea> toPageTareaResponse(ResponseEntity<PageTarea> response) {
        if (response == null) return null;
        
        if (response.getBody() == null) {
            return new ResponseEntity<>(response.getStatusCode());
        }
        
        return new ResponseEntity<>(toDto(response.getBody()), response.getHeaders(), response.getStatusCode());
    }

    public ResponseEntity<com.nuevospa.gestiontareas.dto.generated.LoginResponse> toLoginResponse(ResponseEntity<LoginResponse> response) {
        if (response == null) return null;
        
        if (response.getBody() == null) {
            return new ResponseEntity<>(response.getStatusCode());
        }
        
        return new ResponseEntity<>(toDto(response.getBody()), response.getHeaders(), response.getStatusCode());
    }
} 