package tenista.storages

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.lighthousegames.logging.logging
import tenista.errors.TenistaError
import tenista.mappers.toTenista
import tenista.models.Tenista
import tenista.validator.TenistaValidator
import tenistas.dto.TenistaDto
import java.io.File
import java.time.LocalDateTime


private val logger = logging()

class StorageCsv : leerFichero , escribirFichero {

    private val validator = TenistaValidator()

    override fun leer(file: File): Result<List<Tenista>, TenistaError> {
        logger.debug { "Leyendo tenistas from file: $file" }
        return try {
            val lines = file.reader().readLines()
            val hasHeader: Boolean

            if (lines.firstOrNull()?.startsWith("id,nombre,pais") == true) {
                hasHeader = true
            } else {
                hasHeader = false
            }

            val dataLines = if (hasHeader) {
                lines.drop(1)
            } else {
                lines
            }

            Ok(dataLines
                .map {
                    val data = it.split(",")
                    TenistaDto(
                        id = data[0].toInt(),
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
            )
        } catch (e: Exception) {
            logger.error(e) { "Error loading tenistas from file: $file" }
            Err(TenistaError.FileError("Error loading tenistas from file: $file"))
        }
    }

    override fun escribir(file: File, listTenistas: List<Tenista>): Result<List<Unit>, TenistaError> {
        logger.debug { "Escribiendo tenistas al archivo: $file" }

        return try {
            file.appendText("id,nombre,pais,altura,peso,puntos,mano,fecha_nacimiento,created_at,updated_at\n")

            listTenistas.forEach { tenista ->
                file.appendText(
                    "${tenista.id},${tenista.nombre},${tenista.pais},${tenista.altura}," +
                            "${tenista.peso},${tenista.puntos},${tenista.mano},${tenista.fecha_nacimiento}," +
                            "${tenista.created_at},${tenista.updated_at}\n"
                )
            }
            Ok(listOf(Unit))
        } catch (e: Exception) {
            logger.error(e) { "Error al escribir tenistas en el archivo: $file" }
            Err(TenistaError.FileError("Error al escribir tenistas en el archivo: $file"))
        }
    }

}


