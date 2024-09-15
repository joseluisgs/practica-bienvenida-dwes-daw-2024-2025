package org.example.tenistas.repositories

import org.example.database.service.SqlDeLightManager
import org.example.tenistas.mappers.toTenista
import org.example.tenistas.models.Tenista
import org.lighthousegames.logging.logging
import java.time.LocalDateTime

val logger = logging()

class TenistaRepositoryImpl(
    private val dbManager: SqlDeLightManager
): TenistaRepository {

    private val db = dbManager.databaseQueries

    override fun findAll(): List<Tenista> {
        logger.debug { "Buscando todos los tenistas" }
        return db.selectAllTenistas().executeAsList().map { it.toTenista() }
    }

    override fun findById(id: Int): Tenista? {
        logger.debug { "Buscando tenista con id $id" }
        return db.selectTenistaById(id.toLong()).executeAsOneOrNull()?.toTenista()
    }

    override fun findByCountry(item: String): List<Tenista> {
        logger.debug { "Buscando tenistas por pais ${item}" }
        return db.selectAllTenistasByCountry(item).executeAsList().map { it.toTenista() }
    }

    override fun findByRanking(item: Int): List<Tenista> {
        logger.debug { "Buscando tenistas por ranking ${item}" }
        return db.selectAllTenistasByRanking(item.toLong()).executeAsList().map { it.toTenista() }
    }

    override fun save(item: Tenista): Tenista {
        logger.debug { "Guardando tenista ${item}" }
        val timeStamp = LocalDateTime.now()
        var id = 0L
        db.transaction {
            db.saveTenista(
                nombre = item.nombre,
                pais = item.pais,
                altura = item.altura.toLong(),
                peso = item.peso.toLong(),
                puntos = item.puntos.toLong(),
                mano = item.mano.name,
                fecha_nacimiento = item.fechaNacimiento.toString(),
                created_at = timeStamp.toString(),
                updated_at = timeStamp.toString()
            )
            id = db.selectLastSaveId().executeAsOne()
        }
        return db.selectTenistaById(id).executeAsOne().toTenista()
    }

    override fun update(id: Int, item: Tenista): Tenista? {
        logger.debug { "Actualizando tenista con id $id" }
        this.findById(id) ?: return null
        val timeStamp = LocalDateTime.now()
        db.updateTenista(
            nombre = item.nombre,
            pais = item.pais,
            altura = item.altura.toLong(),
            peso = item.peso.toLong(),
            puntos = item.puntos.toLong(),
            mano = item.mano.name,
            fecha_nacimiento = item.fechaNacimiento.toString(),
            updated_at = timeStamp.toString(),

            id = id.toLong(),
        )
        return db.selectTenistaById(id.toLong()).executeAsOne().toTenista()
    }

    override fun delete(id: Int): Tenista? {
        logger.debug { "Borrando tenista con id $id" }
        val tenista = this.findById(id) ?: return null
        db.deleteTenista(id.toLong())
        return tenista
    }

    override fun deleteAll() {
        logger.debug { "Borrando todos los tenistas" }
        return db.deleteAllTenistas()
    }

}