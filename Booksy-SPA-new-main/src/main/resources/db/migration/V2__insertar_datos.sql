-- --- Inserción de Autores ---

-- Registros de 10 autores chilenos
-- Se asignan IDs manuales para referenciar en las llaves foráneas
INSERT INTO autores (id_autor, nombre, apellido, fecha_nacimiento, nacionalidad, premios) VALUES (1, 'Roberto', 'Bolaño', '1953-04-28', 'Chilena', 'Premio Rómulo Gallegos');
INSERT INTO autores (id_autor, nombre, apellido, fecha_nacimiento, nacionalidad, premios) VALUES (2, 'Isabel', 'Allende', '1942-08-02', 'Chilena', 'Premio Nacional de Literatura 2010');
INSERT INTO autores (id_autor, nombre, apellido, fecha_nacimiento, nacionalidad, premios) VALUES (3, 'Gabriela', 'Mistral', '1889-04-07', 'Chilena', 'Premio Nobel de Literatura 1945');
INSERT INTO autores (id_autor, nombre, apellido, fecha_nacimiento, nacionalidad, premios) VALUES (4, 'Pablo', 'Neruda', '1904-07-12', 'Chilena', 'Premio Nobel de Literatura 1971');
INSERT INTO autores (id_autor, nombre, apellido, fecha_nacimiento, nacionalidad, premios) VALUES (5, 'Nicanor', 'Parra', '1914-09-05', 'Chilena', 'Premio Cervantes 2011');
INSERT INTO autores (id_autor, nombre, apellido, fecha_nacimiento, nacionalidad, premios) VALUES (6, 'Pedro', 'Lemebel', '1952-11-21', 'Chilena', 'Premio Iberoamericano de Letras José Donoso');
INSERT INTO autores (id_autor, nombre, apellido, fecha_nacimiento, nacionalidad, premios) VALUES (7, 'Marcela', 'Paz', '1902-02-28', 'Chilena', 'Premio Nacional de Literatura 1982');
INSERT INTO autores (id_autor, nombre, apellido, fecha_nacimiento, nacionalidad, premios) VALUES (8, 'Alberto', 'Fuguet', '1964-03-07', 'Chilena', 'Premio Municipal de Literatura de Santiago');
INSERT INTO autores (id_autor, nombre, apellido, fecha_nacimiento, nacionalidad, premios) VALUES (9, 'Alejandro', 'Zambra', '1975-09-24', 'Chilena', 'Premio Altazor');
INSERT INTO autores (id_autor, nombre, apellido, fecha_nacimiento, nacionalidad, premios) VALUES (10, 'Hernán', 'Rivera Letelier', '1950-07-11', 'Chilena', 'Premio Nacional de Literatura 2022');

-- --- Inserción de Categorías ---

-- Registros de 10 categorías literarias
INSERT INTO categorias (id_categoria, nombre, descripcion) VALUES (1, 'Novela Contemporánea', 'Obras de ficción narrativa que reflejan la época actual.');
INSERT INTO categorias (id_categoria, nombre, descripcion) VALUES (2, 'Realismo Mágico', 'Narrativa que combina elementos realistas con sucesos fantásticos.');
INSERT INTO categorias (id_categoria, nombre, descripcion) VALUES (3, 'Poesía', 'Composiciones literarias concebidas como expresión artística de la belleza.');
INSERT INTO categorias (id_categoria, nombre, descripcion) VALUES (4, 'Antipoesía', 'Movimiento poético que utiliza lenguaje cotidiano y elementos prosaicos.');
INSERT INTO categorias (id_categoria, nombre, descripcion) VALUES (5, 'Crónica Urbana', 'Narración histórica y periodística enfocada en la marginalidad y la ciudad.');
INSERT INTO categorias (id_categoria, nombre, descripcion) VALUES (6, 'Literatura Infantil', 'Obras literarias dirigidas a un público infantil.');
INSERT INTO categorias (id_categoria, nombre, descripcion) VALUES (7, 'Novela de Formación', 'Narrativa que ilustra el desarrollo físico, moral y psicológico de un personaje.');
INSERT INTO categorias (id_categoria, nombre, descripcion) VALUES (8, 'Ficción Breve', 'Relatos o cuentos de extensión reducida.');
INSERT INTO categorias (id_categoria, nombre, descripcion) VALUES (9, 'Novela del Desierto', 'Obras narrativas ambientadas en las pampas salitreras y el norte grande.');
INSERT INTO categorias (id_categoria, nombre, descripcion) VALUES (10, 'Ensayo', 'Texto en prosa que explora, analiza, interpreta o evalúa un tema.');

-- --- Inserción de Libros ---

-- Registros de 10 libros de autores chilenos
-- Se enlazan los IDs de id_autor y id_categoria correspondientes
INSERT INTO libros (id_libro, isbn, titulo, id_autor, descripcion, fecha_publicacion, precio, id_categoria) VALUES (1, '978-8433966636', 'Los detectives salvajes', 1, 'Búsqueda de la poeta desaparecida Cesárea Tinajero.', '1998-11-01', 25.50, 1);
INSERT INTO libros (id_libro, isbn, titulo, id_autor, descripcion, fecha_publicacion, precio, id_categoria) VALUES (2, '978-8401020438', 'La casa de los espíritus', 2, 'La saga de la familia Trueba a lo largo de cuatro generaciones.', '1982-01-01', 18.90, 2);
INSERT INTO libros (id_libro, isbn, titulo, id_autor, descripcion, fecha_publicacion, precio, id_categoria) VALUES (3, '978-9561113066', 'Desolación', 3, 'Primer trabajo poético de gran envergadura de la autora.', '1922-01-01', 12.00, 3);
INSERT INTO libros (id_libro, isbn, titulo, id_autor, descripcion, fecha_publicacion, precio, id_categoria) VALUES (4, '978-8420658428', 'Veinte poemas de amor y una canción desesperada', 4, 'Libro de poemas de temática romántica y existencial.', '1924-06-01', 10.50, 3);
INSERT INTO libros (id_libro, isbn, titulo, id_autor, descripcion, fecha_publicacion, precio, id_categoria) VALUES (5, '978-9562391098', 'Poemas y antipoemas', 5, 'Obra fundacional de la antipoesía hispanoamericana.', '1954-01-01', 15.00, 4);
INSERT INTO libros (id_libro, isbn, titulo, id_autor, descripcion, fecha_publicacion, precio, id_categoria) VALUES (6, '978-8432219504', 'Tengo miedo torero', 6, 'Historia de amor en el contexto del atentado a Pinochet.', '2001-01-01', 16.50, 5);
INSERT INTO libros (id_libro, isbn, titulo, id_autor, descripcion, fecha_publicacion, precio, id_categoria) VALUES (7, '978-9561211366', 'Papelucho', 7, 'Diario de vida de un niño chileno de ocho años.', '1947-01-01', 8.50, 6);
INSERT INTO libros (id_libro, isbn, titulo, id_autor, descripcion, fecha_publicacion, precio, id_categoria) VALUES (8, '978-9562472629', 'Mala onda', 8, 'Una semana en la vida de un adolescente santiaguino en 1980.', '1991-01-01', 14.00, 7);
INSERT INTO libros (id_libro, isbn, titulo, id_autor, descripcion, fecha_publicacion, precio, id_categoria) VALUES (9, '978-8433971364', 'Bonsái', 9, 'Breve novela sobre el amor, la literatura y el crecimiento.', '2006-01-01', 11.90, 8);
INSERT INTO libros (id_libro, isbn, titulo, id_autor, descripcion, fecha_publicacion, precio, id_categoria) VALUES (10, '978-8420427840', 'La Reina Isabel cantaba rancheras', 10, 'Vida en una oficina salitrera desde la perspectiva de las prostitutas.', '1994-01-01', 13.50, 9);

-- --- Inserción de Clientes ---

-- Registros de 10 clientes con formato chileno
INSERT INTO clientes (id_cliente, rut, nombre, apellido, email, telefono) VALUES (1, '12345678-9', 'Matías', 'Pérez', 'matias.perez@email.cl', '+56912345678');
INSERT INTO clientes (id_cliente, rut, nombre, apellido, email, telefono) VALUES (2, '18765432-1', 'Catalina', 'Mora', 'catalina.mora@email.cl', '+56987654321');
INSERT INTO clientes (id_cliente, rut, nombre, apellido, email, telefono) VALUES (3, '15555666-K', 'Joaquín', 'Tapia', 'joaquin.tapia@email.cl', '+56955556666');
INSERT INTO clientes (id_cliente, rut, nombre, apellido, email, telefono) VALUES (4, '11222333-4', 'Fernanda', 'Ríos', 'fernanda.rios@email.cl', '+56911223344');
INSERT INTO clientes (id_cliente, rut, nombre, apellido, email, telefono) VALUES (5, '19888777-5', 'Sebastián', 'Araya', 'sebastian.araya@email.cl', '+56988887777');
INSERT INTO clientes (id_cliente, rut, nombre, apellido, email, telefono) VALUES (6, '16444555-6', 'Ignacia', 'Gómez', 'ignacia.gomez@email.cl', '+56944445555');
INSERT INTO clientes (id_cliente, rut, nombre, apellido, email, telefono) VALUES (7, '17333222-7', 'Tomás', 'Valdés', 'tomas.valdes@email.cl', '+56933332222');
INSERT INTO clientes (id_cliente, rut, nombre, apellido, email, telefono) VALUES (8, '13999888-8', 'Constanza', 'Muñoz', 'constanza.munoz@email.cl', '+56999998888');
INSERT INTO clientes (id_cliente, rut, nombre, apellido, email, telefono) VALUES (9, '20111222-9', 'Felipe', 'Carrasco', 'felipe.carrasco@email.cl', '+56911112222');
INSERT INTO clientes (id_cliente, rut, nombre, apellido, email, telefono) VALUES (10, '14666777-0', 'Isidora', 'Salas', 'isidora.salas@email.cl', '+56966667777');

-- --- Inserción de Pedidos ---

-- Registros de 10 pedidos asociados a clientes y libros
INSERT INTO pedidos (id_pedido, id_cliente, id_libro, fecha_pedido, total, estado, url_descarga) VALUES (1, 1, 10, '2024-05-15', 13.50, 'PENDIENTE', NULL);
INSERT INTO pedidos (id_pedido, id_cliente, id_libro, fecha_pedido, total, estado, url_descarga) VALUES (2, 2, 3, '2024-05-17', 12.00, 'PENDIENTE', NULL);
INSERT INTO pedidos (id_pedido, id_cliente, id_libro, fecha_pedido, total, estado, url_descarga) VALUES (3, 3, 7, '2024-05-19', 8.50, 'PENDIENTE', NULL);
INSERT INTO pedidos (id_pedido, id_cliente, id_libro, fecha_pedido, total, estado, url_descarga) VALUES (4, 4, 9, '2024-05-10', 11.90, 'PAGADO', 'www.booksyspa.cl/descarga/a1b2c3d4-e5f6');
INSERT INTO pedidos (id_pedido, id_cliente, id_libro, fecha_pedido, total, estado, url_descarga) VALUES (5, 5, 4, '2024-05-18', 10.50, 'PENDIENTE', NULL);
INSERT INTO pedidos (id_pedido, id_cliente, id_libro, fecha_pedido, total, estado, url_descarga) VALUES (6, 6, 2, '2024-05-16', 18.90, 'PENDIENTE', NULL);
INSERT INTO pedidos (id_pedido, id_cliente, id_libro, fecha_pedido, total, estado, url_descarga) VALUES (7, 7, 5, '2024-05-14', 15.00, 'PENDIENTE', NULL);
INSERT INTO pedidos (id_pedido, id_cliente, id_libro, fecha_pedido, total, estado, url_descarga) VALUES (8, 8, 6, '2024-05-13', 16.50, 'PAGADO', 'www.booksyspa.cl/descarga/f6e5-d4c3b2a1');
INSERT INTO pedidos (id_pedido, id_cliente, id_libro, fecha_pedido, total, estado, url_descarga) VALUES (9, 9, 8, '2024-05-12', 14.00, 'PENDIENTE', NULL);
INSERT INTO pedidos (id_pedido, id_cliente, id_libro, fecha_pedido, total, estado, url_descarga) VALUES (10, 10, 1, '2024-05-05', 25.50, 'DESCARGADO', 'www.booksyspa.cl/descarga/9876-5432-10ab');