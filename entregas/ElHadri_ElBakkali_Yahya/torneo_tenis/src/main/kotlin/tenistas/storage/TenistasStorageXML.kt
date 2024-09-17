package tenistas.storage

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.serialization.encodeToString
import nl.adaptivity.xmlutil.serialization.XML
import org.lighthousegames.logging.logging
import tenistas.dto.TenistaDto
import tenistas.errors.TenistasError
import tenistas.mappers.toDto
import tenistas.models.Tenista
import java.io.File


private val logger= logging()

class TenistasStorageXML:TenistasStorageExport {
    override fun store(file: File, lista: List<Tenista>): Result<Long, TenistasError> {
        logger.debug { "Guardando tenistas en fichero XML" }

        val listaOrdenada = lista.sortedByDescending { it.puntos }.map { it.toDto() }


        return try {
            val xml=XML{indent=4}
            val xmlString = xml.encodeToString<List<TenistaDto>>(listaOrdenada)
            file.writeText(xmlString)
            Ok(lista.size.toLong())
        } catch (e: Exception) {
            Err(TenistasError.FileError("Error al escribir el XML: ${e.message}"))
        }
    }
}
