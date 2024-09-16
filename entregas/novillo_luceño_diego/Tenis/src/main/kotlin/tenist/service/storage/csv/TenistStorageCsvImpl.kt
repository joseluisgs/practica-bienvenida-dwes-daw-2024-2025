package org.example.tenist.service.storage.csv

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.tenist.errors.TenistError
import org.example.tenist.mappers.findDexteriry
import org.example.tenist.models.Tenist
import org.koin.core.annotation.Singleton
import org.lighthousegames.logging.logging
import java.io.File
import java.time.LocalDate
import java.time.LocalDateTime

private val logger = logging()

@Singleton
class TenistStorageCsvImpl() : TenistStorageCsv {
    /*
     * Exporta una lista de tenistas a un archivo CSV.
     * @param file el fichero en el que se quieren exportar los datos
     * @param list la lista de Tenistas que se quieren exportar
     */
    override fun export(file: File, list: List<Tenist>): Result<Unit, TenistError> {
        logger.info {"Exportando tenistas a CSV: ${file.absolutePath}"}
        return try {
            TODO()
            Ok(Unit)
        } catch (e: Exception) {
            Err(TenistError.ExportError("Error al exportar los tenistas a CSV: ${e.message}"))
        }
    }

    override fun import(file: File): Result<List<Tenist>, TenistError> {
        logger.info {"Importando tenistas desde CSV: ${file.absolutePath}"}
        return try {
            val lines = file.readLines()
            if (lines[0].replace(" ", "") == "id,nombre,pais,altura,peso,puntos,mano,fecha_nacimiento") lines.drop(1)
            Ok(lines.map {
                val tenist = it.split(",")
                Tenist(
                    id = tenist[0].toInt(),
                    name = tenist[1],
                    country = tenist[2],
                    height = tenist[3].toDoubleOrNull() ?: 0.0,
                    weight = tenist[4].toInt(),
                    points = tenist[5].toIntOrNull() ?: 0,
                    dominantHand = findDexteriry(tenist[6]),
                    birthDate = LocalDate.parse(tenist[7]),
                    createdAt = LocalDateTime.now(),
                    updatedAt = LocalDateTime.now()
                )
            })
        } catch (e: Exception) {
            Err(TenistError.ImportError("Error al importar los tenistas desde CSV: ${e.message}"))
        }
    }

}