# Desafío Técnico: Gestión de Tareas con Spring Boot y Java

La empresa NUEVO SPA desea desarrollar una plataforma de gestión de tareas para mejorar la productividad de sus equipos. El sistema permite a los usuarios crear, actualizar, eliminar y listar tareas. Además, incluye autenticación mediante JWT y documentación de la API utilizando OpenAPI y Swagger.

## Características Implementadas

- ✅ API RESTful con Spring Boot 3.1.x y Java 17
- ✅ Base de datos H2 en memoria
- ✅ Autenticación JWT
- ✅ Documentación con OpenAPI y Swagger
- ✅ Enfoque API First
- ✅ Pruebas unitarias
- ✅ Operaciones CRUD para tareas, usuarios, estados y prioridades

## Enfoque API First

Este proyecto implementa un enfoque API First, donde la especificación OpenAPI define el contrato de la API antes de la implementación. Para más detalles, consulta [README-API-FIRST.md](README-API-FIRST.md).

## Cómo Ejecutar la Aplicación

### Requisitos Previos

- Java 17 o superior
- Maven 3.6 o superior

### Pasos para Ejecutar

1. Clonar el repositorio:
   ```
   git clone https://github.com/tu-usuario/desafio-spring-boot.git
   cd desafio-spring-boot
   ```

2. Compilar el proyecto:
   ```
   ./mvnw clean package
   ```

3. Ejecutar la aplicación:
   ```
   ./mvnw spring-boot:run
   ```

4. Acceder a la aplicación:
   - API: http://localhost:8080/api
   - Documentación Swagger: http://localhost:8080/api/swagger-ui.html
   - Consola H2: http://localhost:8080/api/h2-console (JDBC URL: jdbc:h2:mem:tareas, Usuario: sa, Contraseña: vacía)

## Pruebas

Para ejecutar las pruebas:
```
./mvnw test
```

Para más detalles sobre las pruebas, consulta [README-TESTS.md](README-TESTS.md).

## Documentación Adicional

- [README-API-FIRST.md](README-API-FIRST.md): Detalles sobre el enfoque API First.
- [README-TESTS.md](README-TESTS.md): Información sobre las pruebas.

## Estructura del Proyecto

El proyecto sigue una arquitectura en capas:

1. **Controladores**: Implementan la interfaz generada por OpenAPI.
2. **Servicios**: Contienen la lógica de negocio.
3. **Repositorios**: Acceden a la base de datos.
4. **Modelos**: Representan las entidades de la base de datos.
5. **DTOs**: Objetos para transferir datos entre capas.
6. **Seguridad**: Configuración y componentes de seguridad JWT.

## Endpoints Principales

- **POST /api/auth/login**: Autenticación de usuarios.
- **GET /api/tareas**: Listar tareas con filtros y paginación.
- **POST /api/tareas**: Crear una nueva tarea.
- **GET /api/tareas/{id}**: Obtener una tarea por ID.
- **PUT /api/tareas/{id}**: Actualizar una tarea.
- **PATCH /api/tareas/{id}/estado**: Actualizar el estado de una tarea.
- **DELETE /api/tareas/{id}**: Eliminar una tarea.

Para ver todos los endpoints disponibles, consulta la documentación Swagger.

## Objetivo:
Crear una API RESTful utilizando Spring Boot que gestione usuarios y tareas, aplicando buenas prácticas, principios SOLID y utilizando las tecnologías especificadas.

## Requisitos Técnicos:
### Java:
- Utiliza Java 17 para la implementación.
- Utiliza las características de Java 17, como lambdas y streams, cuando sea apropiado.
- Utilizar Maven como gestor de dependencias

### Spring Boot 3.4.x:
- Construye la aplicación utilizando Spring Boot 3.4.x (última versión disponible).

### Base de Datos:

- Utiliza una base de datos H2.
- Crea tres tablas: usuarios, tareas y estados_tarea.
- La tabla usuarios debe contener datos pre cargados.
- La tabla estados_tarea debe contener estados pre cargados.

### JPA:
- Implementa una capa de persistencia utilizando JPA para almacenar y recuperar las tareas.

### JWT (JSON Web Token):

- Implementa la autenticación utilizando JWT para validar usuarios.

### OpenAPI y Swagger:

- Documenta la API utilizando OpenAPI y Swagger.

## Funcionalidades:
### Autenticación:
- Implementa un endpoint para la autenticación de usuarios utilizando JWT. 

### CRUD de Tareas:
- Implementa operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para las tareas.

## Consideraciones:
### Seguridad:
- Asegúrate de que las operaciones CRUD de tareas solo sean accesibles para usuarios autenticados.

### Documentación:
- Utiliza OpenAPI y Swagger para documentar claramente la API.
- Puntos adicionales si se genera el API mediante metodologia API First. Generar el archivo openapi.yml Nota: Ejemplo Plugin Maven groupId org.openapitools, artifactId openapi-generator-maven-plugin

### Código Limpio:
- Escribe código ordenado, aplicando buenas prácticas y principios SOLID.

### Creatividad
- Se espera dada la descripción del problema se creen las entidades y metodos en consecuencia a lo solicitado.

## Entregables:
### Repositorio de GitHub:
- Realiza un Pull request a este repositorio indicando tu nombre, correo y cargo al que postulas.
- Todos los PR serán rechazados, no es un indicador de la prueba.

### Documentación:
- Incluye instrucciones claras sobre cómo ejecutar y probar la aplicación.
- **Incluir Json de prueba en un archivo texto o mediante un proyecto postman** Nota: Si no va se restaran puntos de la evaluación

## Evaluación:
Se evaluará la solución en función de los siguientes criterios:

- Correcta implementación de las funcionalidades solicitadas.
- Aplicación de buenas prácticas de desarrollo, patrones de diseño y principios SOLID.
- Uso adecuado de Java 17, Spring Boot 3.4.x, H2, JWT, OpenAPI y Swagger.
- Claridad y completitud de la documentación.
- **Puntos extras si la generación de la API se realizo mediante API First**
