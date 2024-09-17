package tenistas.repositories

import tenistas.models.Tenista

interface TenistasRepository {
    fun getAllTenistas(): List<Tenista>
    fun getTenistaById(id: Long): Tenista?
    fun getTenistaByName(nombre: String): Tenista?
    fun saveTenista(tenista: Tenista): Tenista
    fun updateTenista(tenista: Tenista): Tenista?
    fun deleteById(id: Long): Tenista?
}