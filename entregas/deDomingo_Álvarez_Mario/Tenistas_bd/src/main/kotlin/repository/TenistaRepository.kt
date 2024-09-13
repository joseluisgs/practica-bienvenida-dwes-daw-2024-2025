package org.example.tenistas.repository

import org.example.tenistas.models.Tenista

interface TenistaRepository {
    fun findAll(): List<Tenista>
    fun findById(id: Int): Tenista?
    fun save(tenista: Tenista): Tenista
    fun update(tenista: Tenista): Tenista
    fun delete(id: Int): Boolean
}
