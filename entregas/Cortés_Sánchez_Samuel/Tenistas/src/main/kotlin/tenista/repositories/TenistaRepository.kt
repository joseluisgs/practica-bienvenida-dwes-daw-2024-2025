package tenista.repositories

import tenista.models.Tenista

interface TenistaRepository {
    fun getAll():List<Tenista>
    fun getById(id: Int): Tenista?
    fun save(tenista: Tenista): Tenista
    fun update(id:Int, tenista: Tenista):Tenista?
    fun deleteById(id: Int): Tenista?
}