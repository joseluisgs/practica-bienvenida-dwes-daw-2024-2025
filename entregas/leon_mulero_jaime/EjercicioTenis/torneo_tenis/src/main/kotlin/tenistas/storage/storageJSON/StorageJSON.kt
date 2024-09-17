package org.example.tenistas.storage.storageJSON

import com.github.michaelbull.result.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.tenistas.dto.TenistaDtoRank
import org.example.tenistas.errors.TenistaError
import org.example.tenistas.mappers.toTenistaDto
import org.example.tenistas.mappers.toTenistaDtoRanking
import org.example.tenistas.models.Tenista
import org.lighthousegames.logging.logging
import java.io.File

private val logger = logging()

class StorageJSON {

    fun storeJSON(file: File, data: List<Tenista>): Result<Long, TenistaError> {
        logger.debug { "Guardando datos en fichero $file" }

        val dataOrdenada = data.sortedByDescending { it.puntos }
        val dataRanked = dataOrdenada.mapIndexed { index, tenista ->
            val tenistaDto = tenista.toTenistaDto()
            tenistaDto.toTenistaDtoRanking(index + 1)
        }.toList()

        return try {
            val json = Json {
                prettyPrint = true
                ignoreUnknownKeys = true
            }

            val jsonString = json.encodeToString<List<TenistaDtoRank>>(dataRanked)
            file.writeText(jsonString)
            logger.debug { data.size.toLong() }
            Ok(data.size.toLong())
        } catch (e: Exception) {
            logger.debug { "Error: ${e.message}" }
            Err(TenistaError.TenistaStorageError("Error al escribir el JSON: ${e.message}"))
        }
    }
}