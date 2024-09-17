package org.example.tenistas.services

import org.example.tenistas.models.Tenista
import com.github.michaelbull.result.Result
import org.example.tenistas.errors.TenistaError

interface TenistaService {
    fun getAll(): Result<List<Tenista>, TenistaError>
    fun getTenistaById(id: Int): Result<Tenista, TenistaError>
    fun getTenistaByCountry(item: String): Result<List<Tenista>, TenistaError>
    fun getTenistaByRanking(item: Int): Result<List<Tenista>, TenistaError>
    fun saveTenista(item: Tenista): Result<Tenista, TenistaError>
    fun updateTenista(id: Int, item: Tenista): Result<Tenista, TenistaError>
    fun deleteTenista(id: Int): Result<Tenista, TenistaError>
    fun deleteAllTenistas(): Result<Unit, TenistaError>
}