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
 * CambioEstadoTarea
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-16T14:52:53.339869700-03:00[America/Santiago]")
public class CambioEstadoTarea {

  private Integer estadoId;

  /**
   * Default constructor
   * @deprecated Use {@link CambioEstadoTarea#CambioEstadoTarea(Integer)}
   */
  @Deprecated
  public CambioEstadoTarea() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CambioEstadoTarea(Integer estadoId) {
    this.estadoId = estadoId;
  }

  public CambioEstadoTarea estadoId(Integer estadoId) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CambioEstadoTarea cambioEstadoTarea = (CambioEstadoTarea) o;
    return Objects.equals(this.estadoId, cambioEstadoTarea.estadoId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(estadoId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CambioEstadoTarea {\n");
    sb.append("    estadoId: ").append(toIndentedString(estadoId)).append("\n");
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

