package com.nuevospa.gestiontareas.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CambioEstadoTarea {
    @NotNull(message = "El ID del estado es obligatorio")
    private Integer estadoId;
} 