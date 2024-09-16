package tenistas.repositories

import database.SqlDeLightManager
import org.lighthousegames.logging.logging
import tenistas.mappers.toTenista
import tenistas.models.Tenista
private val logger = logging()
class TenistasRepositoryImpl(
    private val dbManager: SqlDeLightManager
):TenistaRepository {
    private val db = dbManager.databaseQueries
    override fun findAll(): List<Tenista> {
        logger.debug { "Buscando todos los tenistas" }
        return db.seleccionarTodosTenistas().executeAsList().map { it.toTenista() }
    }

    override fun findById(id: Long): Tenista? {
        logger.debug { "Buscando tenista por id: $id" }
        return db.seleccionarTenistaPorId(id).executeAsOneOrNull()?.toTenista()
    }

    override fun save(tenista: Tenista): Tenista {
        logger.debug { "Guardando tenista: $tenista" }

        db.transaction {
            db.guardarTenista(
                id = tenista.id,
                nombre = tenista.nombre,
                pais = tenista.fechaNacimiento.toString(),
                altura = tenista.altura.toLong(),
                peso = tenista.peso.toLong(),
                puntos = tenista.puntos.toLong(),
                mano = tenista.mano.toString(),
                fecha_nacimiento = tenista.fechaNacimiento.toString(),
                created_at = tenista.created_at,
                updated_at = tenista.updated_at
            )
        }

        return tenista
    }

    override fun update(id: Long, tenista: Tenista): Tenista? {
        logger.debug { "Actualizando tenista por id: $id" }
        this.findById(id) ?: return null
        db.updateTenista(
            id = tenista.id,
            nombre = tenista.nombre,
            pais = tenista.fechaNacimiento.toString(),
            altura = tenista.altura.toLong(),
            peso = tenista.peso.toLong(),
            puntos = tenista.puntos.toLong(),
            mano = tenista.mano.toString(),
            fecha_nacimiento = tenista.fechaNacimiento.toString(),
            created_at = tenista.created_at,
            updated_at = Tenista.generarFechaActual()
        )
        return tenista
    }

    override fun delete(id: Long): Tenista? {
        logger.debug { "Borrando recluta por id: $id" }
        val result = this.findById(id) ?: return null

        db.eliminarTenistaPorId(
            id = result.id,
        )
        return result
    }
}