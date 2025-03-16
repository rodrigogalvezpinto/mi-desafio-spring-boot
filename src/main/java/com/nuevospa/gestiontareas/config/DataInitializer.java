package com.nuevospa.gestiontareas.config;

import com.nuevospa.gestiontareas.model.EstadoTarea;
import com.nuevospa.gestiontareas.model.Prioridad;
import com.nuevospa.gestiontareas.model.Rol;
import com.nuevospa.gestiontareas.model.Usuario;
import com.nuevospa.gestiontareas.repository.EstadoTareaRepository;
import com.nuevospa.gestiontareas.repository.PrioridadRepository;
import com.nuevospa.gestiontareas.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final EstadoTareaRepository estadoTareaRepository;
    private final PrioridadRepository prioridadRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        log.info("Inicializando datos de prueba...");
        
        // Crear estados de tarea
        if (estadoTareaRepository.count() == 0) {
            log.info("Creando estados de tarea...");
            estadoTareaRepository.save(EstadoTarea.builder().nombre("Pendiente").descripcion("Tareas que aún no se han iniciado").build());
            estadoTareaRepository.save(EstadoTarea.builder().nombre("En Progreso").descripcion("Tareas que están siendo trabajadas").build());
            estadoTareaRepository.save(EstadoTarea.builder().nombre("Completada").descripcion("Tareas que han sido finalizadas").build());
            estadoTareaRepository.save(EstadoTarea.builder().nombre("Cancelada").descripcion("Tareas que han sido canceladas").build());
            log.info("Estados de tarea creados");
        }
        
        // Crear prioridades
        if (prioridadRepository.count() == 0) {
            log.info("Creando prioridades...");
            prioridadRepository.save(Prioridad.builder().nombre("Alta").descripcion("Tareas urgentes que requieren atención inmediata").build());
            prioridadRepository.save(Prioridad.builder().nombre("Media").descripcion("Tareas importantes pero no urgentes").build());
            prioridadRepository.save(Prioridad.builder().nombre("Baja").descripcion("Tareas que pueden esperar").build());
            log.info("Prioridades creadas");
        }
        
        // Crear usuarios
        if (usuarioRepository.count() == 0) {
            log.info("Creando usuarios...");
            
            // Admin
            Usuario admin = Usuario.builder()
                    .nombre("Admin")
                    .apellido("Sistema")
                    .email("admin@example.com")
                    .password(passwordEncoder.encode("admin123"))
                    .rol(Rol.ADMIN)
                    .build();
            usuarioRepository.save(admin);
            
            // Usuario normal
            Usuario usuario = Usuario.builder()
                    .nombre("Usuario")
                    .apellido("Normal")
                    .email("usuario@example.com")
                    .password(passwordEncoder.encode("usuario123"))
                    .rol(Rol.USUARIO)
                    .build();
            usuarioRepository.save(usuario);
            
            log.info("Usuarios creados");
        }
        
        log.info("Inicialización de datos completada");
    }
} 