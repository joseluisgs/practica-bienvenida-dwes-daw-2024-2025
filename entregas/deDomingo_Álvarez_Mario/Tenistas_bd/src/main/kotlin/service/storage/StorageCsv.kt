package org.example.service.storage

import Errors
import org.example.tenistas.models.Tenista
import java.io.File
import com.github.michaelbull.result.Result

interface StorageCsv{
    fun importFromCSV(file : File) : Result<List<Tenista>,Errors>
}