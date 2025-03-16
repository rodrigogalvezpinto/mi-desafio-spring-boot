package com.nuevospa.gestiontareas.dto.generated;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Tarea
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-16T14:52:53.339869700-03:00[America/Santiago]")
public class Tarea {

  private UUID id;

  private String titulo;

  private String descripcion;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime fechaCreacion;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime fechaVencimiento;

  private Integer estadoId;

  private Integer prioridadId;

  private UUID usuarioAsignadoId;

  private UUID usuarioCreadorId;

  /**
   * Default constructor
   * @deprecated Use {@link Tarea#Tarea(String, Integer, Integer)}
   */
  @Deprecated
  public Tarea() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Tarea(String titulo, Integer estadoId, Integer prioridadId) {
    this.titulo = titulo;
    this.estadoId = estadoId;
    this.prioridadId = prioridadId;
  }

  public Tarea id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @Valid 
  @Schema(name = "id", accessMode = Schema.AccessMode.READ_ONLY, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Tarea titulo(String titulo) {
    this.titulo = titulo;
    return this;
  }

  /**
   * Get titulo
   * @return titulo
  */
  @NotNull 
  @Schema(name = "titulo", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("titulo")
  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public Tarea descripcion(String descripcion) {
    this.descripcion = descripcion;
    return this;
  }

  /**
   * Get descripcion
   * @return descripcion
  */
  
  @Schema(name = "descripcion", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("descripcion")
  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Tarea fechaCreacion(OffsetDateTime fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
    return this;
  }

  /**
   * Get fechaCreacion
   * @return fechaCreacion
  */
  @Valid 
  @Schema(name = "fechaCreacion", accessMode = Schema.AccessMode.READ_ONLY, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("fechaCreacion")
  public OffsetDateTime getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(OffsetDateTime fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

  public Tarea fechaVencimiento(OffsetDateTime fechaVencimiento) {
    this.fechaVencimiento = fechaVencimiento;
    return this;
  }

  /**
   * Get fechaVencimiento
   * @return fechaVencimiento
  */
  @Valid 
  @Schema(name = "fechaVencimiento", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("fechaVencimiento")
  public OffsetDateTime getFechaVencimiento() {
    return fechaVencimiento;
  }

  public void setFechaVencimiento(OffsetDateTime fechaVencimiento) {
    this.fechaVencimiento = fechaVencimiento;
  }

  public Tarea estadoId(Integer estadoId) {
    this.estadoId = estadoId;
    return this;
  }

  /**
   * Get estadoId
   * @return estadoId
  */
  @NotNull 
  @Schema(name = "estadoId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("estadoId")
  public Integer getEstadoId() {
    return estadoId;
  }

  public void setEstadoId(Integer estadoId) {
    this.estadoId = estadoId;
  }

  public Tarea prioridadId(Integer prioridadId) {
    this.prioridadId = prioridadId;
    return this;
  }

  /**
   * Get prioridadId
   * @return prioridadId
  */
  @NotNull 
  @Schema(name = "prioridadId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("prioridadId")
  public Integer getPrioridadId() {
    return prioridadId;
  }

  public void setPrioridadId(Integer prioridadId) {
    this.prioridadId = prioridadId;
  }

  public Tarea usuarioAsignadoId(UUID usuarioAsignadoId) {
    this.usuarioAsignadoId = usuarioAsignadoId;
    return this;
  }

  /**
   * Get usuarioAsignadoId
   * @return usuarioAsignadoId
  */
  @Valid 
  @Schema(name = "usuarioAsignadoId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("usuarioAsignadoId")
  public UUID getUsuarioAsignadoId() {
    return usuarioAsignadoId;
  }

  public void setUsuarioAsignadoId(UUID usuarioAsignadoId) {
    this.usuarioAsignadoId = usuarioAsignadoId;
  }

  public Tarea usuarioCreadorId(UUID usuarioCreadorId) {
    this.usuarioCreadorId = usuarioCreadorId;
    return this;
  }

  /**
   * Get usuarioCreadorId
   * @return usuarioCreadorId
  */
  @Valid 
  @Schema(name = "usuarioCreadorId", accessMode = Schema.AccessMode.READ_ONLY, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("usuarioCreadorId")
  public UUID getUsuarioCreadorId() {
    return usuarioCreadorId;
  }

  public void setUsuarioCreadorId(UUID usuarioCreadorId) {
    this.usuarioCreadorId = usuarioCreadorId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Tarea tarea = (Tarea) o;
    return Objects.equals(this.id, tarea.id) &&
        Objects.equals(this.titulo, tarea.titulo) &&
        Objects.equals(this.descripcion, tarea.descripcion) &&
        Objects.equals(this.fechaCreacion, tarea.fechaCreacion) &&
        Objects.equals(this.fechaVencimiento, tarea.fechaVencimiento) &&
        Objects.equals(this.estadoId, tarea.estadoId) &&
        Objects.equals(this.prioridadId, tarea.prioridadId) &&
        Objects.equals(this.usuarioAsignadoId, tarea.usuarioAsignadoId) &&
        Objects.equals(this.usuarioCreadorId, tarea.usuarioCreadorId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, titulo, descripcion, fechaCreacion, fechaVencimiento, estadoId, prioridadId, usuarioAsignadoId, usuarioCreadorId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Tarea {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    titulo: ").append(toIndentedString(titulo)).append("\n");
    sb.append("    descripcion: ").append(toIndentedString(descripcion)).append("\n");
    sb.append("    fechaCreacion: ").append(toIndentedString(fechaCreacion)).append("\n");
    sb.append("    fechaVencimiento: ").append(toIndentedString(fechaVencimiento)).append("\n");
    sb.append("    estadoId: ").append(toIndentedString(estadoId)).append("\n");
    sb.append("    prioridadId: ").append(toIndentedString(prioridadId)).append("\n");
    sb.append("    usuarioAsignadoId: ").append(toIndentedString(usuarioAsignadoId)).append("\n");
    sb.append("    usuarioCreadorId: ").append(toIndentedString(usuarioCreadorId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

