package org.example.services.storage.base

import java.io.File

interface FileStorageRead<T> {
    fun readFromFile(file: String): List<T>
}