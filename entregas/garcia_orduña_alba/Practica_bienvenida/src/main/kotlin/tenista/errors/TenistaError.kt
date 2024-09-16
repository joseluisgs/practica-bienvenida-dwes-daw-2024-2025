package org.example.persona.errors

sealed class TenistaError(message : String) {
    class FileError(message: String) : TenistaError(message)
    class TenistaErrorValida(message: String) : TenistaError(message)
}