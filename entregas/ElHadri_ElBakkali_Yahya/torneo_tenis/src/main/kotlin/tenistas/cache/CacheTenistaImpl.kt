package tenistas.cache

import cache.CacheImpl
import tenistas.models.Tenista

class CacheTenistaImpl(size:Int): CacheImpl<Long, Tenista>(size)