package com.nuevospa.gestiontareas.dto.generated;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.UUID;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * LoginResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-16T14:52:53.339869700-03:00[America/Santiago]")
public class LoginResponse {

  private String token;

  private String tokenType;

  private UUID usuarioId;

  private String nombre;

  private String email;

  private String rol;

  /**
   * Default constructor
   * @deprecated Use {@link LoginResponse#LoginResponse(String, String, UUID, String, String, String)}
   */
  @Deprecated
  public LoginResponse() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public LoginResponse(String token, String tokenType, UUID usuarioId, String nombre, String email, String rol) {
    this.token = token;
    this.tokenType = tokenType;
    this.usuarioId = usuarioId;
    this.nombre = nombre;
    this.email = email;
    this.rol = rol;
  }

  public LoginResponse token(String token) {
    this.token = token;
    return this;
  }

  /**
   * Get token
   * @return token
  */
  @NotNull 
  @Schema(name = "token", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("token")
  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public LoginResponse tokenType(String tokenType) {
    this.tokenType = tokenType;
    return this;
  }

  /**
   * Get tokenType
   * @return tokenType
  */
  @NotNull 
  @Schema(name = "tokenType", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("tokenType")
  public String getTokenType() {
    return tokenType;
  }

  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }

  public LoginResponse usuarioId(UUID usuarioId) {
    this.usuarioId = usuarioId;
    return this;
  }

  /**
   * Get usuarioId
   * @return usuarioId
  */
  @NotNull @Valid 
  @Schema(name = "usuarioId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("usuarioId")
  public UUID getUsuarioId() {
    return usuarioId;
  }

  public void setUsuarioId(UUID usuarioId) {
    this.usuarioId = usuarioId;
  }

  public LoginResponse nombre(String nombre) {
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

  public LoginResponse email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  */
  @NotNull 
  @Schema(name = "email", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LoginResponse rol(String rol) {
    this.rol = rol;
    return this;
  }

  /**
   * Get rol
   * @return rol
  */
  @NotNull 
  @Schema(name = "rol", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("rol")
  public String getRol() {
    return rol;
  }

  public void setRol(String rol) {
    this.rol = rol;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoginResponse loginResponse = (LoginResponse) o;
    return Objects.equals(this.token, loginResponse.token) &&
        Objects.equals(this.tokenType, loginResponse.tokenType) &&
        Objects.equals(this.usuarioId, loginResponse.usuarioId) &&
        Objects.equals(this.nombre, loginResponse.nombre) &&
        Objects.equals(this.email, loginResponse.email) &&
        Objects.equals(this.rol, loginResponse.rol);
  }

  @Override
  public int hashCode() {
    return Objects.hash(token, tokenType, usuarioId, nombre, email, rol);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoginResponse {\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
    sb.append("    tokenType: ").append(toIndentedString(tokenType)).append("\n");
    sb.append("    usuarioId: ").append(toIndentedString(usuarioId)).append("\n");
    sb.append("    nombre: ").append(toIndentedString(nombre)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    rol: ").append(toIndentedString(rol)).append("\n");
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

