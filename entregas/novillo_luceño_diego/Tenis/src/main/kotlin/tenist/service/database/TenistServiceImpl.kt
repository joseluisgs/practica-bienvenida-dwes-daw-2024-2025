package org.example.tenist.service.database

import org.example.cache.Cache
import org.example.tenist.models.Tenist
import org.koin.core.annotation.Singleton

@Singleton
class TenistServiceImpl (
    private val cache: Cache<Tenist,Int>
){
}