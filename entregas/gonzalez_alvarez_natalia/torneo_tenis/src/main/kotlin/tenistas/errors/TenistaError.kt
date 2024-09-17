package tenistas.errors

/**
 * Clase base para representar errores relacionados con los tenistas.
 *
 * @property message Mensaje descriptivo del error.
 * @author Natalia González
 * @version 1.0
 */
sealed class TenistaError(val message: String) {

    /**
     * Error que ocurre durante el proceso de almacenamiento de datos de tenistas.
     *
     * @param message Mensaje descriptivo del error.
     */
    class StorageTenistaError(message: String) : TenistaError(message)

    /**
     * Error que ocurre durante la validación de datos de tenistas.
     *
     * @param message Mensaje descriptivo del error.
     */
    class ValidatorTenistaError(message: String) : TenistaError(message)

    /**
     * Error que ocurre cuando un tenista no se encuentra en la base de datos.
     *
     * @param message Mensaje descriptivo del error.
     */
    class TenistaNotFoundError(message: String) : TenistaError(message)

    /**
     * Error que ocurre cuando no se puede actualizar un tenista.
     *
     * @param message Mensaje descriptivo del error.
     */
    class TenistaNotUpdatedError(message: String) : TenistaError(message)

    /**
     * Error que ocurre cuando no se puede eliminar un tenista.
     *
     * @param message Mensaje descriptivo del error.
     */
    class TenistaNotDeletedError(message: String) : TenistaError(message)
}