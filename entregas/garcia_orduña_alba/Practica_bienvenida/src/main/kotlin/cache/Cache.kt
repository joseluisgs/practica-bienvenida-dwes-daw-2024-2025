package cache

import cache.errors.CacheError
import com.github.michaelbull.result.Result

interface Cache<KEY,ID> {
    fun get(key: KEY): Result<ID,CacheError>
    fun put(key: KEY, value: ID):Result<ID,Nothing>
    fun remove(key: KEY):Result<ID,CacheError>
}