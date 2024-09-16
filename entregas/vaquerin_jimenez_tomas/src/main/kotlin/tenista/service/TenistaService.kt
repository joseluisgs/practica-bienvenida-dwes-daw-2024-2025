package org.example.tenista.service

import org.example.tenista.models.Tenista

interface TenistaService {
    fun getAll(): List<Tenista>
    fun getById(id: Long): Tenista?
    fun create(tenista: Tenista): Tenista
    fun update(id: Long, tenista: Tenista): Tenista?
    fun delete(id: Long): Tenista?

}