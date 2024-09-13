package org.example.service.storage

import Errors
import TenistaMapper.elegirMano
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.tenistas.models.Tenista
import org.lighthousegames.logging.logging
import java.io.File
import java.time.LocalDate
import java.time.LocalDateTime

private val logger = logging()

/**
 * Implementación del almacenamiento de tenistas desde y hacia archivos CSV.
 */
class StorageCsvImpl: StorageCsv {

    /**
     * Carga tenistas desde un archivo CSV.
     * @param file El archivo CSV que contiene los tenistas.
     * @return Un resultado que contiene una lista de tenistas cargados o un error si hubo algún problema durante la carga.
     */
    override fun importFromCSV(file: File): Result<List<Tenista>, Errors> {
        try {
            val tenistas = file.readLines()
                .drop(1)
                .mapNotNull {
                    val tenista = it.split(',')
                    elegirMano(tenista[6])?.let { mano ->
                        Tenista(
                            id = tenista[0].toInt(),
                            nombre = tenista[1],
                            pais = tenista[2],
                            altura = tenista[3].toDouble(),
                            peso = tenista[4].toDouble(),
                            puntos = tenista[5].toInt(),
                            mano = mano,
                            fechaNacimiento = LocalDate.parse(tenista[7]),
                            createdAt = LocalDateTime.parse(tenista[8]),
                            updatedAt = LocalDateTime.parse(tenista[9])
                        )
                    }
                }

            return Ok(tenistas)
        } catch (e: Exception) {
            logger.debug { "Hubo un error al cargar los Tenistas del archivo ${file.name}: ${e.message}" }
            return Err(Errors.TenistaStorageError("Hubo un error al cargar los Tenistas del archivo ${file.name}"))
        }
    }
}
