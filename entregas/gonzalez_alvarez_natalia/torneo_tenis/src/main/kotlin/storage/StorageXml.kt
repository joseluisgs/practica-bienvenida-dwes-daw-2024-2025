package storage

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import nl.adaptivity.xmlutil.serialization.XML
import org.lighthousegames.logging.logging
import tenistas.dto.TenistaDto
import tenistas.errors.TenistaError
import tenistas.mappers.toTenistaList
import tenistas.models.Tenista
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.Path
import kotlin.io.path.writeText

private val logger = logging()

/**
 * Clase que proporciona métodos para guardar una lista de tenistas en formato XML.
 *
 * @author Natalia González
 * @version 1.0
 */
class StorageXml {

    /**
     * Guarda una lista de tenistas en un archivo XML.
     *
     * @param list Lista de objetos [Tenista] que se guardarán en el archivo.
     * @param file Archivo en el que se almacenarán los datos en formato XML.
     * @return Un [Result] que contiene el número de tenistas guardados (como [Long]) en caso de éxito,
     * o un [TenistaError] en caso de fallo (por ejemplo, si hay un problema al escribir el archivo).
     */
    fun save(list: List<Tenista>, file: File): Result<Long, TenistaError> {
        logger.debug { "Guardando en fichero xml: $file" }
        return try {
            val xml = XML { indent = 4 }
            file.writeText(xml.encodeToString<List<Tenista>>(list))
            Ok(list.size.toLong())
        } catch (e: Exception) {
            logger.debug { "No se ha guardado el fichero" }
            Err(TenistaError.StorageTenistaError("No se ha podido guardar el fichero: ${e.message}"))
        }
    }
}