package org.example.exceptions

sealed class StorageExceptions(message: String) : Exception(message) {
    class FileNotFound(message: String) : StorageExceptions(message)
    class FileNotReadable(message: String) : StorageExceptions(message)
}