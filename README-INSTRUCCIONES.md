# Instrucciones para ejecutar y probar la API de Gestión de Tareas

Este documento proporciona instrucciones detalladas sobre cómo ejecutar y probar la API de Gestión de Tareas desarrollada para NUEVO SPA.

## Requisitos previos

- Java 17 o superior
- Maven 3.6.0 o superior
- Postman (opcional, para probar la API)

## Pasos para ejecutar la aplicación

### 1. Clonar el repositorio

```bash
git clone <URL_DEL_REPOSITORIO>
cd desafio-spring-boot
```

### 2. Compilar el proyecto

```bash
./mvnw clean package
```

### 3. Ejecutar la aplicación

```bash
./mvnw spring-boot:run
```

La aplicación se iniciará en `http://localhost:8080` con el contexto `/api`.

## Acceso a la documentación de la API

Una vez que la aplicación esté en ejecución, puedes acceder a la documentación de la API a través de Swagger UI:

```
http://localhost:8080/api/swagger-ui/index.html
```

## Usuarios predefinidos

La aplicación viene con dos usuarios predefinidos:

1. **Administrador**:
   - Email: `admin@nuevospa.com`
   - Contraseña: `password123`
   - Rol: `ADMIN`

2. **Usuario normal**:
   - Email: `usuario@nuevospa.com`
   - Contraseña: `password123`
   - Rol: `USUARIO`

## Probar la API con Postman

Se incluye una colección de Postman (`postman_collection.json`) que contiene ejemplos de todas las solicitudes disponibles en la API. Para utilizarla:

1. Importa la colección en Postman
2. Configura las variables de entorno en Postman:
   - `token`: Se actualizará automáticamente después de iniciar sesión
   - `tareaId`: ID de una tarea creada (deberás actualizarlo manualmente después de crear una tarea)
   - `usuarioId`: Ya viene configurado con el ID del administrador

### Flujo de prueba recomendado:

1. Ejecuta la solicitud "Login" para obtener un token JWT
2. Crea una nueva tarea con la solicitud "Crear Tarea"
3. Copia el ID de la tarea creada (disponible en la cabecera `Location` de la respuesta) y actualiza la variable `tareaId` en Postman
4. Prueba las demás operaciones CRUD sobre la tarea

## Enfoque API First

Este proyecto sigue el enfoque API First, donde primero se define la API mediante un archivo OpenAPI (YAML) y luego se genera código a partir de él. El archivo de definición se encuentra en:

```
src/main/resources/openapi.yml
```

Para regenerar el código a partir de la definición OpenAPI, ejecuta:

```bash
./mvnw clean generate-sources
```

## Estructura del proyecto

- `src/main/java/com/nuevospa/gestiontareas/api/generated`: Interfaces generadas a partir de la definición OpenAPI
- `src/main/java/com/nuevospa/gestiontareas/dto/generated`: DTOs generados a partir de la definición OpenAPI
- `src/main/java/com/nuevospa/gestiontareas/api/delegate`: Implementaciones de los delegados de la API
- `src/main/java/com/nuevospa/gestiontareas/model`: Entidades JPA
- `src/main/java/com/nuevospa/gestiontareas/repository`: Repositorios JPA
- `src/main/java/com/nuevospa/gestiontareas/service`: Servicios de negocio
- `src/main/java/com/nuevospa/gestiontareas/security`: Configuración de seguridad y JWT

## Base de datos

La aplicación utiliza una base de datos H2 en memoria. Puedes acceder a la consola H2 en:

```
http://localhost:8080/api/h2-console
```

Configuración de conexión:
- JDBC URL: `jdbc:h2:mem:tareas`
- Usuario: `sa`
- Contraseña: (dejar en blanco) 