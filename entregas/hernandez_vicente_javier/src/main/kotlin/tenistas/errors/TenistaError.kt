package tenistas.errors

sealed class TenistaError(message: String) {
    class TenistaNotFound(message: String): TenistaError(message)
    class TenistaNotUpdated(message: String): TenistaError(message)
    class TenistaNotDeleted(message: String): TenistaError(message)
}