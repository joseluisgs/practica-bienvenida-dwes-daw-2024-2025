package org.example.tenist.repository

import database.SqlDelightManager
import org.example.tenist.mappers.toTenist
import org.example.tenist.models.Tenist
import org.koin.core.annotation.Singleton
import org.lighthousegames.logging.logging
import java.time.LocalDateTime

private val logger = logging()

@Singleton
class TenistRepositoryImpl(
    private val databaseManager: SqlDelightManager
) : TenistRepository {

    private val db = databaseManager.databaseQueries

    /**
     * Inserta el tenista en la base de datos
     * @param tenist es el tenista que se quiere guardar en la base de datos
     * @return un objeto Tenista si ha podido meterlo en la base de datos o un null sino
     */
    override fun create(tenist: Tenist): Tenist? {
        logger.debug { "Intentando añadir eltenista con id: ${tenist.name}" }
        if (db.tenistExists(tenist.id.toLong()).executeAsOne()) {
            return null
        }
        db.insertTenist(
            id = tenist.id.toLong(),
            name = tenist.name,
            birthDate = tenist.birthDate.toString(),
            country = tenist.country,
            height = tenist.height,
            weight = tenist.weight.toLong(),
            points = tenist.points.toLong(),
            updatedAt = tenist.updatedAt.toString(),
            createdAt = tenist.createdAt.toString(),
            age = tenist.age.toLong(),
            dominantHand = tenist.dominantHand!!.name,
        )
        return tenist
    }

    /**
     * Elimina el tenista de la base de datos
     * @param id es el id del tenista que se quiere eliminar
     * @return un objeto Tenista si ha podido eliminarlo de la base de datos o un null sino
     */
    override fun delete(id: Int, logical : Boolean): Tenist? {
        logger.debug { "Intentando eliminar el tenista con id: $id" }
        if (db.tenistExists(id.toLong()).executeAsOne()) {
            if (logical) db.deleteTenistLogically(id.toLong()) // Si se quiere borrar logicamente
            else db.deleteTenistPhysically(id.toLong()) // Si se quiere borrar fisicamente de la base de datos
            return get(id)
        }
        return null
    }

    /**
     * Actualiza el tenista en la base de datos
     * @param tenist es el tenista que se quiere actualizar en la base de datos
     * @return un objeto Tenista si ha podido actualizarlo en la base de datos o un null sino
     */
    override fun update(tenist: Tenist): Tenist? {
        logger.debug { "Intentando actualizar el tenista con id: ${tenist.id}" }
        if (db.tenistExists(tenist.id.toLong()).executeAsOne()) {
            db.updateTenist(
                name = tenist.name,
                birthDate = tenist.birthDate.toString(),
                country = tenist.country,
                height = tenist.height,
                weight = tenist.weight.toLong(),
                points = tenist.points.toLong(),
                updatedAt = tenist.updatedAt.toString() ,
                age = tenist.age.toLong(),
                dominantHand = tenist.dominantHand!!.name,
            )
            return tenist.copy(updatedAt = LocalDateTime.now())
        }
        return null
    }

    /**
     * Obtiene un tenista de la base de datos
     * @param id es el id del tenista que se quiere obtener
     * @return un objeto Tenista si ha podido obtenerlo de la base de datos o un null sino
     */
    override fun get(id: Int): Tenist? {
        logger.debug { "Buscando el tenista con id: $id" }
        if (db.tenistExists(id.toLong()).executeAsOne()){
            return db.findTenistById(id.toLong()).executeAsOne().toTenist()
        }
        return null
    }

    /**
     * Obtiene todos los tenistas de la base de datos
     * @return una lista de objetos Tenist si hay alguno y una lista vacia sino
     */
    override fun getAll(): List<Tenist> {
        logger.debug { "Buscando todos los tenistas en la base de datos" }
        if (db.countAllTenists().executeAsOne() > 0){
            return db.getAllTenists().executeAsList().map {
                it.toTenist()
            }
        }
        return emptyList()
    }
}