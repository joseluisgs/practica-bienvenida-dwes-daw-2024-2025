package org.example.tenist.service.storage.json

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.tenist.dto.TenistDto
import org.example.tenist.errors.TenistError
import org.example.tenist.mappers.toTenist
import org.example.tenist.mappers.toTenistDto
import org.example.tenist.models.Tenist
import org.koin.core.annotation.Singleton
import java.io.File

@Singleton
class TenistStorageJsonImpl : TenistStorageJson {
    /**
     * Una funcion que exporta un fichero de que contenga toda la
     */
    override fun export(file : File, list: List<Tenist>): Result<Unit, TenistError> {
        return try {
            val json = Json {
                prettyPrint = true
            }
            val dtoList = list.map { it.toTenistDto() }
            val jsonText = json.encodeToString<List<TenistDto>>(dtoList)
            file.writeText(jsonText)
            Ok(Unit)
        } catch (e: Exception) {
            return Err(TenistError.ExportError("No se ha podido exportar el fichero JSON: $e"))
        }
    }
}