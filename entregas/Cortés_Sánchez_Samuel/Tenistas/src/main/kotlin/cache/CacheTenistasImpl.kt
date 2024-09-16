package cache

import cache.errors.CacheError
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.lighthousegames.logging.logging
import tenista.models.Tenista

private val logger = logging()

/**
 * @param cacheSize Tamaño máximo permitido para la caché.
 * @author Samuel Cortés Sánchez
 * @version 1.0
 */
class CacheTenistasImpl(
    private val cacheSize : Int
) : Cache<Long, Tenista> {

    private val cache = mutableMapOf<Long,Tenista>()

    override fun get(key: Long): Result<Tenista, CacheError> {
        logger.debug { "Obteniendo valor de la cache" }
        return if (cache.containsKey(key)){
            Ok(cache.getValue(key))
        }else{
            Err(CacheError.CacheErrorValid("No existe el valor en la cache"))
        }
    }

    override fun put(key: Long, value: Tenista): Result<Tenista, Nothing> {
        logger.debug { "Salvando valor en la cache" }
        if (cache.size > cacheSize && !cache.containsKey(key)){
            logger.debug { "Eliminando primer valor de la cache" }
            cache.remove(cache.keys.first())
        }
        cache[key] = value
        return Ok(value)
    }

    override fun remove(key: Long): Result<Tenista, CacheError> {
        logger.debug { "Eliminando valor de la cache" }
        return if (cache.containsKey(key)){
            Ok(cache.remove(key)!!)
        }else{
            Err(CacheError.CacheErrorValid("No existe el valor en la cache"))
        }
    }

}