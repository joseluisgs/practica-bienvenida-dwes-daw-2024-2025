CREATE TABLE IF NOT EXISTS tenista
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