package com.nuevospa.gestiontareas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class TareaCrearDTO {
    @NotBlank(message = "El título es obligatorio")
    private String titulo;
    
    private String descripcion;
    
    private LocalDateTime fechaVencimiento;
    
    private UUID usuarioId;
    
    @NotNull(message = "El estado es obligatorio")
    private Integer estadoId;
    
    @NotNull(message = "La prioridad es obligatoria")
    private Integer prioridadId;
} 