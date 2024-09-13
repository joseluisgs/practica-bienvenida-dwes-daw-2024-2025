/**
 * Clase sellada que representa los posibles errores relacionados con los productos.
 * @param message El mensaje de error asociado.
 */
sealed class Errors(val message: String) {

    // Error que indica un problema relacionado con el almacenamiento de productos.
    class TenistaStorageError(message: String) : Errors(message)

    // Error que indica un producto inválido.
    class TenistaInvalido(message: String) : Errors(message)

    // Error que indica que el producto no ha sido encontrado.
    class TenistaNotFoundError(message: String) : Errors(message)
}