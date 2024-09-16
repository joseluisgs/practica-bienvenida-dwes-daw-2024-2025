package org.example.repositories.tenista

import org.example.models.Tenista
import org.example.repositories.crud.CrudRepository

interface TenistaRepository: CrudRepository <Tenista,Long> {
    // selección por país
    fun findByPais(pais:String): List<Tenista>
    // selección por ranking
    fun findByRanking(ranking:Long): Tenista?
    fun deleteAll()
}