package com.nuevospa.gestiontareas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "estados_tarea")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoTarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false, unique = true)
    private String nombre;

    private String descripcion;
} 