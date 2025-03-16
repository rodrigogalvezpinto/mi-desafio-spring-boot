package com.nuevospa.gestiontareas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TareaDTO {
    private UUID id;
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaVencimiento;
    private UUID usuarioId;
    private String usuarioNombre;
    private Integer estadoId;
    private String estadoNombre;
    private Integer prioridadId;
    private String prioridadNombre;
} 