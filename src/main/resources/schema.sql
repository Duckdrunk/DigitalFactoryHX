CREATE TABLE IF NOT EXISTS alumnos (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255),
    apellido VARCHAR(255),
    estado VARCHAR(50),
    edad INT,
    created_date TIMESTAMP,
    updated_date TIMESTAMP
);
