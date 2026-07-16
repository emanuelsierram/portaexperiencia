CREATE SCHEMA IF NOT EXISTS mi_antorcha;

CREATE TABLE mi_antorcha.miembros (
  id_miembro INT NOT NULL AUTO_INCREMENT,
  usuario_id VARCHAR(50) NOT NULL,
  nombres VARCHAR(100) NOT NULL,
  apellidos VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL UNIQUE,
  telefono VARCHAR(20) NOT NULL,
  perfil VARCHAR(30) NOT NULL,
  id_grupo_pequeno INT NULL, -- FK lógica a grupos_pequenos (nulo por si es nuevo)
  id_anciano INT NULL,
  fecha_creacion DATETIME NOT NULL,
  fecha_actualizacion DATETIME NOT NULL,
  PRIMARY KEY (id_miembro)
);

CREATE TABLE mi_antorcha.estudio_biblico (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_persona VARCHAR(100) NOT NULL,
    telefono_persona VARCHAR(20),
    direccion_persona VARCHAR(150) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    leccion INT,
    id_usuario_asignado BIGINT,
    persona_que_reporta VARCHAR(100),
    id_grupo BIGINT
);

CREATE TABLE mi_antorcha.historico_lecciones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    contador_semana INT NOT NULL,
    id_estudio_biblico BIGINT NOT NULL,
    fecha_estudio DATETIME NOT NULL,
    id_actividad BIGINT NOT NULL
);