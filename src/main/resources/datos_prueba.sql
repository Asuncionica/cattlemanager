-- =====================================================================
-- DATOS DE PRUEBA - CattleManager (ProjectDAM)
-- Base de datos: cattlemanager (PostgreSQL)
-- Ejecutar en pgAdmin: Tools > Query Tool > pegar y ejecutar
--
-- IMPORTANTE: Las contraseñas están en texto plano ("12345").
--   El DataInitializer las convierte a BCrypt al arrancar el backend.
--   Credenciales de acceso: cualquier email + contraseña 12345
-- =====================================================================

-- Limpiar todo respetando las FK (el orden importa)
TRUNCATE TABLE
    tarea,
    evento_sanitario,
    evento_reproductivo,
    evento_productivo,
    animal,
    granja,
    usuario,
    rol
RESTART IDENTITY CASCADE;


-- =====================================================================
-- ROLES (3 roles del sistema)
-- =====================================================================
INSERT INTO rol (id, nombre) OVERRIDING SYSTEM VALUE VALUES
(1, 'Veterinario'),
(2, 'Encargado'),
(3, 'Peón');


-- =====================================================================
-- USUARIOS (2 por cada rol = 6 usuarios)
-- Contraseña: 12345 (se hashea al arrancar el backend)
-- =====================================================================
INSERT INTO usuario (id, nombre, email, password, rol_id) OVERRIDING SYSTEM VALUE VALUES
(1, 'Ana García',     'ana@test.com',    '12345', 1),
(2, 'Pedro Sánchez',  'pedro@test.com',  '12345', 1),
(3, 'María López',    'maria@test.com',  '12345', 2),
(4, 'Carlos Ruiz',    'carlos@test.com', '12345', 2),
(5, 'Juan Martínez',  'juan@test.com',   '12345', 3),
(6, 'Laura Torres',   'laura@test.com',  '12345', 3);


-- =====================================================================
-- GRANJA (1 granja gestionada por el encargado María López)
-- =====================================================================
INSERT INTO granja (id, nombre, ubicacion, telefono, usuario_id) OVERRIDING SYSTEM VALUE VALUES
(1, 'Ganadería El Roble', 'Calle Mayor 12, Córdoba', '957123456', 3);


-- =====================================================================
-- ANIMALES (10 cabezas de ganado)
-- =====================================================================
INSERT INTO animal (id, identificador, raza, sexo, fecha_nacimiento, granja_id) OVERRIDING SYSTEM VALUE VALUES
(1,  'ES001', 'Frisona',   'Hembra', '2020-03-15', 1),
(2,  'ES002', 'Frisona',   'Hembra', '2019-07-22', 1),
(3,  'ES003', 'Charolesa', 'Macho',  '2021-01-10', 1),
(4,  'ES004', 'Limusina',  'Hembra', '2020-11-05', 1),
(5,  'ES005', 'Frisona',   'Hembra', '2018-09-30', 1),
(6,  'ES006', 'Retinta',   'Hembra', '2022-02-14', 1),
(7,  'ES007', 'Charolesa', 'Macho',  '2021-06-20', 1),
(8,  'ES008', 'Limusina',  'Hembra', '2019-12-01', 1),
(9,  'ES009', 'Retinta',   'Macho',  '2023-04-08', 1),
(10, 'ES010', 'Frisona',   'Hembra', '2020-08-17', 1);


-- =====================================================================
-- EVENTOS PRODUCTIVOS (9 eventos: producción leche, pesajes, controles)
-- =====================================================================
INSERT INTO evento_productivo (tipo, descripcion, fecha, animal_id) VALUES
('Producción leche',  'Producción diaria: 28 litros',         '2025-01-10', 1),
('Producción leche',  'Producción diaria: 32 litros',         '2025-01-10', 2),
('Producción leche',  'Producción diaria: 25 litros',         '2025-01-10', 5),
('Producción leche',  'Producción diaria: 30 litros',         '2025-02-05', 1),
('Pesaje',            'Peso registrado: 580 kg',              '2025-03-01', 3),
('Pesaje',            'Peso registrado: 620 kg',              '2025-03-01', 7),
('Pesaje',            'Peso registrado: 490 kg',              '2025-03-15', 4),
('Control calidad',   'Grasa 4.1%, proteína 3.3%',            '2025-04-01', 2),
('Control calidad',   'Grasa 3.8%, proteína 3.1%',            '2025-04-01', 5);


-- =====================================================================
-- EVENTOS REPRODUCTIVOS (7 eventos: inseminaciones, partos, ecografías)
-- =====================================================================
INSERT INTO evento_reproductivo (tipo, descripcion, fecha, animal_id) VALUES
('Inseminación',    'Inseminación artificial, semen toro Frisón',      '2024-06-15', 1),
('Inseminación',    'Inseminación artificial, semen toro Charolés',    '2024-07-10', 4),
('Parto',           'Parto sin complicaciones, ternero macho',          '2025-03-20', 2),
('Parto',           'Parto gemelar, dos terneras hembras',              '2025-01-08', 5),
('Celo detectado',  'Celo observado, se programa inseminación',        '2025-04-12', 6),
('Ecografía',       'Gestación confirmada: 3 meses',                   '2024-10-05', 1),
('Ecografía',       'Gestación confirmada: 2 meses',                   '2024-11-20', 4);


-- =====================================================================
-- EVENTOS SANITARIOS (12 eventos: vacunas, desparasitaciones, tratamientos)
-- =====================================================================
INSERT INTO evento_sanitario (tipo, descripcion, fecha, animal_id) VALUES
('Vacuna',           'Vacuna IBR/BVD anual',                          '2025-01-05', 1),
('Vacuna',           'Vacuna IBR/BVD anual',                          '2025-01-05', 2),
('Vacuna',           'Vacuna IBR/BVD anual',                          '2025-01-05', 3),
('Vacuna',           'Vacuna IBR/BVD anual',                          '2025-01-05', 4),
('Vacuna',           'Vacuna IBR/BVD anual',                          '2025-01-05', 5),
('Desparasitación',  'Ivermectina 1% subcutánea, dosis 5 ml',         '2024-11-15', 1),
('Desparasitación',  'Ivermectina 1% subcutánea, dosis 5 ml',         '2024-11-15', 2),
('Desparasitación',  'Ivermectina 1% subcutánea, dosis 5 ml',         '2024-11-15', 6),
('Tratamiento',      'Antibiótico por mastitis leve, 3 días',          '2025-02-10', 1),
('Tratamiento',      'Antiinflamatorio por cojera pie derecho',        '2025-03-05', 8),
('Revisión',         'Revisión general anual: todo correcto',          '2025-04-02', 9),
('Revisión',         'Revisión general anual: todo correcto',          '2025-04-02', 10);


-- =====================================================================
-- TAREAS (5 tareas asignadas a la granja y repartidas entre peones)
-- =====================================================================
INSERT INTO tarea (titulo, descripcion, fecha_vencimiento, completada, granja_id, peon_id) VALUES
('Limpieza establos',     'Limpiar y desinfectar establos norte y sur',       '2025-04-15', false, 1, 5),
('Control de forraje',    'Revisar stock de forraje y anotar consumo semanal','2025-04-10', true,  1, 5),
('Reparar vallado',       'Reparar tramo de vallado caído en parcela 3',      '2025-04-20', false, 1, 6),
('Recogida de muestras',  'Recoger muestras de leche para laboratorio',       '2025-04-18', false, 1, 5),
('Mantenimiento equipo',  'Revisar y engrasar ordeñadora mecánica',           '2025-04-25', false, 1, 6);


-- =====================================================================
-- Resetear secuencias para que los próximos INSERT auto-incrementen bien
-- =====================================================================
SELECT setval('rol_id_seq',     (SELECT MAX(id) FROM rol));
SELECT setval('usuario_id_seq', (SELECT MAX(id) FROM usuario));
SELECT setval('granja_id_seq',  (SELECT MAX(id) FROM granja));
SELECT setval('animal_id_seq',  (SELECT MAX(id) FROM animal));
