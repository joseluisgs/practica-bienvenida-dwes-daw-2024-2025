package org.example.tenistas.storage.storageXML

import org.example.tenistas.errors.TenistaError
import org.example.tenistas.models.Tenista
import java.io.File
import com.github.michaelbull.result.*
import kotlinx.serialization.encodeToString
import nl.adaptivity.xmlutil.serialization.XML
import org.example.tenistas.dto.TenistaDto
import org.example.tenistas.dto.TenistaDtoRank
import org.lighthousegames.logging.logging
import org.example.tenistas.mappers.toListTenistaDto
import org.example.tenistas.mappers.toTenistaDto
import org.example.tenistas.mappers.toTenistaDtoRanking

private val logger = logging()

class StorageXML {

    fun storeXml(file: File, data: List<Tenista>): Result<Long, TenistaError> {
        logger.debug { "Guardando datos en fichero $file" }

        val dataOrdenada = data.sortedByDescending { it.puntos }
        val dataRanked = dataOrdenada.mapIndexed { index, tenista ->
            val tenistaDto = tenista.toTenistaDto()
            tenistaDto.toTenistaDtoRanking(index + 1)
        }.toList()

        return try {
            val xml = XML { indent = 4 }
            val xmlString = xml.encodeToString<List<TenistaDtoRank>>(dataRanked)
            file.writeText(xmlString)
            Ok(data.size.toLong())
        } catch (e: Exception) {
            Err(TenistaError.TenistaStorageError("Error al escribir el XML: ${e.message}"))
        }
    }
}