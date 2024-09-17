package org.example.repositories.tenista

import models.Tenista
import org.example.repositories.crud.CrudRepository

interface TenistaRepository : CrudRepository<Tenista, Int> {
    fun findByCountry(pais: String): List<Tenista>
    fun findByRanking(): List<Tenista>
}