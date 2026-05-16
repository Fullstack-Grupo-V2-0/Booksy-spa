-- Creación de tabla usuarios
CREATE TABLE usuarios (
    id_usuario   INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre       TEXT NOT NULL,
    email        TEXT NOT NULL UNIQUE,
    password     TEXT NOT NULL,
    rol          TEXT NOT NULL,
    activo       INTEGER NOT NULL DEFAULT 1
);
