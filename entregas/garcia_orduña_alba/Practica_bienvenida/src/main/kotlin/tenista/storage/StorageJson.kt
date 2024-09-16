package tenista.storage

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.persona.dto.TenistaDto
import org.example.persona.errors.TenistaError
import org.example.persona.models.Tenista
import org.example.tenista.mappers.toTenista
import org.example.tenista.mappers.toTenistaDto
import org.lighthousegames.logging.logging
import java.io.File

private val logger = logging()

class StorageJson : Storage {
    override fun load(file: File): Result<List<Tenista>, TenistaError> {
        logger.debug { "Leyendo el fichero: $file" }
        return try {
            val json = Json { ignoreUnknownKeys = true}
            Ok(json.decodeFromString<List<TenistaDto>>(file.readText()).map { it.toTenista() })
        }catch (e:Exception){
            logger.error { "Error al leer el fichero ${e.message}" }
            Err(TenistaError.FileError("Error al leer el fichero ${e.message}"))
        }
    }

    override fun store(file: File, listPersonas: List<Tenista>): Result<Unit, TenistaError> {
        logger.debug { "Guardando el fichero: $file" }
        return try {
            val json = Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                explicitNulls= false
            }
            val jsonString = json.encodeToString(listPersonas.map { it.toTenistaDto() })
            file.writeText(jsonString)
            Ok(Unit)
        } catch (e: Exception) {
            logger.error { "Error al escribir: ${e.message}" }
            Err(TenistaError.FileError("Error al escribir"))
        }
    }

}