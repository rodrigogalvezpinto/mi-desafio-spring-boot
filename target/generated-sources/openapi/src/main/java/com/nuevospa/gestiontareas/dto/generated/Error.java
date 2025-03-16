package com.nuevospa.gestiontareas.dto.generated;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Error
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-16T14:52:53.339869700-03:00[America/Santiago]")
public class Error {

  private String codigo;

  private String mensaje;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime timestamp;

  @Valid
  private List<String> detalles;

  /**
   * Default constructor
   * @deprecated Use {@link Error#Error(String, String, OffsetDateTime)}
   */
  @Deprecated
  public Error() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Error(String codigo, String mensaje, OffsetDateTime timestamp) {
    this.codigo = codigo;
    this.mensaje = mensaje;
    this.timestamp = timestamp;
  }

  public Error codigo(String codigo) {
    this.codigo = codigo;
    return this;
  }

  /**
   * Get codigo
   * @return codigo
  */
  @NotNull 
  @Schema(name = "codigo", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("codigo")
  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public Error mensaje(String mensaje) {
    this.mensaje = mensaje;
    return this;
  }

  /**
   * Get mensaje
   * @return mensaje
  */
  @NotNull 
  @Schema(name = "mensaje", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("mensaje")
  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

  public Error timestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * Get timestamp
   * @return timestamp
  */
  @NotNull @Valid 
  @Schema(name = "timestamp", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("timestamp")
  public OffsetDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public Error detalles(List<String> detalles) {
    this.detalles = detalles;
    return this;
  }

  public Error addDetallesItem(String detallesItem) {
    if (this.detalles == null) {
      this.detalles = new ArrayList<>();
    }
    this.detalles.add(detallesItem);
    return this;
  }

  /**
   * Get detalles
   * @return detalles
  */
  
  @Schema(name = "detalles", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("detalles")
  public List<String> getDetalles() {
    return detalles;
  }

  public void setDetalles(List<String> detalles) {
    this.detalles = detalles;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Error error = (Error) o;
    return Objects.equals(this.codigo, error.codigo) &&
        Objects.equals(this.mensaje, error.mensaje) &&
        Objects.equals(this.timestamp, error.timestamp) &&
        Objects.equals(this.detalles, error.detalles);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codigo, mensaje, timestamp, detalles);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Error {\n");
    sb.append("    codigo: ").append(toIndentedString(codigo)).append("\n");
    sb.append("    mensaje: ").append(toIndentedString(mensaje)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    detalles: ").append(toIndentedString(detalles)).append("\n");
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

