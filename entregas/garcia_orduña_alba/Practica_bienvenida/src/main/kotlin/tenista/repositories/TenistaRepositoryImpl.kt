package org.example.persona.repositories

import database.SqlDelightManager
import org.example.persona.models.Tenista
import org.example.tenista.mappers.toTenista
import org.lighthousegames.logging.logging

private val logger = logging()

class TenistaRepositoryImpl(
    private val dbManager: SqlDelightManager
) : TenistaRepository {

    private val db = dbManager.databaseQueries

    override fun getAll(): List<Tenista> {
        logger.debug { "Obteniendo todos los tenistas" }
        return db.selectAllTenista().executeAsList().map { it.toTenista() }
    }

    override fun getById(id: Int): Tenista? {
        logger.debug { "Obteniendo tenista por ID: $id" }
        return db.selectTenistaById(id.toLong()).executeAsOneOrNull()?.toTenista()
    }

    override fun save(tenista: Tenista): Tenista {
        logger.debug { "Guardando tenista: $tenista" }
        db.insertTenista(
            id = tenista.id.toLong(),
            nombre = tenista.nombre,
            pais = tenista.pais,
            altura = tenista.altura.toLong(),
            peso = tenista.peso.toLong(),
            puntos = tenista.puntos,
            mano = tenista.mano.toString(),
            fecha_nacimiento = tenista.fecha_nacimiento.toString(),
            created_at = tenista.created_at.toString(),
            updated_at = tenista.updated_at.toString()

        )
        return tenista
    }

    override fun update(id: Int, tenista: Tenista): Tenista? {
        logger.debug { "Actualizando tenista con ID: $id" }

        if (getById(id) == null) return null

        db.updateTenista(
            id = id.toLong(),
            nombre = tenista.nombre,
            pais = tenista.pais,
            altura = tenista.altura.toLong(),
            peso = tenista.peso.toLong(),
            puntos = tenista.puntos,
            mano = tenista.mano.toString(),
            fecha_nacimiento = tenista.fecha_nacimiento.toString(),
            created_at = tenista.created_at.toString(),
            updated_at = tenista.updated_at.toString()
        )
        return tenista
    }

    override fun deleteById(id: Int): Tenista? {
        logger.debug { "Eliminando tenista por ID: $id" }

        val tenista = getById(id)
        if (tenista == null) return null

        db.deleteByIdTenista(id.toLong())
        return tenista
    }

    override fun tenistaMasRango(): Tenista {
        logger.debug { "Obteniendo el tenista con más rango" }
        return db.obtenerTenistaConMasRango().executeAsOne().toTenista()
    }
}