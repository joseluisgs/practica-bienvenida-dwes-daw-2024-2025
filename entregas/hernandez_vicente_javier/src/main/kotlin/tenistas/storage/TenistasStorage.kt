package tenistas.storage

import com.github.michaelbull.result.Result
import tenistas.errors.FileError
import tenistas.models.Tenista
import java.io.File

interface TenistasStorage {
    fun storeCsv(file: File, tenistas: List<Tenista>): Result<Unit, FileError>
    fun readCsv(file: File): Result<List<Tenista>, FileError>
    fun storeJson(file: File, tenistas: List<Tenista>): Result<Unit, FileError>
    fun storeXml(file: File, tenistas: List<Tenista>): Result<Unit, FileError>
}