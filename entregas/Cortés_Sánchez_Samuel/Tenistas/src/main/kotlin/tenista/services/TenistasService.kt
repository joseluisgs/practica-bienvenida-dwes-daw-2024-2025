package tenista.services

import com.github.michaelbull.result.Result
import tenista.errors.TenistaError
import tenista.models.Tenista

interface TenistasService {
    fun getAll(): Result<List<Tenista>, TenistaError>
    fun getById(id: Int): Result<Tenista, TenistaError>
    fun save(tenista: Tenista): Result<Tenista, TenistaError>
    fun update(id:Int, tenista: Tenista):Result<Tenista, TenistaError>
    fun deleteById(id: Int): Result<Tenista, TenistaError>
}