package cache

import cache.errors.CacheError
import com.github.michaelbull.result.Result

/**
 * Interfaz genérica que define las operaciones básicas para un sistema de caché.
 *
 * @param K Tipo de las claves que se usarán para acceder a los valores en la caché.
 * @param T Tipo de los valores que se almacenarán en la caché.
 * @author Natalia González
 * @version 1.0
 */
interface Cache<K, T> {

    /**
     * Recupera un valor de la caché según la clave proporcionada.
     *
     * @param key Clave del elemento a recuperar.
     * @return Un [Result] que contiene el valor recuperado si la clave existe,
     *         o un [CacheError] si ocurre algún error (por ejemplo, si no existe la clave).
     */
    fun get(key: K): Result<T, CacheError>

    /**
     * Almacena un valor en la caché con la clave proporcionada.
     *
     * @param key Clave con la que se identificará el valor almacenado.
     * @param value Valor a almacenar en la caché.
     * @return Un [Result] que contiene el valor almacenado si la operación es exitosa,
     *         o `Nothing` si no hay errores.
     */
    fun put(key: K, value: T): Result<T, Nothing>

    /**
     * Elimina un valor de la caché según la clave proporcionada.
     *
     * @param key Clave del elemento a eliminar.
     * @return Un [Result] que contiene el valor eliminado si la operación es exitosa,
     *         o un [CacheError] si ocurre algún error (por ejemplo, si la clave no existe).
     */
    fun remove(key: K): Result<T, CacheError>

    /**
     * Limpia toda la caché, eliminando todos los elementos almacenados.
     *
     * @return Un [Result] con un valor `Unit` si la operación es exitosa,
     *         o `Nothing` si no hay errores.
     */
    fun clear(): Result<Unit, Nothing>
}