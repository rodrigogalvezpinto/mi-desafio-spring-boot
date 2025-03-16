# Pruebas de la API de Gestión de Tareas

Este documento proporciona instrucciones para ejecutar las pruebas de la API de Gestión de Tareas.

## Pruebas Unitarias

Las pruebas unitarias verifican el funcionamiento correcto de componentes individuales de la aplicación.

### Ejecutar todas las pruebas unitarias

```bash
./mvnw test
```

### Ejecutar una prueba específica

```bash
./mvnw test -Dtest=ApiControllerTest
```

## Pruebas de Integración

Las pruebas de integración verifican la interacción entre diferentes componentes de la aplicación.

### Ejecutar todas las pruebas de integración

```bash
./mvnw verify
```

## Pruebas Manuales con Postman

Para probar manualmente la API, puedes utilizar la colección de Postman proporcionada (`postman_collection.json`).

1. Importa la colección en Postman
2. Configura las variables de entorno:
   - `token`: Se actualizará automáticamente después de iniciar sesión
   - `tareaId`: ID de una tarea creada (deberás actualizarlo manualmente después de crear una tarea)
   - `usuarioId`: Ya viene configurado con el ID del administrador

### Flujo de prueba recomendado:

1. Ejecuta la solicitud "Login" para obtener un token JWT
2. Crea una nueva tarea con la solicitud "Crear Tarea"
3. Copia el ID de la tarea creada (disponible en la cabecera `Location` de la respuesta) y actualiza la variable `tareaId` en Postman
4. Prueba las demás operaciones CRUD sobre la tarea

## Cobertura de Pruebas

Para generar un informe de cobertura de pruebas:

```bash
./mvnw verify jacoco:report
```

El informe se generará en `target/site/jacoco/index.html`. 