package tenistas.errors

sealed class TenistasError(message: String) {
    class FileError(message: String) : TenistasError(message)
    class NoEncontrado(message: String) : TenistasError(message)
    class NoActualizado(message: String) : TenistasError(message)
}
