package tenista.repository

import database.SqlDelightManager
import org.lighthousegames.logging.logging
import tenista.mappers.toTenista
import tenista.models.Tenista
import tenista.repositories.TenistaRepository

private val logger = logging()

class TenistaRepositoryImpl(
    private val dbManager: SqlDelightManager
) : TenistaRepository {

    private val db= dbManager.databaseQueries

    override fun getAll(): List<Tenista> {
        logger.debug { "Obteniendo todos los tenistas" }
        val lista= mutableListOf<Tenista>()
        db.selectAllTenista().executeAsList().map { it.toTenista() }.forEach{lista.add(it)}
        return lista
    }

    override fun getById(id: Int): Tenista? {
        logger.debug { "Obteniendo tenista con id $id" }
        db.selectTenistaByid(id.toLong()).executeAsList().map { it.toTenista() }
        return null
    }

    override fun save(tenista: Tenista): Tenista {
        logger.debug { "Guardando tenista $tenista" }
        db.transaction {
            db.insertTenista(tenista.nombre,tenista.pais,tenista.altura.toLong(),tenista.peso.toLong(),tenista.puntos.toLong(),tenista.mano,tenista.fecha_nacimiento.toString(),tenista.created_at.toString(),tenista.updated_at.toString())
        }
        return tenista
    }

    override fun update(id: Int, tenista: Tenista): Tenista? {
        logger.debug { "Actualizando tenista" }

        val result = getById(id)?: return null
        db.updateTenista(nombre = result.nombre,pais = result.pais,altura = result.altura.toLong(),peso = result.peso.toLong(),puntos = result.puntos.toLong(),mano = result.mano,fecha_nacimiento = result.fecha_nacimiento.toString(),created_at = result.created_at.toString(),updated_at = result.updated_at.toString())

        return tenista
    }

    override fun deleteById(id: Int): Tenista? {
        logger.debug { "Borrando tenista por id $id" }

        val result = getById(id)?: return null

        db.deleteTenistaById(result.id.toLong())
        return result
    }
}