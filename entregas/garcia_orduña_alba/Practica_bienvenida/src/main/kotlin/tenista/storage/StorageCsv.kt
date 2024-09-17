package tenista.storage

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.persona.errors.TenistaError
import org.example.persona.models.ManoTenista
import org.example.persona.models.Tenista
import org.lighthousegames.logging.logging
import java.io.File
import java.time.LocalDateTime

private val logger = logging()

class StorageCsv : Storage {
    override fun load(file: File): Result<List<Tenista>, TenistaError> {
        logger.debug { "Cargando el fichero $file" }
        return try {
            val tenistas = file.readLines().drop(1).map { line ->
                val campos = line.split(",")
                Tenista(
                    id = campos[0].toInt(),
                    nombre = campos[1],
                    pais = campos[2],
                    altura = campos[3].toInt(),
                    peso = campos[4].toInt(),
                    puntos = campos[5].toInt(),
                    mano = ManoTenista.valueOf(campos[6]),
                    fecha_nacimiento = LocalDateTime.parse(campos[7]),
                    created_at = LocalDateTime.parse(campos[8]),
                    updated_at = LocalDateTime.parse(campos[9])
                )
            }
            Ok(tenistas)
        } catch (e: Exception) {
            logger.error(e) { "Error al cargar el fichero : ${e.message}" }
            Err(TenistaError.FileError("Error al leer el fichero"))
        }
    }

    override fun store(file: File, listTenistas: List<Tenista>): Result<Unit, TenistaError> {
        logger.debug { "Guardando ${listTenistas.size} tenistas en el fichero ${file.name}" }
        return try {

            file.writeText("id,nombre,pais,altura,peso,puntos,mano,fecha_nacimiento,created_at,updated_at\n")

            listTenistas.forEach { tenista ->
                file.appendText("${tenista.id},${tenista.nombre},${tenista.pais},${tenista.altura},${tenista.peso},${tenista.puntos},${tenista.mano},${tenista.fecha_nacimiento},${tenista.created_at},${tenista.updated_at}\n")
            }
            Ok(Unit)
        } catch (e: Exception) {
            logger.error { "Error al guardar el fichero: ${e.message}" }
            Err(TenistaError.FileError("Error al guardar el fichero"))
        }
    }
}