CREATE TABLE estudio_biblico (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    nombre_persona VARCHAR(100) NOT NULL,
    telefono_persona VARCHAR(20),
    direccion_persona VARCHAR(150) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    leccion INT,
    id_usuario_asignado VARCHAR(36),
    persona_que_reporta VARCHAR(100),
    id_grupo VARCHAR(36)
);