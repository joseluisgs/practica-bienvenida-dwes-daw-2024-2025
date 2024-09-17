package tenistas.repositories

import tenistas.models.Tenista

/**
 * Interfaz para la gestión de operaciones relacionadas con los tenistas.
 *
 * @author Natalia González
 * @version 1.0
 */
interface TenistaRepository {

    /**
     * Obtiene todos los tenistas almacenados.
     *
     * @return Lista de todos los tenistas.
     */
    fun findAll(): List<Tenista>

    /**
     * Busca un tenista por su identificador.
     *
     * @param id Identificador del tenista a buscar.
     * @return El tenista con el identificador proporcionado, o `null` si no se encuentra.
     */
    fun findById(id: Long): Tenista?

    /**
     * Guarda un nuevo tenista.
     *
     * @param tenista El tenista a guardar.
     * @return El tenista guardado.
     */
    fun save(tenista: Tenista): Tenista

    /**
     * Actualiza un tenista existente.
     *
     * @param id Identificador del tenista a actualizar.
     * @param tenista El nuevo tenista con la información actualizada.
     * @return El tenista actualizado, o `null` si el tenista con el identificador proporcionado no existe.
     */
    fun update(id: Long, tenista: Tenista): Tenista?

    /**
     * Elimina todos los tenistas almacenados.
     */
    fun deleteAllTenistas()

    /**
     * Elimina un tenista por su identificador.
     *
     * @param id Identificador del tenista a eliminar.
     * @return El tenista eliminado, o `null` si el tenista con el identificador proporcionado no existe.
     */
    fun delete(id: Long): Tenista?
}