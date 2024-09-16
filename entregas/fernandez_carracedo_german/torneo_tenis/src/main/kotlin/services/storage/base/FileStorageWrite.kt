package org.example.services.storage.base

import java.io.File

interface FileStorageWrite<T> {
    fun writeToFile(list: List<T>, file: File)
}