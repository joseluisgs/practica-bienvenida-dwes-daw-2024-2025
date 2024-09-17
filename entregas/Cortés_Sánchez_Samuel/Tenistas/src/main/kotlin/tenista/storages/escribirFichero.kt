package tenista.storages

import com.github.michaelbull.result.Result
import tenista.errors.TenistaError
import tenista.models.Tenista
import java.io.File

interface escribirFichero {
    fun escribir(file : File, listTenistas : List<Tenista>): Result<List<Unit>, TenistaError>
}