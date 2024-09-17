package org.example.tenista.services

import com.github.michaelbull.result.Result
import org.example.persona.errors.TenistaError
import org.example.persona.models.Tenista
import java.io.File

interface TenistaService {
    fun getAll(): Result<List<Tenista>, TenistaError>
    fun getById(id: Int): Result<Tenista, TenistaError>
    fun save(tenista: Tenista): Result<Tenista, TenistaError>
    fun update(id: Int,tenista: Tenista): Result<Tenista, TenistaError>
    fun deleteById(id: Int): Result<Tenista, TenistaError>
    fun loadJson(file: File): Result<List<Tenista>, TenistaError>
    fun storeJson(file: File, listTenista: List<Tenista>) : Result<Unit, TenistaError>
    fun loadCsv(file: File): Result<List<Tenista>, TenistaError>
    fun storeCsv(file: File, listTenista: List<Tenista>) : Result<Unit, TenistaError>
    fun obtenerTenistaConMasRango():Tenista
}