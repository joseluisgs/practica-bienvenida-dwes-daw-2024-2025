package storage

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.lighthousegames.logging.logging
import tenistas.dto.TenistaDto
import tenistas.errors.TenistaError
import tenistas.mappers.toTenista
import tenistas.mappers.toTenistaDto
import tenistas.models.Tenista
import java.io.File
import java.time.LocalDateTime

private val logger = logging()

/**
 * Clase que implementa la interfaz [Storage] para gestionar la carga y el guardado de datos
 * de tenistas en formato CSV.
 *
 * @author Natalia González
 * @version 1.0
 */
class StorageCsv : Storage<Tenista> {

    /**
     * Carga una lista de tenistas desde un archivo CSV.
     *
     * @param file El archivo CSV desde el que se cargarán los datos.
     * @return Un [Result] con la lista de objetos [Tenista] en caso de éxito, o un [TenistaError]
     * en caso de fallo (por ejemplo, si el archivo no existe o hay errores en el formato).
     */
    override fun load(file: File): Result<List<Tenista>, TenistaError> {
        logger.debug { "Cargando tenistas desde el fichero csv" }
        return try {
            val tenistasList = file.readLines().drop(1)
                .map {
                    val data = it.split(",")
                    TenistaDto(
                        id = data[0],
                        nombre = data[1],
                        pais = data[2],
                        altura = data[3].toInt(),
                        peso = data[4].toInt(),
                        puntos = data[5].toInt(),
                        mano = data[6],
                        fecha_nacimiento = data[7],
                        created_at = LocalDateTime.now().toString(),
                        updated_at = LocalDateTime.now().toString()
                    ).toTenista()
                }
            Ok(tenistasList)
        } catch (e: Exception) {
            logger.debug { "No existe el fichero" }
            Err(TenistaError.StorageTenistaError("Error al cargar el fichero csv: ${e.message}"))
        }
    }

    /**
     * Guarda una lista de tenistas en un archivo CSV.
     *
     * @param list La lista de tenistas que se guardará.
     * @param file El archivo CSV en el que se guardarán los datos.
     * @return Un [Result] con el número de tenistas guardados (como [Long]) en caso de éxito,
     * o un [TenistaError] en caso de fallo (por ejemplo, si hay un problema al escribir el archivo).
     */
    override fun save(list: List<Tenista>, file: File): Result<Long, TenistaError> {
        logger.debug { "Guardando fichero csv: $file" }
        return try {
            file.writeText("id,nombre,pais,altura,peso,puntos,mano,fecha nacimiento,created at, updated at\n")
            list.map { it.toTenistaDto() }
                .forEach { tenistaDto ->
                    file.appendText("${tenistaDto.id},${tenistaDto.nombre},${tenistaDto.pais},${tenistaDto.altura},${tenistaDto.peso},${tenistaDto.puntos},${tenistaDto.mano},${tenistaDto.fecha_nacimiento},${tenistaDto.created_at},${tenistaDto.updated_at}\n")
                }
            Ok(list.size.toLong())
        } catch (e: Exception) {
            logger.debug { "No se ha guardado el fichero" }
            Err(TenistaError.StorageTenistaError("Error al guardar el fichero csv: ${e.message}"))
        }
    }
}