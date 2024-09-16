package tenistas.errors

sealed class FileError(message: String) {
    class FileReadingError(message: String) : FileError(message)
    class FileWritingError(message: String) : FileError(message)
}