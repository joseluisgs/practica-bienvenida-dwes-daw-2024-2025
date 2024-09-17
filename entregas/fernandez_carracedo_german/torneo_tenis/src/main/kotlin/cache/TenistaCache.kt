package org.example.cache

import org.example.models.Tenista

class TenistaCache(size: Int=5) : CacheImpl<Long, Tenista>(size)