package cache

import cache.errors.CacheError
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.lighthousegames.logging.logging
import tenistas.models.Tenista

private val logger = logging()

/**
 * Implementación de la interfaz [Cache] para almacenar objetos de tipo [Tenista].
 *
 * @property size Tamaño máximo de la caché.
 * @author Natalia González
 * @version 1.0
 */
class CacheImpl(
    private val size: Int
) : Cache<Long, Tenista> {

    // Mapa que almacena los elementos de la caché con claves de tipo Long y valores de tipo Tenista.
    private val cache = mutableMapOf<Long, Tenista>()

    /**
     * Obtiene un valor de la caché según la clave proporcionada.
     *
     * @param key Clave del elemento a recuperar.
     * @return Un [Result] que contiene el valor [Tenista] si la clave existe en la caché,
     *         o un [CacheError] si no se encuentra.
     */
    override fun get(key: Long): Result<Tenista, CacheError> {
        logger.debug { "Obteniendo valor de la caché" }
        return if (cache.containsKey(key)) {
            Ok(cache.getValue(key))
        } else {
            Err(CacheError("No existe el valor en la caché"))
        }
    }

    /**
     * Almacena un valor en la caché asociado a la clave proporcionada.
     *
     * @param key Clave con la que se identificará el valor almacenado.
     * @param value Valor de tipo [Tenista] a almacenar en la caché.
     * @return Un [Result] que contiene el valor almacenado si la operación es exitosa.
     *
     * Si la caché está llena y la clave no existe, elimina el primer elemento insertado.
     */
    override fun put(key: Long, value: Tenista): Result<Tenista, Nothing> {
        logger.debug { "Guardando valor en la caché" }
        if (cache.size >= size && !cache.containsKey(key)) {
            logger.debug { "Eliminando valor de la caché debido a capacidad máxima" }
            cache.remove(cache.keys.first())
        }
        cache[key] = value
        return Ok(value)
    }

    /**
     * Elimina un valor de la caché según la clave proporcionada.
     *
     * @param key Clave del elemento a eliminar.
     * @return Un [Result] que contiene el valor eliminado si la operación es exitosa,
     *         o un [CacheError] si la clave no se encuentra en la caché.
     */
    override fun remove(key: Long): Result<Tenista, CacheError> {
        logger.debug { "Eliminando valor de la caché" }
        return if (cache.containsKey(key)) {
            Ok(cache.remove(key)!!)
        } else {
            Err(CacheError("No existe el valor en la caché"))
        }
    }

    /**
     * Limpia toda la caché, eliminando todos los elementos almacenados.
     *
     * @return Un [Result] con un valor `Unit` si la operación es exitosa.
     */
    override fun clear(): Result<Unit, Nothing> {
        logger.debug { "Limpiando caché" }
        cache.clear()
        return Ok(Unit)
    }
}