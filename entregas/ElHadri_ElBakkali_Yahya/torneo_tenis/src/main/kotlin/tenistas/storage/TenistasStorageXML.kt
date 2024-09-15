package tenistas.storage

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import nl.adaptivity.xmlutil.serialization.XML
import org.lighthousegames.logging.logging
import tenistas.errors.TenistasError
import tenistas.models.Tenista
import java.io.File

private val logger= logging()

class TenistasStorageXML:TenistasStorageExport {
    override fun store(file: File, lista: List<Tenista>): Result<Long, TenistasError> {
        logger.debug { "Guardando tenistas en fichero XML" }

        return try {
            val xmlData = XML.encodeToString(lista)
            file.writer().use { writer ->
                writer.write(xmlData)
            }
            logger.info { "Tenistas guardados correctamente en el archivo XML" }
            Ok(lista.size.toLong())

        } catch (e: Exception) {
            logger.error { "Error al guardar el archivo XML: ${e.message}" }
            Err(TenistasError.FileError("Error al escribir en el archivo XML"))
        }
    }
}