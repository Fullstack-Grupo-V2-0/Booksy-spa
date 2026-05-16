-- Inserción de 5 usuarios iniciales
-- Las passwords están en texto plano para simplificar el ejemplo académico
INSERT INTO usuarios (id_usuario, nombre, email, password, rol, activo)
VALUES (1, 'Admin Sistema',    'admin@booksyspa.cl',       'admin1234',   'ADMIN',   1);

INSERT INTO usuarios (id_usuario, nombre, email, password, rol, activo)
VALUES (2, 'Matías Pérez',     'matias.perez@email.cl',    'pass1234',    'CLIENTE',  1);

INSERT INTO usuarios (id_usuario, nombre, email, password, rol, activo)
VALUES (3, 'Catalina Mora',    'catalina.mora@email.cl',   'pass5678',    'CLIENTE',  1);

INSERT INTO usuarios (id_usuario, nombre, email, password, rol, activo)
VALUES (4, 'Joaquín Tapia',    'joaquin.tapia@email.cl',   'pass9012',    'CLIENTE',  1);

INSERT INTO usuarios (id_usuario, nombre, email, password, rol, activo)
VALUES (5, 'Fernanda Ríos',    'fernanda.rios@email.cl',   'pass3456',    'CLIENTE',  1);
