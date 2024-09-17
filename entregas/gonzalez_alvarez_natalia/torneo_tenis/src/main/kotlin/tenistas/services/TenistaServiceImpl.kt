package tenistas.services

import cache.CacheImpl
import com.github.michaelbull.result.*
import org.lighthousegames.logging.logging
import tenistas.errors.TenistaError
import tenistas.models.Tenista
import tenistas.repositories.TenistaRepository
import tenistas.validators.TenistaValidator

private val logger = logging()

/**
 * Implementación del servicio para manejar operaciones relacionadas con los tenistas.
 *
 * @property repository Repositorio para acceder y modificar la base de datos de tenistas.
 * @property cache Caché para almacenar y recuperar tenistas.
 * @property validator Validador para verificar la validez de los tenistas.
 * @author Natalia González
 * @version 1.0
 */
class TenistaServiceImpl(
    private val repository: TenistaRepository,
    private val cache: CacheImpl,
    private val validator: TenistaValidator
) : TenistaService {

    /**
     * Obtiene todos los tenistas.
     *
     * @return Un [Result] que contiene una lista de todos los tenistas si la operación es exitosa,
     *         o un [TenistaError] en caso de error.
     */
    override fun getAll(): Result<List<Tenista>, TenistaError> {
        logger.debug { "Obteniendo todos los tenistas" }
        val tenistas : MutableList<Tenista> = mutableListOf()
        repository.findAll().forEach { tenistas.add(it) }

        return Ok(tenistas)
    }

    /**
     * Obtiene un tenista específico por su identificador.
     *
     * Primero busca en la caché, si no se encuentra, busca en el repositorio y luego guarda en la caché.
     *
     * @param id Identificador del tenista a obtener.
     * @return Un [Result] que contiene el tenista con el identificador proporcionado si la operación es exitosa,
     *         o un [TenistaError] si el tenista no se encuentra o si ocurre un error.
     */
    override fun getTenistaById(id: Long): Result<Tenista, TenistaError> {
        logger.debug { "Obteniendo tenista con id: $id" }
        return cache.get(id).mapBoth(
            success = {
                logger.debug { "Tenista encontrado en caché" }
                Ok(it)
            },
            failure = {
                logger.debug { "Tenista no encontrado en caché, buscando en repositorio" }
                repository.findById(id)
                    ?.let { tenista ->
                        Ok(tenista).also {
                            logger.debug { "Guardando tenista en caché" }
                            cache.put(tenista.id.toLong(), tenista)
                        }
                    }
                    ?: Err(TenistaError.TenistaNotFoundError("No se ha encontrado el tenista con id: $id"))
            }
        )
    }

    /**
     * Guarda un nuevo tenista.
     *
     * Valida el tenista antes de guardarlo en el repositorio y luego en la caché.
     *
     * @param tenista El tenista a guardar.
     * @return Un [Result] que contiene el tenista guardado si la operación es exitosa,
     *         o un [TenistaError] en caso de error.
     */
    override fun saveTenista(tenista: Tenista): Result<Tenista, TenistaError> {
        logger.debug { "Guardando tenista: $tenista" }
        return validator.validate(tenista).andThen {
            Ok(repository.save(it))
        }.andThen { savedTenista ->
            logger.debug { "Guardando tenista en caché" }
            cache.put(savedTenista.id.toLong(), savedTenista)
        }
    }

    /**
     * Actualiza un tenista existente.
     *
     * Valida el tenista antes de actualizarlo en el repositorio y luego en la caché.
     *
     * @param id Identificador del tenista a actualizar.
     * @param tenista El tenista con la nueva información.
     * @return Un [Result] que contiene el tenista actualizado si la operación es exitosa,
     *         o un [TenistaError] si el tenista no se encuentra o no se actualiza.
     */
    override fun updateTenista(id: Long, tenista: Tenista): Result<Tenista, TenistaError> {
        logger.debug { "Actualizando tenista con id: $id" }
        return validator.validate(tenista).andThen { validTenista ->
            repository.update(id, validTenista)
                ?.let { Ok(it) }
                ?: Err(TenistaError.TenistaNotUpdatedError("No se ha actualizado el tenista con id: $id"))
        }.andThen { updatedTenista ->
            logger.debug { "Actualizando tenista en caché" }
            cache.put(id, updatedTenista)
        }
    }

    /**
     * Elimina todos los tenistas.
     *
     * Borra todos los tenistas del repositorio y limpia la caché.
     *
     * @return Un [Result] que indica el éxito de la operación con [Unit] si la operación es exitosa,
     *         o un [TenistaError] en caso de error.
     */
    override fun deteleAll(): Result<Unit, TenistaError> {
        logger.debug { "Borrando todos los tenistas" }
        repository.deleteAllTenistas().also {
            logger.debug { "Eliminando tenistas de cache" }
            cache.clear()
            return Ok(it)
        }
    }

    /**
     * Elimina un tenista específico por su identificador.
     *
     * Elimina el tenista del repositorio y lo elimina de la caché.
     *
     * @param id Identificador del tenista a eliminar.
     * @return Un [Result] que contiene el tenista eliminado si la operación es exitosa,
     *         o un [TenistaError] si el tenista no se encuentra o no se elimina.
     */
    override fun deleteTenista(id: Long): Result<Tenista, TenistaError> {
        logger.debug { "Borrando tenista con id: $id" }
        return repository.delete(id)
            ?.let { deletedTenista ->
                logger.debug { "Eliminando tenista de caché" }
                cache.remove(id)
                Ok(deletedTenista)
            }
            ?: Err(TenistaError.TenistaNotDeletedError("No se ha eliminado el tenista con id: $id"))
    }
}