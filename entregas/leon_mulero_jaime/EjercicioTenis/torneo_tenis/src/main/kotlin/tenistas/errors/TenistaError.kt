package org.example.tenistas.errors

sealed class TenistaError(val message: String) {
    class TenistaNoEncontrado(message: String) : TenistaError(message)
    class TenistaNoValido(message: String) : TenistaError(message)
    class TenistaNoActualizado(message: String) : TenistaError(message)
    class TenistaNoEliminado(message: String) : TenistaError(message)
    class TenistaStorageError(message: String) : TenistaError(message)
}