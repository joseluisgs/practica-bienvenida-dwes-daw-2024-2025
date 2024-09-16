package tenistas.storage

import com.github.michaelbull.result.Result
import tenistas.errors.TenistasError
import tenistas.models.Tenista
import java.io.File

interface TenistasStorageExport {
    fun store(file: File, lista: List<Tenista>): Result<Long, TenistasError>
}