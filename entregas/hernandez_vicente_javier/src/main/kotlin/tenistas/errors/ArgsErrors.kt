package tenistas.errors

sealed class ArgsErrors(message: String) {
    class FileDoesNotExistError(message: String) : ArgsErrors(message)
    class InvalidExtension(message: String) : ArgsErrors(message)
    class InvalidArgumentsError(message: String) : ArgsErrors(message)
}