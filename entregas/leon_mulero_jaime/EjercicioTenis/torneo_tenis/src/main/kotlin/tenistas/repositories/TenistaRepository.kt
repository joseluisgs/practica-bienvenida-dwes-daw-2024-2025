package org.example.tenistas.repositories

import org.example.tenistas.models.Tenista

interface TenistaRepository {
    fun findAll(): List<Tenista>
    fun findById(id: Int): Tenista?
    fun findByCountry(item: String): List<Tenista>
    fun findByRanking(item: Int): List<Tenista>
    fun save(item: Tenista): Tenista
    fun update(id: Int, item: Tenista): Tenista?
    fun delete(id: Int): Tenista?
    fun deleteAll()
}