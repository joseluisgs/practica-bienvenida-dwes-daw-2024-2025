package storage

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import config.Config
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.lighthousegames.logging.logging
import tenistas.dto.TenistaDto
import tenistas.errors.TenistaError
import tenistas.mappers.toTenistaDtoList
import tenistas.models.Tenista
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.Path

private val logger = logging()

/**
 * Clase que proporciona métodos para guardar una lista de tenistas en formato JSON.
 *
 * @author Natalia González
 * @version 1.0
 */
class StorageJson {

    /**
     * Guarda una lista de tenistas en un archivo JSON.
     *
     * @param list Lista de objetos [Tenista] que se guardarán en el archivo.
     * @param file Archivo en el que se almacenarán los datos en formato JSON.
     * @return Un [Result] que contiene el número de tenistas guardados (como [Long]) en caso de éxito,
     * o un [TenistaError] en caso de fallo (por ejemplo, si hay un problema al escribir el archivo).
     */
    fun save(list: List<Tenista>, file: File): Result<Long, TenistaError> {
        logger.debug { "Guardando en fichero json: $file" }

        return try {
            val json = Json {
                ignoreUnknownKeys = true
                prettyPrint = true
            }
            val jsonString = json.encodeToString<List<TenistaDto>>(list.toTenistaDtoList())
            file.writeText(jsonString)
            Ok(list.size.toLong())
        } catch (e: Exception) {
            logger.debug { "No se ha guardado el fichero" }
            Err(TenistaError.StorageTenistaError("No se ha podido guardar el fichero: ${e.message}"))
        }
    }
}