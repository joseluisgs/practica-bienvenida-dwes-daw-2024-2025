package tenista.validator

import org.lighthousegames.logging.logging
import java.io.File

private val logger = logging()

class TenistaValidator {

    fun validarCsv(args: Array<String>): Boolean {
        if (args.size !in 1..2) {
            logger.error { "Número de argumentos incorrecto. Se espera 1 o 2 argumentos." }
            return false
        }

        val archivoEntrada = File(args[0])

        if (!archivoEntrada.name.endsWith(".csv")) {
            logger.error { "El archivo de entrada no tiene el formato .csv." }
            return false
        }

        if (!archivoEntrada.exists() || !archivoEntrada.isFile) {
            logger.error { "El archivo de entrada no existe o no es un archivo válido." }
            return false
        }

        return true
    }
}
