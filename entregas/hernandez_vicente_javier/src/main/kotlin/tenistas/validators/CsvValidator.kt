package tenistas.validators

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import tenistas.errors.CsvErrors
import java.io.File

private val logger = org.lighthousegames.logging.logging()

/**
 * Valida el formato del archivo CSV.
 * @param csvContent Contenido del archivo CSV.
 * @return Result<Unit, CsvErrors> con el resultado de la validación.
 * @since 1.0
 * @author Javier Hernández
 */
fun validateCsvFormat(csvContent: String): Result<Unit, CsvErrors> {
    val archivo = File(csvContent)
    val lineas = archivo.readLines()
    val numeroFilas = lineas.size
    logger.debug { "Validando el formato del CSV" }
    if(numeroFilas !in 100..101) {
        logger.error { "Numero de filas incorrecto"}
        return Err(CsvErrors.InvalidCsvFormat("El archivo debe tener entre 100 y 100 filas. Tiene ${numeroFilas} filas."))
    }
    for ((indice, linea) in lineas.withIndex()) {
        val columnas = linea.split(',')
        if(columnas.size!= 8) {
            logger.error { "Numero de columnas incorrecto"}
            return Err(CsvErrors.InvalidCsvFormat("La fila $indice debe tener 8 columnas. Tiene ${columnas.size} columnas."))
        }
    }
    return Ok(Unit)
}