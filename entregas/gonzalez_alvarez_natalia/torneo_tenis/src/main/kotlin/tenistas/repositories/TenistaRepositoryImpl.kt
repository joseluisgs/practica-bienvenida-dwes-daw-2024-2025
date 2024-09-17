package tenistas.repositories

import database.SqlDelightManager
import org.lighthousegames.logging.logging
import tenistas.mappers.toTenista
import tenistas.models.Tenista
import java.time.LocalDate

private val logger = logging()

/**
 * Implementación de [TenistaRepository] que utiliza [SqlDelightManager] para la gestión de tenistas en la base de datos.
 *
 * @property dbManager El gestor de base de datos que proporciona acceso a las consultas SQLDelight.
 * @author Natalia González
 * @version 1.0
 */
class TenistaRepositoryImpl(
    private val dbManager: SqlDelightManager
) : TenistaRepository {

    private val db = dbManager.databaseQueries

    /**
     * Busca todos los tenistas almacenados en la base de datos.
     *
     * @return Lista de todos los tenistas.
     */
    override fun findAll(): List<Tenista> {
        logger.debug { "Buscando todos los tenistas" }
        return db.selectAllTenistas().executeAsList().map { it.toTenista() }
    }

    /**
     * Busca un tenista específico por su identificador.
     *
     * @param id Identificador del tenista a buscar.
     * @return El tenista con el identificador proporcionado, o `null` si no se encuentra.
     */
    override fun findById(id: Long): Tenista? {
        logger.debug { "Buscando tenista con id: $id" }
        return db.selectTenistaById(id).executeAsOneOrNull()?.toTenista()
    }

    /**
     * Guarda un nuevo tenista en la base de datos.
     *
     * @param tenista El tenista a guardar.
     * @return El tenista guardado.
     */
    override fun save(tenista: Tenista): Tenista {
        logger.debug { "Guardando tenista: $tenista" }
        val timeStamp = LocalDate.now().toString()
        db.transaction {
            db.insertTenista(
                id = tenista.id.toLong(),
                nombre = tenista.nombre,
                pais = tenista.pais,
                altura = tenista.altura.toLong(),
                peso = tenista.peso.toLong(),
                puntos = tenista.puntos.toLong(),
                mano = tenista.mano.toString(),
                fecha_nacimiento = tenista.fecha_nacimiento.toString(),
                created_at = timeStamp,
                updated_at = timeStamp
            )
        }
        return db.selectTenistaById(tenista.id.toLong()).executeAsOne().toTenista()
    }

    /**
     * Actualiza la información de un tenista existente.
     *
     * @param id Identificador del tenista a actualizar.
     * @param tenista El tenista con la nueva información.
     * @return El tenista actualizado, o `null` si el tenista con el identificador proporcionado no existe.
     */
    override fun update(id: Long, tenista: Tenista): Tenista? {
        logger.debug { "Actualizando tenista con id: $id" }
        this.findById(id) ?: return null
        val timeStamp = LocalDate.now()

        db.updateTenista(
            nombre = tenista.nombre,
            pais = tenista.pais,
            altura = tenista.altura.toLong(),
            peso = tenista.peso.toLong(),
            puntos = tenista.puntos.toLong(),
            mano = tenista.mano.toString(),
            fecha_nacimiento = tenista.fecha_nacimiento.toString(),
            updated_at = timeStamp.toString(),
            id = tenista.id.toLong()
        )

        return tenista
    }

    /**
     * Elimina todos los tenistas almacenados en la base de datos.
     */
    override fun deleteAllTenistas() {
        logger.debug { "Eliminando todos los tenistas" }
        db.deleteAllTenistas()
    }

    /**
     * Elimina un tenista específico por su identificador.
     *
     * @param id Identificador del tenista a eliminar.
     * @return El tenista eliminado, o `null` si el tenista con el identificador proporcionado no existe.
     */
    override fun delete(id: Long): Tenista? {
        logger.debug { "Borrando tenista con id: $id" }
        db.deleteTenista(id)
        return this.findById(id)
    }
}