package org.example.tenistas.storage.storageCSV

import com.github.michaelbull.result.*
import org.example.tenistas.dto.TenistaDto
import org.example.tenistas.errors.TenistaError
import org.example.tenistas.mappers.toTenista
import org.example.tenistas.mappers.toTenistaDto
import org.example.tenistas.mappers.toTenistaDtoRanking
import org.example.tenistas.models.Tenista
import org.example.tenistas.storage.validators.CsvValidator
import org.lighthousegames.logging.logging
import java.io.File
import java.time.LocalDateTime

private val logger = logging()

class StorageCSV {

    private val validator = CsvValidator()

    fun storeCsv(file: File, data: List<Tenista>): Result<Long, TenistaError> {
        logger.debug { "Cargando datos en fichero $file" }
        return try {
            file.writeText("ID,NOMBRE,PAIS,ALTURA,PESO,PUNTOS,MANO,FECHA DE NACIMIENTO,RANKING\n")
            data.sortedByDescending { it.puntos }.mapIndexed { index, tenista ->
                tenista.toTenistaDto().toTenistaDtoRanking(index + 1) }
                .forEach { tenistaDtoRank ->
                    file.appendText("${tenistaDtoRank.id},${tenistaDtoRank.nombre},${tenistaDtoRank.pais},${tenistaDtoRank.altura},${tenistaDtoRank.peso},${tenistaDtoRank.puntos},${tenistaDtoRank.mano},${tenistaDtoRank.fechaNacimiento},${tenistaDtoRank.ranking}\n")
                }
            Ok(data.size.toLong())
        } catch (e: Exception) {
            logger.error { "Error al guardar el fichero csv de tenistas: ${e.message}" }
            Err(TenistaError.TenistaStorageError("Error al guardar el fichero csv de tenistas: ${e.message}"))
        }
    }

    fun loadCsv(file: File): Result<List<Tenista>, TenistaError> {
        logger.debug { "Cargando datos del fichero $file" }
        val borrarTitulo = if (file.readLines().first().startsWith("id")) 1 else 0
        val listaTenistas = file.readLines().drop(borrarTitulo).map {
            val data = it.split(",")
            if (validator.validate(data).isOk) {
                TenistaDto(
                    id = data[0].toInt(),
                    nombre = data[1],
                    pais = data[2],
                    altura = data[3].toInt(),
                    peso = data[4].toInt(),
                    puntos = data[5].toInt(),
                    mano = data[6].uppercase(),
                    fechaNacimiento = data[7],
                    createdAt = LocalDateTime.now().toString(),
                    updatedAt = LocalDateTime.now().toString(),
                ).toTenista()
            } else {
                logger.error { "Error al cargar el fichero csv de tenistas: ${validator.validate(data).error.message}" }
                return Err(TenistaError.TenistaStorageError("Error al cargar el fichero csv de tenistas: ${validator.validate(data).error.message}"))
            }
        }
        return Ok(listaTenistas)
    }
}