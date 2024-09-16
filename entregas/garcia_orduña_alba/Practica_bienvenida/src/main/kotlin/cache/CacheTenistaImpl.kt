package cache

import cache.errors.CacheError
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

import org.example.persona.models.Tenista
import org.lighthousegames.logging.logging


private val logger = logging()

class CacheTenistaImpl(
    private val cacheSize : Int
) : Cache<Long,Tenista> {

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