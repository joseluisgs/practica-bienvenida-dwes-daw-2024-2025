package org.example.service

import FileError
import ParsingError
import TenistasError
import ValidationError
import org.example.tenista.models.Mano
import org.example.tenista.models.Tenista
import java.io.File
import java.time.LocalDate
import java.util.logging.Logger

class ReadFile {

    private val logger = Logger.getLogger(ReadFile::class.java.name)

    fun leerCSV(filePath: String): List<Tenista>? {
        val file = File(filePath)
        if (!file.exists()) {
            throw FileError("File does not exist at the provided path: $filePath")
        }

        return try {
            file.readLines()
                .let { lines ->
                    if (lines.isEmpty()) {
                        throw FileError("File is empty or could not be read.")
                    }
                    processLines(lines)
                }
        } catch (e: TenistasError) {
            logger.severe("Error: ${e.message}")
            null
        } catch (e: Exception) {
            logger.severe("Unexpected error: ${e.message}")
            null
        }
    }

    private fun processLines(lines: List<String>): List<Tenista> {
        lines.first()
        val dataLines = lines.drop(1) // Saltamos el encabezado

        return dataLines.mapNotNull { line ->
            val datos = line.split(",").map { it.trim() }
            try {
                validateData(datos)
                parseTenista(datos)
            } catch (e: TenistasError) {
                logger.warning("Error processing line: ${e.message}")
                null
            }
        }.let { tenistas ->
            assignRankings(tenistas)
        }
    }

    private fun validateData(datos: List<String>) {
        if (datos.size < 8 || datos.any { it.isBlank() || it == "\"\"" }) {
            throw ValidationError("Insufficient data or blank fields: ${datos.joinToString(",")}")
        }
    }

    private fun parseTenista(datos: List<String>): Tenista {
        return try {
            Tenista(
                id = datos[0].toLong(),
                nombre = datos[1],
                pais = datos[2],
                altura = datos[3].toInt(),
                peso = datos[4].toDouble(),
                puntos = datos[5].toInt(),
                mano = Mano.valueOf(datos[6].uppercase()),
                fechaNacimiento = LocalDate.parse(datos[7]),
                ranking = 0
            )
        } catch (e: Exception) {
            throw ParsingError("Error parsing line: ${datos.joinToString(",")} - ${e.message}")
        }
    }

    fun assignRankings(tenistas: List<Tenista>): List<Tenista> {
        // Ordena los tenistas por puntos de forma descendente y mantiene el índice original
        val sortedWithOriginalIndex = tenistas
            .mapIndexed { index, tenista -> tenista to index }
            .sortedByDescending { it.first.puntos }

        // Asigna el ranking a cada tenista
        val rankedTenistas = sortedWithOriginalIndex.mapIndexed { newIndex, (tenista, originalIndex) ->
            tenista.copy(ranking = newIndex + 1)
        }

        // Crea una lista de tenistas con rankings asignados, manteniendo el orden original
        return tenistas.map { originalTenista ->
            rankedTenistas.find { it.id == originalTenista.id } ?: originalTenista
        }
    }


}
