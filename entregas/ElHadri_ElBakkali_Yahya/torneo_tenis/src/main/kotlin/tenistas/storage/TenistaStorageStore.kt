package tenistas.storage

import com.github.michaelbull.result.Result
import tenistas.errors.TenistasError
import tenistas.models.Tenista
import java.io.File

interface TenistaStorageStore {
    fun load(file: File): Result<List<Tenista>, TenistasError>
}