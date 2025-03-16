package com.nuevospa.gestiontareas.dto.generated;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
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
 * Usuario
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-16T14:52:53.339869700-03:00[America/Santiago]")
public class Usuario {

  private UUID id;

  private String email;

  private String password;

  private String nombre;

  private String apellido;

  /**
   * Gets or Sets rol
   */
  public enum RolEnum {
    ADMIN("ADMIN"),
    
    USUARIO("USUARIO");

    private String value;

    RolEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static RolEnum fromValue(String value) {
      for (RolEnum b : RolEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private RolEnum rol;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime fechaCreacion;

  /**
   * Default constructor
   * @deprecated Use {@link Usuario#Usuario(String, String, String, String, RolEnum)}
   */
  @Deprecated
  public Usuario() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Usuario(String email, String password, String nombre, String apellido, RolEnum rol) {
    this.email = email;
    this.password = password;
    this.nombre = nombre;
    this.apellido = apellido;
    this.rol = rol;
  }

  public Usuario id(UUID id) {
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

  public Usuario email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  */
  @NotNull @jakarta.validation.constraints.Email
  @Schema(name = "email", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Usuario password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Get password
   * @return password
  */
  @NotNull 
  @Schema(name = "password", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("password")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Usuario nombre(String nombre) {
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

  public Usuario apellido(String apellido) {
    this.apellido = apellido;
    return this;
  }

  /**
   * Get apellido
   * @return apellido
  */
  @NotNull 
  @Schema(name = "apellido", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("apellido")
  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public Usuario rol(RolEnum rol) {
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
  public RolEnum getRol() {
    return rol;
  }

  public void setRol(RolEnum rol) {
    this.rol = rol;
  }

  public Usuario fechaCreacion(OffsetDateTime fechaCreacion) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Usuario usuario = (Usuario) o;
    return Objects.equals(this.id, usuario.id) &&
        Objects.equals(this.email, usuario.email) &&
        Objects.equals(this.password, usuario.password) &&
        Objects.equals(this.nombre, usuario.nombre) &&
        Objects.equals(this.apellido, usuario.apellido) &&
        Objects.equals(this.rol, usuario.rol) &&
        Objects.equals(this.fechaCreacion, usuario.fechaCreacion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email, password, nombre, apellido, rol, fechaCreacion);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Usuario {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    nombre: ").append(toIndentedString(nombre)).append("\n");
    sb.append("    apellido: ").append(toIndentedString(apellido)).append("\n");
    sb.append("    rol: ").append(toIndentedString(rol)).append("\n");
    sb.append("    fechaCreacion: ").append(toIndentedString(fechaCreacion)).append("\n");
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

