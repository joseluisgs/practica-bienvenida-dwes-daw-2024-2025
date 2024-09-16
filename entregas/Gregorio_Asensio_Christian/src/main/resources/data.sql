CREATE TABLE IF NOT EXISTS tenistas
(
    id              INTEGER PRIMARY KEY,
    nombre          TEXT NOT NULL,
    pais            TEXT,
    altura          INTEGER,
    peso            INTEGER,
    puntos          INTEGER,
    mano            TEXT,
    fechaNacimiento TEXT NOT NULL,
    created_at      TEXT NOT NULL,
    updated_at      TEXT NOT NULL
);