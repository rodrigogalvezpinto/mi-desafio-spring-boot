-- Insertar estados de tareas
INSERT INTO estados_tarea (id, nombre, descripcion) VALUES 
(1, 'PENDIENTE', 'Tarea pendiente de iniciar'),
(2, 'EN_PROGRESO', 'Tarea en proceso de ejecución'),
(3, 'COMPLETADA', 'Tarea finalizada exitosamente'),
(4, 'CANCELADA', 'Tarea cancelada');

-- Insertar prioridades
INSERT INTO prioridades (id, nombre, descripcion) VALUES 
(1, 'BAJA', 'Prioridad baja, puede esperar'),
(2, 'MEDIA', 'Prioridad media, atender cuando sea posible'),
(3, 'ALTA', 'Prioridad alta, atender pronto'),
(4, 'URGENTE', 'Prioridad urgente, atender inmediatamente');

-- Insertar usuarios con contraseña simple para pruebas
INSERT INTO usuarios (id, nombre, apellido, email, password, rol, fecha_creacion) VALUES 
('a1b2c3d4-e5f6-4a5b-8c7d-1e2f3a4b5c6d', 'Admin', 'Usuario', 'admin@nuevospa.com', 'password123', 'ADMIN', CURRENT_TIMESTAMP()),
('b2c3d4e5-f6a7-5b6c-9d8e-2f3a4b5c6d7e', 'Usuario', 'Normal', 'usuario@nuevospa.com', 'password123', 'USUARIO', CURRENT_TIMESTAMP()); 