# Enfoque API First en la Gestión de Tareas

Este proyecto implementa un enfoque API First para el desarrollo de la API RESTful de gestión de tareas. A continuación, se explica en qué consiste este enfoque y cómo se ha implementado en el proyecto.

## ¿Qué es API First?

API First es un enfoque de desarrollo donde la especificación de la API se define antes de comenzar la implementación. Esto garantiza que la API sea consistente, bien documentada y fácil de usar para los consumidores.

En este proyecto, utilizamos OpenAPI (anteriormente conocido como Swagger) para definir la especificación de la API y generar automáticamente código a partir de ella.

## Estructura del Proyecto

### Especificación OpenAPI

La especificación de la API se encuentra en:

```
src/main/resources/openapi.yml
```

Este archivo YAML define todos los endpoints, parámetros, respuestas y modelos de datos de la API.

### Código Generado

A partir de la especificación OpenAPI, se genera automáticamente:

1. **Interfaces de API**: Definen el contrato que debe implementar el controlador.
2. **DTOs (Data Transfer Objects)**: Representan los modelos de datos utilizados en la API.

El código generado se encuentra en:

```
target/generated-sources/openapi/
```

### Implementación

La implementación del enfoque API First se realiza mediante:

1. **ApiController**: Implementa las interfaces generadas y delega la lógica a los delegados correspondientes.
2. **Delegados de API**: Implementan la lógica de negocio para cada endpoint.
3. **DtoMapper**: Convierte entre los DTOs generados y los modelos/DTOs existentes.

## Ventajas del Enfoque API First

1. **Consistencia**: La API implementada siempre coincide con la especificación.
2. **Documentación automática**: La documentación siempre está actualizada.
3. **Facilidad de evolución**: Cambios en la API pueden ser manejados de manera más sistemática.
4. **Generación de clientes**: Facilita la generación de clientes para diferentes lenguajes.

## Controladores Tradicionales vs. API First

En este proyecto, existen dos conjuntos de controladores:

1. **Controladores Tradicionales**: Se encuentran en el paquete `com.nuevospa.gestiontareas.controller` y están marcados como `@Deprecated`.
2. **ApiController**: Implementa el enfoque API First y se encuentra en el paquete `com.nuevospa.gestiontareas.api`.

Se recomienda utilizar el `ApiController` en lugar de los controladores tradicionales, ya que sigue el enfoque API First y garantiza la consistencia con la especificación OpenAPI.

## Cómo Modificar la API

Para modificar la API, sigue estos pasos:

1. Actualiza la especificación OpenAPI en `src/main/resources/openapi.yml`.
2. Ejecuta `mvn clean compile` para regenerar el código a partir de la especificación.
3. Actualiza la implementación en los delegados de API si es necesario.
4. Actualiza el `DtoMapper` si has añadido o modificado modelos de datos.

## Documentación de la API

La documentación de la API está disponible en:

```
http://localhost:8080/api/swagger-ui.html
```

Esta documentación se genera automáticamente a partir de la especificación OpenAPI y siempre está actualizada.

## Conclusión

El enfoque API First implementado en este proyecto garantiza una API consistente, bien documentada y fácil de usar. Se recomienda seguir este enfoque para cualquier modificación o extensión de la API. 