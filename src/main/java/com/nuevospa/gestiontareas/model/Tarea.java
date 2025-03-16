package com.nuevospa.gestiontareas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tareas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "El título es obligatorio")
    @Size(min = 3, max = 50, message = "El título debe tener entre 3 y 50 caracteres")
    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descripcion;

    @CreationTimestamp
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_vencimiento")
    private LocalDateTime fechaVencimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_asignado_id")
    private Usuario usuarioAsignado;
    
    @Column(name = "usuario_asignado_id", insertable = false, updatable = false)
    private UUID usuarioAsignadoId;

    @NotNull(message = "El estado es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_id", nullable = false)
    private EstadoTarea estado;

    @Column(name = "estado_id", insertable = false, updatable = false)
    private Integer estadoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prioridad_id", nullable = false)
    private Prioridad prioridad;

    @Column(name = "prioridad_id", insertable = false, updatable = false)
    private Integer prioridadId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_creador_id", nullable = false, updatable = false)
    private Usuario usuarioCreador;

    @Column(name = "usuario_creador_id", insertable = false, updatable = false)
    private UUID usuarioCreadorId;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
    }
} 