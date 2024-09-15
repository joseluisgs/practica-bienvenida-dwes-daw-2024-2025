package org.example.tenistas.storage.genericStorage

import com.github.michaelbull.result.Result
import org.example.tenistas.errors.TenistaError
import org.example.tenistas.models.Tenista
import org.example.tenistas.storage.storageCSV.StorageCSV
import org.example.tenistas.storage.storageJSON.StorageJSON
import org.example.tenistas.storage.storageXML.StorageXML
import org.lighthousegames.logging.logging
import java.io.File

private val logger = logging()

class TenistaStorages (
    val storageCSV: StorageCSV,
    val storageJson: StorageJSON,
    val storageXML: StorageXML
) {
    fun storeCsv(file: File, data: List<Tenista>): Result<Long, TenistaError> {
        logger.debug { "Guardando datos en fichero $file" }
        return storageCSV.storeCsv(file, data)
    }

    fun loadCsv(file: File): Result<List<Tenista>, TenistaError> {
        logger.debug { "Cargando datos en fichero $file" }
        return storageCSV.loadCsv(file)
    }

    fun storeJson(file: File, data: List<Tenista>): Result<Long, TenistaError> {
        logger.debug { "Guardando datos en fichero $file" }
        return storageJson.storeJSON(file, data)
    }

    fun storeXML(file: File, data: List<Tenista>): Result<Long, TenistaError> {
        logger.debug { "Guardando datos en fichero $file" }
        return storageXML.storeXml(file, data)
    }
}