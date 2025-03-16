package com.nuevospa.gestiontareas.dto.generated;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * EstadoTarea
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-16T14:52:53.339869700-03:00[America/Santiago]")
public class EstadoTarea {

  private Integer id;

  private String nombre;

  private String descripcion;

  /**
   * Default constructor
   * @deprecated Use {@link EstadoTarea#EstadoTarea(String)}
   */
  @Deprecated
  public EstadoTarea() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public EstadoTarea(String nombre) {
    this.nombre = nombre;
  }

  public EstadoTarea id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", accessMode = Schema.AccessMode.READ_ONLY, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public EstadoTarea nombre(String nombre) {
    this.nombre = nombre;
    return this;
  }

  /**
   * Get nombre
   * @return nombre
  */
  @NotNull 
  @Schema(name = "nombre", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("nombre")
  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public EstadoTarea descripcion(String descripcion) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EstadoTarea estadoTarea = (EstadoTarea) o;
    return Objects.equals(this.id, estadoTarea.id) &&
        Objects.equals(this.nombre, estadoTarea.nombre) &&
        Objects.equals(this.descripcion, estadoTarea.descripcion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nombre, descripcion);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EstadoTarea {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nombre: ").append(toIndentedString(nombre)).append("\n");
    sb.append("    descripcion: ").append(toIndentedString(descripcion)).append("\n");
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

