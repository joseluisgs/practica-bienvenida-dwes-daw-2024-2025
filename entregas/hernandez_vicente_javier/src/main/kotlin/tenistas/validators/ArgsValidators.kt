package tenistas.validators

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.lighthousegames.logging.logging
import tenistas.errors.ArgsErrors
import java.io.File

private val logger = logging()

/**
 * Valida los argumentos de entrada.
 * @param path Ruta del archivo de entrada.
 * @return Result<String, ArgsErrors> con el resultado de la validación.
 * @since 1.0
 * @author Javier Hernández
 */
fun validateArgsEntrada(path: String): Result<String, ArgsErrors> {
    logger.debug { "Validando argumentos de entrada" }
    logger.debug { "Comprobando si el archivo existe: $path" }
     if (!File(path).exists() && !File(path).canRead() && !File(path).isFile) {
         logger.error {"Error al leer el archivo $path. No se puede encontrar o leer. Verifique que el archivo exista y que tenga permisos de lectura."}
        Err(ArgsErrors.FileDoesNotExistError("El archivo $path no existe o no se puede leer"))
    }
    val archivo = File(path)
    if (archivo.extension.equals("csv", ignoreCase = true)) {
        logger.error { "Error al leer el archivo $path. No tiene extensión.csv. Debe ser.csv. Verifique que el archivo tenga la extensión correcta."}
        Err(ArgsErrors.InvalidExtension("El archivo $path no tiene extensión .csv"))
    }
    return Ok(path)
}

/**
 * Valida los argumentos de salida.
 * @param path Ruta del archivo de salida.
 * @return Result<String, ArgsErrors> con el resultado de la validación.
 * @since 1.0
 * @author Javier Hernández
 */

fun validateArgsSalida(path: String): Result<String, ArgsErrors> {
    logger.debug { "Validando argumentos de salida" }
    if(path.isEmpty()) return Ok("argumento de salida vacio cogiendo valor por defecto")
    if(!validateArgsSalidaExtension(path)) return Err(ArgsErrors.InvalidExtension("El argumento de salida debe terminar en.json,.xml o.csv"))
    else return Ok("argumento de salida valido")
}

/**
 * Valida la extensión del archivo de salida.
 * @param path Ruta del archivo de salida.
 * @return Boolean con el resultado de la validación.
 * @since 1.0
 * @author Javier Hernández
 */

private fun validateArgsSalidaExtension(path: String): Boolean {
    return  path.contains(".json") || path.contains(".xml") || path.contains(".csv")
}
