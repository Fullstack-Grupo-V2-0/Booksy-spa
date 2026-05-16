-- --- Archivo V1__creacion_esquema.sql ---

-- Creación de tabla autores según Autor.java
CREATE TABLE autores (
    id_autor INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT,
    apellido TEXT,
    fecha_nacimiento DATE,
    nacionalidad TEXT,
    premios TEXT
);

-- Creación de tabla categorias según Categoria.java
CREATE TABLE categorias (
    id_categoria INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT,
    descripcion TEXT
);

-- Creación de tabla clientes según Cliente.java
CREATE TABLE clientes (
    id_cliente INTEGER PRIMARY KEY AUTOINCREMENT,
    rut TEXT,
    nombre TEXT,
    apellido TEXT,
    email TEXT,
    telefono TEXT
);

-- Creación de tabla libros según Libro.java
-- Se mapean estrictamente las columnas id_autor e id_categoria definidas en @JoinColumn
CREATE TABLE libros (
    id_libro INTEGER PRIMARY KEY AUTOINCREMENT,
    isbn TEXT,
    titulo TEXT,
    id_autor INTEGER NOT NULL,
    descripcion TEXT,
    fecha_publicacion DATE,
    precio NUMERIC(38,2),
    url_descarga TEXT,
    id_categoria INTEGER NOT NULL,
    FOREIGN KEY (id_autor) REFERENCES autores (id_autor),
    FOREIGN KEY (id_categoria) REFERENCES categorias (id_categoria)
);

-- Creación de tabla pedidos según Pedido.java
-- Se mapea el Enum EstadoPedido como TEXT
CREATE TABLE pedidos (
    id_pedido INTEGER PRIMARY KEY AUTOINCREMENT,
    id_cliente INTEGER NOT NULL,
    id_libro INTEGER NOT NULL,
    fecha_pedido DATE,
    total NUMERIC(38,2),
    estado TEXT,
    url_descarga TEXT,
    FOREIGN KEY (id_cliente) REFERENCES clientes (id_cliente),
    FOREIGN KEY (id_libro) REFERENCES libros (id_libro)
);