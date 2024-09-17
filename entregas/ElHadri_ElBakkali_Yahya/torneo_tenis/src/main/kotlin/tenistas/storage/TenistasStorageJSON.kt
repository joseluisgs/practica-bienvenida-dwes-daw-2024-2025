package tenistas.storage

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.lighthousegames.logging.logging
import tenistas.dto.TenistaDto
import tenistas.errors.TenistasError
import tenistas.mappers.toDto
import tenistas.models.Tenista
import java.io.File

private val logger= logging()

class TenistasStorageJSON:TenistasStorageExport {
    override fun store(file: File, lista: List<Tenista>): Result<Long, TenistasError> {
        logger.debug { "Guardando tenistas en fichero json $file" }
        return try {
            val json = Json {
                prettyPrint = true
                ignoreUnknownKeys = true
            }
            val jsonString = json.encodeToString<List<TenistaDto>>(lista.map { it.toDto() })
            file.writeText(jsonString)
            Ok(lista.size.toLong())
        }catch(e:Exception){
            Err(TenistasError.FileError("Error al escribir el JSON: ${e.message}"))
        }
    }
}