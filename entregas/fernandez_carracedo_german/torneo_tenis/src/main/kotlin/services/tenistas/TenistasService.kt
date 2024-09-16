package org.example.services.tenistas

import com.github.michaelbull.result.Result
import org.example.errors.tenistas.TenistaError
import org.example.models.Tenista

interface TenistasService {

    fun getAll(): Result<List<Tenista>, TenistaError>
    fun getById(id: Long): Result<Tenista, TenistaError>
    fun save(tenista: Tenista): Result<Tenista, TenistaError>
    fun update(id: Long, tenista: Tenista): Result<Tenista, TenistaError>
    fun delete(id: Long): Result<Tenista, TenistaError>

    fun deleteAllTenistas(): Result<Unit, TenistaError>
    fun getTenistaByRanking(ranking:Long): Result<Tenista, TenistaError>
    fun getTenistasByPais(pais: String): Result<List<Tenista>, TenistaError>
    fun loadTenistasFromCsv(fileName: String):List<Tenista>

}