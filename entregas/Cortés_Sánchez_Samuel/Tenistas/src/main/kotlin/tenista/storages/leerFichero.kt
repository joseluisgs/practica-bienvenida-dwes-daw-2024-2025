package tenista.storages

import com.github.michaelbull.result.Result
import tenista.errors.TenistaError
import tenista.models.Tenista
import java.io.File

interface leerFichero {
    fun leer(file : File): Result<List<Tenista>, TenistaError>
}