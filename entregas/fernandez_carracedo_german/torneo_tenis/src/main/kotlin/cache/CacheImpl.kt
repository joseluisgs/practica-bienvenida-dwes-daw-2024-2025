package org.example.cache

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.cache.errors.CacheError
import org.lighthousegames.logging.logging


private val logger = logging()
/**
 * Clase que implementa el funcionamiento de las funciones de la caché
 * @author Germán Fernández
 * @since 1.0.0
 * @see Cache
 * @see CacheError
 * @param size Indica el tamaño máximo que tendrá la caché
 * @property cache Un mapa mutable para el almacenamiento de los datos en la caché
 */
open class CacheImpl<K, T>(
    private val size: Int
) : Cache<K, T> {
    private val cache = mutableMapOf<K, T>()
    /**
     * Implementación genérica de la función de mostrado de datos a la caché
     *  @author Germán Fernández

     * @since 1.0.0
     * @param key Identificador específico del dato a mostrar de la caché
     * @return Result<T, CacheError> Nos devuelve el objeto buscado o un error en caso de no existir el dato en la caché
     */
    override fun get(key: K): Result<T, CacheError> {
        logger.debug { "Obteniendo valor de la cache" }
        return if (cache.containsKey(key)) {
            Ok(cache.getValue(key))
        } else {
            Err(CacheError("No existe el valor en la cache"))
        }
    }
    /**
     * Implementación genérica de la función de introducción de datos a la caché
     *  @author Germán Fernández

     * @since 1.0.0
     * @param key Identificador específico del dato introducido en la caché
     * @param value Dato introducido en la caché
     * @return Result<T, CacheError> Nos devuelve el objeto introducido o un error en caso de no ser posible la introducción del dato en la misma
     */
    override fun put(key: K, value: T): Result<T, Nothing> {
        logger.debug { "Guardando valor en la cache" }
        if (cache.size >= size && !cache.containsKey(key)) {
            logger.debug { "Eliminando valor de la cache" }
            cache.remove(cache.keys.first())
        }
        cache[key] = value
        return Ok(value)
    }
    /**
     * Implementación genérica de la función de eliminación de datos en la caché
     * @author Germán Fernández
     * @since 1.0.0
     * @param key Identificador específico del dato a borrar en la caché
     * @return Result<T, CacheError> Nos devuelve el objeto eliminado o un error en caso de no ser posible la eliminación del dato en la misma
     */
    override fun remove(key: K): Result<T, CacheError> {
        logger.debug { "Eliminando valor de la cache" }
        return if (cache.containsKey(key)) {
            Ok(cache.remove(key)!!)
        } else {
            Err(CacheError("No existe el valor en la cache"))
        }
    }
    /**
     * Implementación genérica de la función de borrado completo de la caché
     * @author Germán Fernández
     * @since 1.0.0
     * @return Result<Unit, Nothing> Nos devuelve un Unit en caso de realizarse el borrado de la caché y un Nothing en caso de no ser posible
     */
    override fun clear(): Result<Unit, Nothing> {
        logger.debug { "Limpiando cache" }
        cache.clear()
        return Ok(Unit)
    }
}