package tenistas.cache

import tenistas.errors.CacheError
import com.github.michaelbull.result.Result

interface CacheTenistas<K, T> {
    fun get(key: K): Result<T, CacheError>
    fun put(key: K, value: T): Result<T, CacheError>
    fun remove(key: K): Result<T, CacheError>
    fun clear()
}