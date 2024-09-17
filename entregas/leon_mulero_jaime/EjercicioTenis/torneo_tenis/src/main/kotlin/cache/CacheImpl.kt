package org.example.cache

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.cache.errors.CacheError
import org.example.tenistas.models.Tenista
import org.lighthousegames.logging.logging

private val logger = logging()

open class CacheImpl(
    private val size: Int
) : Cache<Int, Tenista> {
    private val cache = mutableMapOf<Int, Tenista>()

    override fun get(key: Int): Result<Tenista, CacheError> {
        logger.debug { "Obteniendo valor de la cache" }
        return if (cache.containsKey(key)) {
            logger.debug { "Obtenido valor de la cache con éxito" }
            Ok(cache.getValue(key))
        } else {
            logger.info { "No exite el valor en la cache" }
            Err(CacheError("No existe el valor en la cache"))
        }
    }

    override fun put(key: Int, value: Tenista): Result<Tenista, Nothing> {
        logger.debug { "Guardando valor en la cache" }
        if (cache.size >= size && !cache.containsKey(key)) {
            logger.debug { "Eliminando valor de la cache" }
            cache.remove(cache.keys.first())
        }
        cache[key] = value
        logger.debug { "Guardado valor en la cache con éxito" }
        return Ok(value)
    }

    override fun remove(key: Int): Result<Tenista, CacheError> {
        logger.debug { "Eliminando valor de la cache" }
        return if (cache.containsKey(key)) {
            logger.debug { "Eliminado valor de la cache con éxito" }
            Ok(cache.remove(key)!!)
        } else {
            logger.info { "No exite el valor en la cache" }
            Err(CacheError("No existe el valor en la cache"))
        }
    }

    override fun clear(): Result<Unit, Nothing> {
        logger.debug { "Limpiando cache" }
        cache.clear()
        logger.debug { "Cache limpiada con éxito" }
        return Ok(Unit)
    }
}