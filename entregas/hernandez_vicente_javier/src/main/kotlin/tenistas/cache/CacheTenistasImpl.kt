package tenistas.cache

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import config.Config.cacheSize
import tenistas.errors.CacheError
import tenistas.mapper.logger
import tenistas.models.Tenista

/**
 * Cache de almacenamiento de tenistas
 * @property size Tamaño de la cache
 * @author Javier Hernández
 * @since 1.0
 */
class CacheTenistasImpl(
    private val size: Int
): CacheTenistas<Long, Tenista> {
    private val cache = mutableMapOf<Long, Tenista>()

    /**
     * Obtiene un valor de la cache
     * @param key Clave del valor a obtener
     * @return Result<Tenista, CacheError> Valor obtenido de la cache o error si no existe el valor en la cache
     * @author Javier Hernández
     * @since 1.0
     */
    override fun get(key: Long): Result<Tenista, CacheError> {
        logger.debug { "Obteniendo valor de la cache" }
        return if (cache.containsKey(key)){
            Ok(cache.getValue(key))
        }else{
            Err(CacheError.CacheErrorValid("No existe el valor en la cache"))
        }
    }

    /**
     * Obtiene el tamaño de la cache
     * @param key Tamaño de la cache
     * @param value Valor a guardar en la cache
     * @return Result<Tenista, CacheError> Valor guardado en la cache o error si no se pudo guardar el valor en la cache
     * @author Javier Hernández
     * @since 1.0
     */
    override fun put(key: Long, value: Tenista): Result<Tenista, CacheError> {
        logger.debug { "Salvando valor en la cache" }
        if (cache.size > cacheSize && !cache.containsKey(key)){
            logger.debug { "Eliminando primer valor de la cache" }
            cache.remove(cache.keys.first())
        }
        cache[key] = value
        return Ok(value)
    }

    /**
     * Elimina un valor de la cache
     * @param key Clave del valor a eliminar
     * @return Result<Tenista, CacheError> Valor eliminado de la cache o error si no existe el valor en la cache
     * @author Javier Hernández
     * @since 1.0
     */

    override fun remove(key: Long): Result<Tenista, CacheError> {
        logger.debug { "Eliminando valor de la cache" }
        return if (cache.containsKey(key)){
            Ok(cache.remove(key)!!)
        }else{
            Err(CacheError.CacheErrorValid("No existe el valor en la cache"))
        }
    }
    /**
     * Limpia la cache
     * @return Unit No retorna nada, solo limpia la cache de valores almacenados en ella
     * @author Javier Hernández
     * @since 1.0
     */

    override fun clear() {
        logger.debug { "Limpiando cache" }
        cache.clear()
    }
}