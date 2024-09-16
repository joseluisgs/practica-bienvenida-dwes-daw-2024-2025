// Define la clase base para los errores
sealed class TenistasError(message: String) : Exception(message)

// Define errores específicos
class FileError(message: String) : TenistasError(message)
class ParsingError(message: String) : TenistasError(message)
class ValidationError(message: String) : TenistasError(message)
