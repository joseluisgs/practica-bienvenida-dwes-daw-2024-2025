package tenista.errors

sealed class TenistaError(message: String) {
    class TenistaErrorValida(message: String) : TenistaError(message)
    class FileError(message: String) : TenistaError(message)
    class StorageError(message: String) : TenistaError(message)
    class ValidationError(message: String) : TenistaError(message)
}