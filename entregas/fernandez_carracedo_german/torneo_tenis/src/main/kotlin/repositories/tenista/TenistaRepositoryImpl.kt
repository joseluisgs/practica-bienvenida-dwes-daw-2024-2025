package org.example.repositories.tenista

import org.example.models.Tenista
import org.example.mappers.toTenista
import org.example.services.database.SqlDeLightClient
import org.lighthousegames.logging.logging
import java.time.LocalDate
import java.time.LocalDateTime

private val logger = logging()

class TenistaRepositoryImpl(
    private val dbManager: SqlDeLightClient
): TenistaRepository {

    //private val db = SqlDeLightClient.databaseQueries
    private val db = dbManager.databaseQueries

    override fun findByPais(pais: String): List<Tenista> {
        logger.debug { "Buscando tenistas por pais: $pais" }
        return db.selectAllTenistasByPais(pais).executeAsList().map { it.toTenista() }
    }

    override fun findByRanking(ranking: Long): Tenista? {
        logger.debug { "Buscando tenista por ranking: $ranking" }
        return db.selectTenistaByRanking(ranking).executeAsOneOrNull()?.toTenista()
    }

    override fun findAll(): List<Tenista> {
        logger.debug { "Obteniendo todos los tenistas" }
        return db.selectAllTenistas().executeAsList().map { it.toTenista() }
    }

    override fun findById(id: Long): Tenista? {
        logger.debug { "Buscando tenista por id: $id" }
        return db.selectTenistaById(id).executeAsOneOrNull()?.toTenista()
    }

    override fun save(tenista: Tenista): Tenista {
        logger.debug { "Guardando tenista: $tenista" }
        val timeStamp = LocalDate.now().toString()
        db.transaction {
            db.insertTenista(
                nombre = tenista.nombre,
                pais = tenista.pais,
                altura = tenista.altura.toLong(),
                peso = tenista.peso.toLong(),
                puntos = tenista.puntos.toLong(),
                mano = tenista.mano.toString(),
                fechaNacimiento = tenista.fechaNacimiento.toString(),
                created_at = timeStamp,
                updated_at = timeStamp
            )
        }
        return db.selectTenistaEntityLastInserted().executeAsOne().toTenista()
    }

    override fun update(id: Long, tenista: Tenista): Tenista? {
        logger.debug { "Actualizando tenista por id: $id" }
        var result = this.findById(id) ?: return null
        val timeStamp = LocalDate.now()

        result = result.copy(
            nombre = tenista.nombre,
            pais = tenista.pais,
            altura = tenista.altura,
            peso = tenista.peso,
            puntos = tenista.puntos,
            mano = tenista.mano,
            fechaNacimiento = tenista.fechaNacimiento,
            updatedAt = timeStamp,
        )

        db.updateTenista(
            nombre = result.nombre,
            pais = result.pais,
            altura = result.altura.toLong(),
            peso = result.peso.toLong(),
            puntos = result.puntos.toLong(),
            mano = result.mano.toString(),
            fechaNacimiento = result.fechaNacimiento.toString(),
            updated_at = timeStamp.toString(),
            id = id
        )
        return result    }

    override fun delete(id: Long): Tenista? {
        logger.debug { "Borrando tenista por id: $id" }
        val result = this.findById(id) ?: return null

        db.deleteTenista(id)

        return result
    }

    override fun deleteAll() {
        logger.debug { "Borrando todos los tenistas" }
        db.removeAllTenistas()
    }

}