-- Crear tabla reclutadores (contraseñas en texto plano, solo para pruebas)
CREATE TABLE IF NOT EXISTS reclutadores (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  apellido VARCHAR(100),
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(50) NOT NULL DEFAULT 'RECLUTADOR',
  active TINYINT(1) NOT NULL DEFAULT 1,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  last_login TIMESTAMP NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Insert de ejemplo
INSERT INTO reclutadores (nombre, apellido, email, password)
VALUES ('Alejo','Pérez','alejo@example.com','secret123');
