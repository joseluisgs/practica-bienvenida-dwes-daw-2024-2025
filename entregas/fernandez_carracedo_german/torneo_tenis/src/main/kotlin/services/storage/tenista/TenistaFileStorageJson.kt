package org.example.services.storage.tenista

import org.example.models.Tenista
import org.example.services.storage.base.FileStorageWrite
import java.io.File

class TenistaFileStorageJson: FileStorageWrite<Tenista> {
    override fun writeToFile(list: List<Tenista>, file: File) {
        TODO("Not yet implemented")
    }
}