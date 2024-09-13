package tenistas.servicies

import com.github.michaelbull.result.Result
import tenistas.errors.TenistasError
import tenistas.models.Tenista
import java.io.File

interface TenistasService {
    fun getAll(): Result<List<Tenista>, TenistasError>
    fun getById(id: Long): Result<Tenista, TenistasError>
    fun create(tenista: Tenista): Result<Tenista, TenistasError>
    fun update(id: Long, tenista: Tenista): Result<Tenista, TenistasError>
    fun deleteById(id: Long): Result<Tenista, TenistasError>
    fun import(file: File): Result<List<Tenista>, TenistasError>
    fun export(file: File, list: List<Tenista>): Result<Long, TenistasError>

}