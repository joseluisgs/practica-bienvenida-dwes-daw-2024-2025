DROP TABLE IF EXISTS TENISTAS;
CREATE TABLE TENISTAS
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    nombre           VARCHAR(100),
    pais             VARCHAR(100),
    altura           INT,
    peso             DOUBLE,
    puntos           INT,
    mano             VARCHAR(10),
    fecha_nacimiento DATE,
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
